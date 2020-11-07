package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO pour le livre
 */
@Repository
public interface LivreRepository extends JpaRepository<Livre, Long>, JpaSpecificationExecutor<Livre> {

    /**
     * Trouve tous les livres contenant le mot clé de recherche en ignorant la case
     * @param mc mot clé de recherche
     * @return une liste de livre
     */
    List<Livre> findByTitreContainingIgnoreCase(String mc);

    /**
     * Trouve un livre par son titre
     * @param titre titre du livre à trouver
     * @return un livre
     */
    Livre findByTitre(String titre);
}
