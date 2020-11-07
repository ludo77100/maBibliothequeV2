package org.ludo.clientui.proxies;

import org.ludo.clientui.beans.EmpruntBean;
import org.ludo.clientui.beans.LivreBean;
import org.ludo.clientui.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "zuul-server", contextId = "MicroserviceBibliothequeProxy", configuration = FeignConfig.class)
public interface MicroserviceBibliothequeProxy {

    /*
    **************
    APIs Livre
    **************
     */

    /**
     * Controlleur pour récupérer la liste de tout les livres disponible
     */
    @GetMapping(value = "/bibliotheque/liste")
    List<LivreBean> listeLivre();

    /**
     * Controlleur pour effectuer une recherche sur les livres
     * @param mc mot clé pour effectuer la recherche
     */
    @GetMapping(value = "/bibliotheque/listeRecherche")
    List<LivreBean> listeLivreRecherche(@RequestParam(name = "mc")String mc);

    /**
     * Controlleur pour afficher les détails d'un livre
     * @param idLidvre id du livre pour lequel les détails sont demandés
     */
    @GetMapping(value = "/bibliotheque/livre/{idLivre}")
    LivreBean findLivreById(@PathVariable("idLivre")Long idLidvre);

    /*
    **************
    APIs Emprunt
    **************
     */

    /**
     * Récupère la liste de tous les emprunts
     */
    @GetMapping(value = "/bibliotheque/listeEmprunt")
    List<EmpruntBean> listeEmprunt();

    /**
     * Récupère un emprunt par son id
     * @param idEmprunt id de l'emprunt à retourner
     */
    @GetMapping(value = "/bibliotheque/emprunt/id/{idEmprunt}")
    EmpruntBean findById(@RequestParam Long idEmprunt);

    /**
     * Liste tous les emprunts pour un utilisateur
     * @param pseudoEmprunteur pseudo de l'utilisateur
     */
    @GetMapping(value = "/bibliotheque/emprunt/pseudo/{pseudoEmprunteur}")
    List<EmpruntBean> listeEmpruntUtilisateur(@PathVariable("pseudoEmprunteur") String pseudoEmprunteur);

    /**
     * Prolonge un emprunt par son id d'une durée de 4 semaines
     * @param idEmprunt id de l'emprunt à prolonger
     */
    @PutMapping(value = "/bibliotheque/emprunt/prolonger/{idEmprunt}")
    EmpruntBean prolongerEmprunt(@PathVariable("idEmprunt")Long idEmprunt);

}
