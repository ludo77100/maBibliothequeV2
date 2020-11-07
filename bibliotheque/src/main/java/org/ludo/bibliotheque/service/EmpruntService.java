package org.ludo.bibliotheque.service;


import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Livre;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmpruntService {

    List<Emprunt> findAll();

    Optional<Emprunt> findById(Long idEmprunt);

    List<Emprunt> findAllByPseudoEmprunteur(String pseudoEmprunteur);

    Emprunt ouvrirEmprunt(Long idLivre, String pseudoEmprunteur);

    Emprunt cloturerEmprunt(Long idEmprunt);

    Date ajouter4Semaines(Date date);

    Emprunt prolongerEmprunt(Long idEmprunt);

    List<Emprunt> listeLivreNonRendueApresDateFin();

}
