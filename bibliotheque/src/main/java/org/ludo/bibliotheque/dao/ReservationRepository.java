package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /*
    Ne fonctionne pas TODO
     */
    /*Set<Reservation> findAllByEtatReservationEnumsIsAttente();*/

}
