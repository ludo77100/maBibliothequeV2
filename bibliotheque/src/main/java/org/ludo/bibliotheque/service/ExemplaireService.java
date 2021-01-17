package org.ludo.bibliotheque.service;

import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;

import java.util.List;

public interface ExemplaireService {

    Exemplaire ajouterExemplaire(String titreLivre);

    List<Exemplaire> getAllExemplaire();

    List<Exemplaire> getAllExemplaireForLivre(String titreLivre);

    void deleteById(Long idExemplaire);

    Exemplaire findByIdentifiant(String identifiant);

    List<Exemplaire> findAllByTitre(String titre);
}
