package org.ludo.bibliotheque.controller;

import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExemplaireController {

    @Autowired
    ExemplaireService exemplaireService;

    //TODO controlleur pour ajouter un nouvelle exemplaire

    //TODO controlleur pour compter le nombre d'exemplaire

    @GetMapping(value = "/exemplaire/{idLivre}")
    public Exemplaire ajouterExemplaire(@PathVariable Long idLivre){
        return exemplaireService.ajouterExemplaire(idLivre) ;
    }

}
