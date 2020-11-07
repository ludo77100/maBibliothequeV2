package org.ludo.bibliotheque.dao;

import org.ludo.bibliotheque.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * DAO pour Emprunt
 */
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    /**
     * Trouve tous les emprunts d'un utilisateur par son pseudo et qui sont en cours
     * @param pseudoEmprunteur pseudo de l'utilisateur
     * @return une liste d'emprunts
     */
    List<Emprunt> findAllByPseudoEmprunteurAndEnCoursIsTrue(String pseudoEmprunteur);

    /**
     * Trouve tous les emprunts qui sont en cours et ou la date de fin prévu est avant la date du jour
     * @param dateDuJour date du jour
     * @return une liste d'emprunts
     */
    List<Emprunt> findAllByEnCoursTrueAndDateFinBefore(Date dateDuJour);
}
