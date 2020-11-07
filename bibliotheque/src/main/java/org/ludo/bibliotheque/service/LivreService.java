package org.ludo.bibliotheque.service;


import org.ludo.bibliotheque.entities.Livre;

import java.util.List;

public interface LivreService {

    List<Livre> findAll();

    List<Livre> findByTitreContainingIgnoreCase(String mc);

    Livre enregistrerNouveauLivre(Livre livre);

    void supprimerLivre(Long livre);

    Livre findLivreById(Long idLidvre);
}
