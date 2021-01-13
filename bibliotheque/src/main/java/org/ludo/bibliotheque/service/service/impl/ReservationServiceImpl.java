package org.ludo.bibliotheque.service.service.impl;

import org.ludo.bibliotheque.Enums.EtatEnums;
import org.ludo.bibliotheque.Enums.EtatReservationEnums;
import org.ludo.bibliotheque.dao.EmpruntRepository;
import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.dao.ReservationRepository;
import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.exceptions.EmpruntExceptions;
import org.ludo.bibliotheque.exceptions.ReservationExceptions;
import org.ludo.bibliotheque.service.EmailService;
import org.ludo.bibliotheque.service.EmpruntService;
import org.ludo.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    LivreRepository livreRepository ;

    @Autowired
    ReservationRepository reservationRepository ;

    @Autowired
    EmpruntRepository empruntRepository ;

    @Autowired
    EmailService emailService ;

    @Autowired
    EmpruntService empruntService ;

    @Override
    public Reservation ouvrirReservation(String pseudoDemandeur, String titreLivre) throws ReservationExceptions {

        Livre livreDemande = livreRepository.findByTitre(titreLivre);
        List<Emprunt> listeEmpruntUtilisateur = empruntRepository.findAllByPseudoEmprunteurAndEnCoursIsTrue(pseudoDemandeur);
        List<Reservation> listeReservationUtilisateur = reservationRepository.findAllBypseudoDemandeur(pseudoDemandeur);

        this.verifLivreReserveNonEmprunte(listeEmpruntUtilisateur, titreLivre, listeReservationUtilisateur);

        Set<Reservation> reservationList = livreDemande.getReservations();
        int tailleListeReservation = 0;
        for (Reservation e : reservationList) {
            if (e.getEtatReservationEnums() == EtatReservationEnums.ENCOURS){
                tailleListeReservation++;
            }
        }
        if (tailleListeReservation >= livreDemande.getExemplaires().size()*2) {
            throw new ReservationExceptions("La liste de réservations est complète");
        } else {


            Date date = new Date();
            Reservation ouvrirReservation = new Reservation();

            ouvrirReservation.setDateDemandeReservation(date);
            ouvrirReservation.setLivre(livreDemande);
            ouvrirReservation.setPseudoDemandeur(pseudoDemandeur);
            ouvrirReservation.setEtatReservationEnums(EtatReservationEnums.ENCOURS);
            return reservationRepository.save(ouvrirReservation);

        }
    }

    @Override
    public Reservation mettreReservationAttente(Exemplaire exemplaire) throws MessagingException {

        //Dans cette partie on créé la date de cloture a j+2
        Date dateCloture = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateCloture);
        calendar.add(Calendar.DATE, 2);
        dateCloture = calendar.getTime();

        //ici on appel la méthode pour trouver la reservation la plus ancienne
        Reservation reservation = this.getOlderReservationForLivre(exemplaire.getLivre().getTitre());

        //Ici on set les valeurs nécéssaires
        reservation.setEtatReservationEnums(EtatReservationEnums.ATTENTE);
        reservation.setExemplaire(exemplaire);
        reservation.setDateCloture(dateCloture);

        //Ici on envoie le mail pour prévenir qu'un exemplaire est disponible
        emailService.envoyerEmailExemplaireDispo(exemplaire, reservation);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getOlderReservationForLivre(String titreLivre) {

        Reservation reservationPlusAncienne = new Reservation();
        Livre livre = livreRepository.findByTitre(titreLivre);
        Date date = new Date();
        Set<Reservation> reservationSet = livre.getReservations();

        if (!reservationSet.isEmpty()) {
            for (Reservation e : reservationSet) {
                if (e.getEtatReservationEnums() == EtatReservationEnums.ENCOURS && e.getDateDemandeReservation().before(date)) {
                    reservationPlusAncienne = e;
                    date = e.getDateDemandeReservation();
                }
            }
        }
        return reservationPlusAncienne;
    }

    //TODO à tester
    /**
     * Cette méthode permet de vérifier qu'un utilisateur demandant une réservation pour un livre
     * ne le possède pas déjà sur un emprunt ou une reservation
     * @param listeEmpruntUtilisateur
     * @param titreLivre
     */
    public void verifLivreReserveNonEmprunte(List<Emprunt> listeEmpruntUtilisateur, String titreLivre, List<Reservation> listeReservationUtilisateur) throws ReservationExceptions{

        for (Emprunt emprunt : listeEmpruntUtilisateur) {
            String livre = emprunt.getExemplaire().getLivre().getTitre();
            if (livre.equals(titreLivre) && emprunt.isEnCours())
                throw new ReservationExceptions("Un emprunt pour ce livre existe déjà pour cette utilisateur");
        }

        for (Reservation reservation : listeReservationUtilisateur){
            String livre = reservation.getLivre().getTitre();
            if (livre.equals(titreLivre) && reservation.getEtatReservationEnums() == EtatReservationEnums.ENCOURS){
                throw new ReservationExceptions("Une réservation pour ce livre existe déjà pour cette utilisateur");
            }
        }
    }

    public void verificationReservationAttente() throws MessagingException {

        //On récupère toutes les reservations en attente
        Set<Reservation> reservations = reservationRepository.findAllByEtatReservationEnums(EtatReservationEnums.ATTENTE);
        //On initialise la date du jour
        Date dateDuJour = new Date();

        //Sur chaque reservations de la liste
        for (Reservation e: reservations) {
            //On regarde si la date de cloture est avant la date du jour
            if (e.getDateCloture().before(dateDuJour)){
                //Dans ce cas on cloture la reservation
                e.setEtatReservationEnums(EtatReservationEnums.CLOTURE);
                //Et on vérifie si il y a d'autre demandes de réservation et on execute ou pas la mise en attente d'une reservation
                if (!e.getExemplaire().getLivre().getReservations().isEmpty()) {
                    System.out.println("1");
                    Set<Reservation> reservationList = e.getExemplaire().getLivre().getReservations();
                    for (Reservation f : reservationList){
                        System.out.println("2");
                        if (f.getEtatReservationEnums() == EtatReservationEnums.ENCOURS){
                            System.out.println("3");
                            this.mettreReservationAttente(e.getExemplaire());
                            break;
                        }
                    }
                } else {
                    //Si aucune reservation, l'exemplaire passe à disponible
                    e.getExemplaire().setEtat(EtatEnums.DISPONIBLE);
                    e.getExemplaire().getLivre().setQuantiteDispo(e.getExemplaire().getLivre().getQuantiteDispo() + 1);
                }
                reservationRepository.save(e);
            }
        }
    }

    @Override
    public Reservation accepterReservation(long idReservation) throws EmpruntExceptions {
        Reservation reservation = reservationRepository.findById(idReservation).get();
        Exemplaire exemplaire = reservation.getExemplaire();

        empruntService.ouvrirEmprunt(exemplaire.getIdentifiant(), reservation.getPseudoDemandeur());
        reservation.setExemplaire(null);
        reservation.setEtatReservationEnums(EtatReservationEnums.CLOTURE);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservationForUser(String pseudoEmprunteur) {
        List<Reservation> listeReservationUtilisateur = reservationRepository.findAllBypseudoDemandeur(pseudoEmprunteur);

        for (int i = 0 ; i < listeReservationUtilisateur.size() ; i++){

            Date dateReservationUtilisateur = listeReservationUtilisateur.get(i).getDateDemandeReservation();
            int compteurReservations = 1 ;
            Set<Reservation> reservations = listeReservationUtilisateur.get(i).getLivre().getReservations();

    for (Reservation e : reservations){
                if (e.getDateDemandeReservation().before(dateReservationUtilisateur) && e.getEtatReservationEnums().equals(EtatReservationEnums.ENCOURS)){
                    compteurReservations = compteurReservations +1 ;
                }
            }
            listeReservationUtilisateur.get(i).setPositionFileAttente(compteurReservations);
        }

        for (Reservation e : listeReservationUtilisateur){
            Set<Exemplaire> exemplaireSet = e.getLivre().getExemplaires();
            Date date = new Date() ;
            for (Exemplaire f : exemplaireSet){

                if (f.getEtat().equals(EtatEnums.EMPRUNTE))
                    empruntService.ajouter4Semaines(date);
                e.setDateDisponibiliteEstimee(date);
            }
            e.setDateDisponibiliteEstimee(date);
        }
        return listeReservationUtilisateur ;
    }

    @Override
    public Reservation fermerReservation(long idReservation) {

        Reservation reservation = reservationRepository.findById(idReservation).get();
        Date date = new Date();

        reservation.setEtatReservationEnums(EtatReservationEnums.ANNULE);
        reservation.setDateCloture(date);

        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteById(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }

}
