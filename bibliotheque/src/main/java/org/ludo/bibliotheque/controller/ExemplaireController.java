package org.ludo.bibliotheque.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Cette API permet l'ajout d'un exemplaire avec le titre d'un livre")
    @GetMapping(value = "/exemplaire/{titreLivre}")
    public Exemplaire ajouterExemplaire(@PathVariable String titreLivre){
        return exemplaireService.ajouterExemplaire(titreLivre) ;
    }

}
