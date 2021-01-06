package org.ludo.bibliotheque.service;


import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.exceptions.EmpruntExceptions;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmpruntService {

    List<Emprunt> findAll();

    Optional<Emprunt> findById(Long idEmprunt);

    List<Emprunt> findAllByPseudoEmprunteur(String pseudoEmprunteur);

    Emprunt ouvrirEmprunt(String identifiantExemplaire, String pseudoEmprunteur) throws EmpruntExceptions;

    Emprunt cloturerEmprunt(Long idEmprunt) throws EmpruntExceptions, MessagingException;

    Date ajouter4Semaines(Date date);

    Emprunt prolongerEmprunt(Long idEmprunt);

    List<Emprunt> listeLivreNonRendueApresDateFin();

    void deleteById(Long idEmprunt);
}
