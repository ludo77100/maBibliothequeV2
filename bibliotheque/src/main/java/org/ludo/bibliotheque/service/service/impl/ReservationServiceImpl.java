package org.ludo.bibliotheque.service.service.impl;

import org.ludo.bibliotheque.dao.EmpruntRepository;
import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.dao.ReservationRepository;
import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.exceptions.ReservationExceptions;
import org.ludo.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    LivreRepository livreRepository ;

    @Autowired
    ReservationRepository reservationRepository ;

    @Autowired
    EmpruntRepository empruntRepository ;

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

        return reservationRepository.save(ouvrirReservation);
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
}
