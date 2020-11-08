package org.ludo.bibliotheque.service.service.impl;

import org.ludo.bibliotheque.dao.ExemplaireRepository;
import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.SortedSet;

@Service
public class ExemplaireServiceImpl implements ExemplaireService {

    @Autowired
    ExemplaireRepository exemplaireRepository ;

    @Autowired
    LivreRepository livreRepository ;

    @Override
    public Exemplaire ajouterExemplaire(Long idLivre) {

        Exemplaire exemplaire = new Exemplaire();
        Livre livre = livreRepository.findById(idLivre).get();

        /*
        Remonte la dérnière référence pour ce livre,
        si null commence à 1
        sinon on prend la dernière ref et on fait + 1
         */

        SortedSet<Exemplaire> setExemplaires = (SortedSet<Exemplaire>) livre.getExemplaires();
        String identifiant = setExemplaires.last().getIdentifiant();

        if (identifiant == null){
            exemplaire.setIdentifiant(livre.getTitre().substring(0,1) + "1");
        } else {
            exe
        }

    /*
    Règle de gestion pour la création de l'identifiant d'un livre
    - Commence par la première lettre du titre
    -
    - Suivi d'un numéro qui s'incrémente en fonction du numéro de la dernière référence
     */

        return null;
    }
}
