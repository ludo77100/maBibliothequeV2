package org.ludo.bibliotheque.service.service.impl;

import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.dao.ReservationRepository;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    LivreRepository livreRepository ;

    @Autowired
    ReservationRepository reservationRepository ;

    @Override
    public Reservation ouvrirReservation(String pseudoDemandeur, String titreLivre) {

        Livre livre = livreRepository.findByTitre(titreLivre);
        Date date = new Date() ;
        Reservation ouvrirReservation = new Reservation() ;

        ouvrirReservation.setDateDemandeReservation(date);
        ouvrirReservation.setLivre(livre);
        ouvrirReservation.setPseudoDemandeur(pseudoDemandeur);

        return reservationRepository.save(ouvrirReservation);
    }
}
