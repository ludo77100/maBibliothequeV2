package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.entities.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

    Exemplaire findByIdentifiant(String identifiantExemplaire);
}
