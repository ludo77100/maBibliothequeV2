package org.ludo.bibliotheque.controller;

import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.exceptions.EmpruntExceptions;
import org.ludo.bibliotheque.exceptions.ReservationExceptions;
import org.ludo.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService ;

    @PostMapping(value = "/reservation/ouvrir")
    public Reservation ouvrirReservation(@RequestParam String pseudoDemandeur, @RequestParam String titreLivre) throws ReservationExceptions {
        return reservationService.ouvrirReservation(pseudoDemandeur, titreLivre) ;
    }

    @GetMapping(value = "reservation/all")
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservation();
    }


    @GetMapping(value = "/reservation/olderForLivre")
    public Reservation getOlderReservationForLivre(@RequestParam String titreLivre){
        return reservationService.getOlderReservationForLivre(titreLivre);
    }

    @GetMapping(value = "/reservation/accepter")
    public Reservation acceptReservation(@RequestParam long idReservation) throws EmpruntExceptions {
        return reservationService.accepterReservation(idReservation);
    }

    @GetMapping(value = "/reservation/user")
    public List<Reservation> getAllReservationForUtlisateur(@RequestParam String pseudoEmprunteur){
        return reservationService.getAllReservationForUser(pseudoEmprunteur);
    }

}
