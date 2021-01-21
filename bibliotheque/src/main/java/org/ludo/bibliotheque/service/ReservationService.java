package org.ludo.bibliotheque.service;

import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.exceptions.EmpruntExceptions;
import org.ludo.bibliotheque.exceptions.ReservationExceptions;

import javax.mail.MessagingException;
import java.util.List;

public interface ReservationService {
    Reservation ouvrirReservation(String pseudoDemandeur, String titreLivre) throws ReservationExceptions;

    Reservation mettreReservationAttente(Exemplaire exemplaire) throws MessagingException;

    List<Reservation> getAllReservation();

    Reservation getOlderReservationForLivre(String titreLivre);

    void verificationReservationAttente() throws MessagingException;

    Reservation accepterReservation(long idReservation) throws EmpruntExceptions;

    List<Reservation> getAllReservationForUser(String pseudoEmprunteur);

    Reservation fermerReservation(long idReservation) throws MessagingException;

    void deleteById(Long idReservation);
}
