package org.ludo.bibliotheque.service.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.bibliotheque.BibliothequeApplication;
import org.ludo.bibliotheque.Enums.EtatEnums;
import org.ludo.bibliotheque.Enums.EtatReservationEnums;
import org.ludo.bibliotheque.dao.EmpruntRepository;
import org.ludo.bibliotheque.dao.ExemplaireRepository;
import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.dao.ReservationRepository;
import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.exceptions.EmpruntExceptions;
import org.ludo.bibliotheque.service.EmpruntService;
import org.ludo.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class EmpruntServiceImpl implements EmpruntService {

    private static final Logger logger = LogManager.getLogger(BibliothequeApplication.class);

    @Autowired
    EmpruntRepository empruntRepository ;

    @Autowired
    LivreRepository livreRepository ;

    @Autowired
    ExemplaireRepository exemplaireRepository ;

    @Autowired
    ReservationRepository reservationRepository ;

    @Autowired
    ReservationService reservationService ;


    /**
     * Trouve tous les emprunts
     * @return liste d'emprunts
     */
    @Override
    public List<Emprunt> findAll() {
        logger.debug("Appel empruntService méthode findAll");
        return empruntRepository.findAll();
    }

    /**
     * Trouve un emprunt par son id
     * @param idEmprunt id de l'emprunt
     * @return l'emprunt
     */
    @Override
    public Optional<Emprunt> findById(Long idEmprunt) {
        logger.debug("Appel empruntService méthode findById avec paramètre id : " + idEmprunt);
        return empruntRepository.findById(idEmprunt);
    }

    /**
     * Trouve tous les emprunt pour un pseudo d'utilisateur
     * @param pseudoEmprunteur pseudo de l'utilisateur
     * @return la liste des emprunts
     */
    @Override
    public List<Emprunt> findAllByPseudoEmprunteur(String pseudoEmprunteur) {
        logger.debug("Appel empruntService méthode findAllByPseudoEmprunteur avec paramètre pseudoEmprunteur : " + pseudoEmprunteur);
        return empruntRepository.findAllByPseudoEmprunteurAndEnCoursIsTrue(pseudoEmprunteur);
    }

    /**
     * Ajoute 4 semaine à une date
     * @param date date à laquelle les 4 semaines doivent être ajoutée
     * @return la nouvelle date
     */
    @Override
    public Date ajouter4Semaines(Date date){

        logger.debug("Appel empruntService méthode ajouter4Semaines avec paramètre date : " + date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 28);
        return calendar.getTime();
    }

    /**
     * Prolonge un emprunt
     * @param idEmprunt id de l'emprunt à prolonger
     * @return l'emprunt prolongé
     */
    @Override
    public Emprunt prolongerEmprunt(Long idEmprunt) {

        logger.debug("Appel empruntService méthode prolongerEmprunt avec paramètre idEmprunt : " + idEmprunt);

        Emprunt emprunt = empruntRepository.findById(idEmprunt).get();

        Date dateDebut = emprunt.getDateFin();

        if (emprunt.isProlongeable() == true && emprunt.isEnCours() == true) {
            emprunt.setDateFin(ajouter4Semaines(dateDebut));
            emprunt.setProlongeable(false);
        } else {
            return null ;
        }
        return empruntRepository.save(emprunt);
    }

    /**
     * Trouve tous les emprunts non rendues à date
     * @return liste d'emprunts
     */
    @Override
    public List<Emprunt> listeLivreNonRendueApresDateFin() {

        logger.debug("Appel empruntService méthode listeLivreNonRendueApresDateFin");

        Date dateDuJour = new Date();
        List<Emprunt> listeEmprunt = empruntRepository.findAllByEnCoursTrueAndDateFinBefore(dateDuJour);
        return listeEmprunt;
    }

    /**
     * Enregistre un nouvel emprunt
     * @param identifiantExemplaire identifiant de l'exemplaire emprunté
     * @param pseudoEmprunteur pseudo de l'emprunteur
     * @return le nouvel emprunt
     */
    @Transactional
    @Override
    public Emprunt ouvrirEmprunt(String identifiantExemplaire, String pseudoEmprunteur) throws EmpruntExceptions {

        logger.debug("Appel empruntService méthode ouvrirEmprunt");

        Emprunt nouvelEmprunt = new Emprunt();
        Exemplaire exemplaire = exemplaireRepository.findByIdentifiant(identifiantExemplaire);
        Date date = new Date();

            nouvelEmprunt.setDateDebut(date);
            nouvelEmprunt.setDateFin(ajouter4Semaines(date));
            nouvelEmprunt.setPseudoEmprunteur(pseudoEmprunteur);
            nouvelEmprunt.setExemplaire(exemplaire);
            nouvelEmprunt.setEnCours(true);
            nouvelEmprunt.setProlongeable(true);
            nouvelEmprunt.setExemplaire(exemplaire);
            exemplaire.setEtat(EtatEnums.EMPRUNTE);
            exemplaire.getLivre().setQuantiteDispo(exemplaire.getLivre().getQuantiteDispo() - 1 );

            return empruntRepository.save(nouvelEmprunt);

    }

    /**
     * Cloture un emprunt
     * @param idEmprunt id de l'emprûnt à clôturer
     * @return emprûnt clôturer
     */
    @Transactional
    @Override
    public Emprunt cloturerEmprunt(Long idEmprunt) throws EmpruntExceptions, MessagingException {

        logger.debug("Appel empruntService méthode cloturerEmprunt");

        Emprunt emprunt = empruntRepository.findById(idEmprunt).get();
        Livre livre = emprunt.getExemplaire().getLivre();
        Date date = new Date();

        if (!livre.getReservations().isEmpty()) {
            reservationService.mettreReservationAttente(emprunt.getExemplaire());
            emprunt.getExemplaire().setEtat(EtatEnums.ATTENTE);
        } else {
            emprunt.getExemplaire().setEtat(EtatEnums.DISPONIBLE);
            emprunt.setEnCours(false);
            emprunt.setDateFin(date);
        }
        return emprunt;
    }
}