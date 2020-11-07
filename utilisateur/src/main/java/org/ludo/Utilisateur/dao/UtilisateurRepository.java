package org.ludo.Utilisateur.dao;

import org.ludo.Utilisateur.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    /**
     * Trouve un utilisateur par son pseudo
     * @param pseudo pseudo utilisateur
     * @return l'utilisateur
     */
    Utilisateur findByPseudo(String pseudo);


}
