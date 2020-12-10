package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.Enums.EtatReservationEnums;
import org.ludo.bibliotheque.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Set<Reservation> findAllByEtatReservationEnums(EtatReservationEnums attente);

    List<Reservation> findAllBypseudoDemandeur(String pseudoEmprunteur);
}
