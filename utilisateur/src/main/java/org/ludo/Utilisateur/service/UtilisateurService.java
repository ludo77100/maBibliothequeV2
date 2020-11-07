package org.ludo.Utilisateur.service;

import org.ludo.Utilisateur.dto.UtilisateurDto;
import org.ludo.Utilisateur.entities.Utilisateur;

import java.util.List;


public interface UtilisateurService {
    List<Utilisateur> findAll();

    Utilisateur findByPseudo(String pseudo);

    Utilisateur enregistrerUtilisateur(UtilisateurDto utilisateurDto);

    Utilisateur privilegeUtilisateur(String pseudo);

    Utilisateur privilegeBibliothecaire(String pseudo);

    Utilisateur privilegeAdministrateur(String pseudo);
}
