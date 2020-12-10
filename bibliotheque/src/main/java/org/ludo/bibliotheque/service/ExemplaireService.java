package org.ludo.bibliotheque.service;

import org.ludo.bibliotheque.entities.Exemplaire;

import java.util.List;

public interface ExemplaireService {

    Exemplaire ajouterExemplaire(String titreLivre);

    List<Exemplaire> getAllExemplaire();

}
