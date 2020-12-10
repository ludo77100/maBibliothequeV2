package org.ludo.bibliotheque.service;

import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;

import java.util.List;

public interface ExemplaireService {

    Exemplaire ajouterExemplaire(String titreLivre);

    List<Exemplaire> getAllExemplaire();

    List<Exemplaire> getAllExemplaireForLivre(Livre livre);
}
