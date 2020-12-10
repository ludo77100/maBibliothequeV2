package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {

    Exemplaire findByIdentifiant(String identifiantExemplaire);

    List<Exemplaire> findAllByLivre(Livre livre);
}
