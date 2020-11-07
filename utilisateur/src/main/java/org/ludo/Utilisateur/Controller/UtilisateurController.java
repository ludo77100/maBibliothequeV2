package org.ludo.Utilisateur.Controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.Utilisateur.UtilisateurApplication;
import org.ludo.Utilisateur.dao.UtilisateurRepository;
import org.ludo.Utilisateur.dto.UtilisateurDto;
import org.ludo.Utilisateur.entities.Utilisateur;
import org.ludo.Utilisateur.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {

    private static final Logger logger = LogManager.getLogger(UtilisateurApplication.class);

    @Autowired
    private UtilisateurService utilisateurService;

    /**
     * Liste tous les utilisateurs
     * @return liste d'utilisateur
     */
    @ApiOperation("Pour retourner une liste des utilisateurs")
    @GetMapping(value = "/listeUtilisateur")
    public List<Utilisateur> listeUtilisateur(){

        logger.debug("Appel controlleur listeUtilisateur");

        return utilisateurService.findAll();
    }

    /**
     * Trouve un utilisateur par son pseudo
     * @param pseudo pseudo de l'utilisateur à trouver
     * @return un utilisateur
     */
    @ApiOperation(value = "Pour s'identifier")
    @GetMapping(value = "/utilisateur/{pseudo}")
    public Utilisateur login(@PathVariable String pseudo){

        logger.debug("Appel controlleur login");

        Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
        return utilisateur;
    }

    /**
     * Enregistre un nouvel utilisateur
     * @param utilisateurDto informations sur l'utilisateur
     * @return un nouvel utilisateur
     */
    @ApiOperation(value = "Pour enregistrer un nouvel utilisateur")
    @PostMapping(value = "/utilisateur")
    public Utilisateur enregistrerUtilisateur(@RequestBody UtilisateurDto utilisateurDto){

        logger.debug("Appel controlleur enregistrerUtilisateur");

        return utilisateurService.enregistrerUtilisateur(utilisateurDto);
    }

    /**
     * Permet la modification des privilège d'un utilisateur
     * @param pseudo pseudo pour appliquer la modification
     * @param privilege privilège à appliquer
     * @return la modification de privilège demandée
     */
    @ApiOperation(value = "Pour modifier les privilède d'un utilisateur")
    @PutMapping(value = "/utilisateur/{pseudo}/{privilege}")
    public Utilisateur modificationPrivilege(@PathVariable String pseudo, @PathVariable String privilege){

        logger.debug("Appel controlleur modificationPrivilege");

        switch (privilege){
            case "utilisateur":
                return utilisateurService.privilegeUtilisateur(pseudo) ;
            case "bibliothécaire":
                return utilisateurService.privilegeBibliothecaire(pseudo) ;
            case "administrateur":
                return utilisateurService.privilegeAdministrateur(pseudo) ;
        }
        return null ;
    }
}
