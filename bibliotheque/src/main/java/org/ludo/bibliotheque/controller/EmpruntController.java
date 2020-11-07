package org.ludo.bibliotheque.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.bibliotheque.BibliothequeApplication;

import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlleur Emprunt du microservice
 */
@RestController
public class EmpruntController {

    private static final Logger logger = LogManager.getLogger(BibliothequeApplication.class);

    @Autowired
    EmpruntService empruntService ;

    /**
     * Récupère la liste de tous les emprunts
     * @return liste d'emprunts
     */
    @ApiOperation(value = "Pour lister les emprunts")
    @GetMapping(value = "/listeEmprunt")
    public List<Emprunt> listeEmprunt(){
        logger.debug("Appel controlleur listeEmprunt");
        return empruntService.findAll();
    }

    /**
     * Récupère un emprunt par son id
     * @param idEmprunt id de l'emprunt à retourner
     * @return un emprunt
     */
    @ApiOperation(value = "Pour trouver un emprunt par son id")
    @GetMapping(value = "/emprunt/id/{idEmprunt}")
    public Optional<Emprunt> findById(@RequestParam Long idEmprunt){
        logger.debug("Appel controlleur findById emprunt");
        return empruntService.findById(idEmprunt);
    }

    /**
     * Liste tous les emprunts pour un utilisateur
     * @param pseudoEmprunteur pseudo de l'utilisateur
     * @return liste d'emprunts
     */
    @ApiOperation(value = "Pour lister les emprunts pour un utlisateur")
    @GetMapping(value = "/emprunt/pseudo/{pseudoEmprunteur}")
    public List<Emprunt> listeEmpruntUtilisateur(@PathVariable("pseudoEmprunteur") String pseudoEmprunteur){
        logger.debug("Appel controlleur listeEmpruntUtilisateur");
        return empruntService.findAllByPseudoEmprunteur(pseudoEmprunteur) ;
    }

    /**
     * Prolonge un emprunt par son id d'une durée de 4 semaines
     * @param idEmprunt id de l'emprunt à prolonger
     * @return responseEntity
     */
    @ApiOperation(value = "Permet de prolonger un prêt de 4 semaines")
    @PutMapping(value = "/emprunt/prolonger/{idEmprunt}")
    public ResponseEntity<Emprunt> prolongerEmprunt(@PathVariable("idEmprunt")Long idEmprunt){

        logger.debug("Appel controlleur prolongerEmprunt");

        Emprunt emprunt = empruntService.prolongerEmprunt(idEmprunt);

        if (emprunt != null){
            return ResponseEntity.ok(emprunt);
        }
        return new ResponseEntity(
                "Ne peut pas être prolongé",
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Permet l'ouverture d'un nouvel emprunt pour un livre
     * @param idLivre id du livre pour l'emprunt
     * @param pseudoEmprunteur pseudo de l'emprunteur
     * @return un nouvel emprunt
     */
    @ApiOperation(value = "Pour ouvrir un emprunt")
    @PostMapping(value = "/emprunt/{idLivre}")
    public Emprunt ouvrirEmprunt(@PathVariable Long idLivre, @RequestParam String pseudoEmprunteur){

        logger.debug("Appel controlleur ouvrirEmprunt");

        return empruntService.ouvrirEmprunt(idLivre, pseudoEmprunteur);
    }

    /**
     * Permet de clôturer un emprunt par son id
     * @param idEmprunt id de l'emprunt à clôturer
     * @return la clôture de l'emprunt
     */
    @ApiOperation(value = "Permet de de cloturer un emprunt")
    @PutMapping(value = "emprunt/{idEmprunt}")
    public Emprunt cloturerEmprunt(@RequestParam Long idEmprunt){
        logger.debug("Appel controlleur cloturerEmprunt");
        return empruntService.cloturerEmprunt(idEmprunt);
    }

    /**
     * Retourne une liste d'emprunt non rendu avant la date du jour
     * @return une liste d'emprunts
     */
    @ApiOperation(value = "Pour retourner une liste de livre non rendue avant la date de fin d'emprunt")
    @GetMapping(value = "/emprunt")
    public List<Emprunt> listeLivreNonRendueApresDateFin(){
        logger.debug("Appel controlleur listeLivreNonRendueApresDateFin");

        return empruntService.listeLivreNonRendueApresDateFin();
    }
}
