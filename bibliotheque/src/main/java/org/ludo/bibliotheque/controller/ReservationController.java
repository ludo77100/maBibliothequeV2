package org.ludo.bibliotheque.controller;

import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService ;

    @PostMapping(value = "/reservation/ouvrir")
    public Reservation ouvrirReservation(@RequestParam String pseudoDemandeur, @RequestParam String titreLivre){
        return reservationService.ouvrirReservation(pseudoDemandeur, titreLivre) ;
    }

}
