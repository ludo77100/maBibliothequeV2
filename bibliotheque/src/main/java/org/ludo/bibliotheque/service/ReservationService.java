package org.ludo.bibliotheque.service;

import org.ludo.bibliotheque.entities.Reservation;

public interface ReservationService {
    Reservation ouvrirReservation(String pseudoDemandeur, String titreLivre);
}
