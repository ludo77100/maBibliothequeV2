package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
