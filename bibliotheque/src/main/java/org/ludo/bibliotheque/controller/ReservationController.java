package org.ludo.bibliotheque.controller;

import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.exceptions.EmpruntExceptions;
import org.ludo.bibliotheque.exceptions.ReservationExceptions;
import org.ludo.bibliotheque.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationService reservationService ;

    @PostMapping(value = "/reservation/ouvrir/{titreLivre}/{pseudoDemandeur}")
    public Reservation ouvrirReservation(@PathVariable("titreLivre") String titreLivre, @PathVariable("pseudoDemandeur") String pseudoDemandeur) throws ReservationExceptions {
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

    @GetMapping(value = "/reservation/accepter/{idReservation}")
    public Reservation acceptReservation(@PathVariable long idReservation) throws EmpruntExceptions {
        return reservationService.accepterReservation(idReservation);
    }

    @GetMapping(value = "/reservation/user/{pseudoEmprunteur}")
    public List<Reservation> getAllReservationForUtlisateur(@PathVariable("pseudoEmprunteur") String pseudoEmprunteur){
        return reservationService.getAllReservationForUser(pseudoEmprunteur);
    }

    @GetMapping(value = "/reservation/fermer/{idReservation}")
    public Reservation fermerReservation(@PathVariable long idReservation){
        return reservationService.fermerReservation(idReservation);
    }


    @DeleteMapping(value = "/reservation/supprimer/{idReservation}")
    public void supprimerReservation(@PathVariable("idReservation") Long idReservation){
        reservationService.deleteById(idReservation);
    }

}
