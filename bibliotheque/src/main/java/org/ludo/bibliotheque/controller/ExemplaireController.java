package org.ludo.bibliotheque.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExemplaireController {

    @Autowired
    ExemplaireService exemplaireService;

    //TODO controlleur pour ajouter un nouvelle exemplaire

    //TODO controlleur pour compter le nombre d'exemplaire

    @ApiOperation(value = "Cette API permet l'ajout d'un exemplaire avec le titre d'un livre")
    @GetMapping(value = "/exemplaire/ajouter/{titreLivre}")
    public Exemplaire ajouterExemplaire(@PathVariable String titreLivre){
        return exemplaireService.ajouterExemplaire(titreLivre) ;
    }

    @ApiOperation(value = "Cette API permet de récupérer tous les exemplaires")
    @GetMapping(value = "/exemplaire/")
    public List<Exemplaire> getAllExemplaire(){
        return exemplaireService.getAllExemplaire();
    }

    @ApiOperation(value = "Cette API permet de récupérer tous les exemplaires d'un livre")
    @GetMapping(value = "/exemplaire/{titreLivre}")
    public List<Exemplaire> getAllExemplaireForLivre(@PathVariable("titreLivre") String titreLivre){
        return exemplaireService.getAllExemplaireForLivre(titreLivre);
    }

    @DeleteMapping(value = "/exemplaire/supprimer/{idExemplaire}")
    public void supprimerExemplaire(@PathVariable("idExemplaire") Long idExemplaire){
        exemplaireService.deleteById(idExemplaire);
    }

}
