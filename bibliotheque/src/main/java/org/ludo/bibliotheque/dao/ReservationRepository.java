package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByTitreLivre(String titreLivre);

    /*
    Ne fonctionne pas TODO
     */
    /*Set<Reservation> findAllByEtatReservationEnumsIsAttente();*/

}
