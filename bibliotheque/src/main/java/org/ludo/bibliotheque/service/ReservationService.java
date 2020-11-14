package org.ludo.bibliotheque.service;

import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.exceptions.ReservationExceptions;

public interface ReservationService {
    Reservation ouvrirReservation(String pseudoDemandeur, String titreLivre) throws ReservationExceptions;

    Reservation mettreReservationAttente(Exemplaire exemplaire, Reservation reservationPlusAncienne);
}
