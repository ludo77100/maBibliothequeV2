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

        this.verifLivreReserveNonEmprunte(listeEmpruntUtilisateur, titreLivre);

        Date date = new Date() ;
        Reservation ouvrirReservation = new Reservation() ;

        ouvrirReservation.setDateDemandeReservation(date);
        ouvrirReservation.setLivre(livreDemande);
        ouvrirReservation.setPseudoDemandeur(pseudoDemandeur);
        ouvrirReservation.setEtatReservationEnums(EtatReservationEnums.ENCOURS);

        return reservationRepository.save(ouvrirReservation);
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
                if (e.getDateDemandeReservation().before(date)) {
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
     * ne le possède pas déjà sur un emprunt
     * @param listeEmpruntUtilisateur
     * @param titreLivre
     */
    public void verifLivreReserveNonEmprunte(List<Emprunt> listeEmpruntUtilisateur, String titreLivre) throws ReservationExceptions{

        for (int i = 0 ; i < listeEmpruntUtilisateur.size() ; i++){
            Emprunt emprunt = listeEmpruntUtilisateur.get(i);
            String livre = emprunt.getExemplaire().getLivre().getTitre();
            if (livre == titreLivre)
                throw new ReservationExceptions("Un emprunt pour ce livre existe déjà pour cette utilisateur");
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
                    this.mettreReservationAttente(e.getExemplaire());
                } else {
                    //Si aucune reservation, l'exemplaire passe à disponible
                    e.getExemplaire().setEtat(EtatEnums.DISPONIBLE);
                }
                reservationRepository.save(e);
            }
        }
    }

    @Override
    public Reservation accepterReservation(long idReservation) throws EmpruntExceptions {
        Reservation reservation = reservationRepository.findById(idReservation).get();
        Exemplaire exemplaire = reservation.getExemplaire();

        reservation.setEtatReservationEnums(EtatReservationEnums.CLOTURE);
        empruntService.ouvrirEmprunt(exemplaire.getIdentifiant(), reservation.getPseudoDemandeur());

        return reservationRepository.save(reservation);
    }
}
