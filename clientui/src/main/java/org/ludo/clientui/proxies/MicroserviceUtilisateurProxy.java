package org.ludo.clientui.proxies;

import org.ludo.clientui.beans.UtilisateurBean;
import org.ludo.clientui.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "zuul-server", contextId = "MicroserviceUtilisateurProxy", configuration = FeignConfig.class)
public interface MicroserviceUtilisateurProxy {

    /**
     * Liste tous les utilisateurs
     */
    @GetMapping(value = "/utilisateur/listeUtilisateur")
    List<UtilisateurBean> listeUtilisateur();

    /**
     * Trouve un utilisateur par son pseudo
     * @param pseudo pseudo de l'utilisateur à trouver
     * @return un utilisateur
     */
    @GetMapping(value = "/utilisateur/utilisateur/{pseudo}")
    UtilisateurBean login(@PathVariable String pseudo);

    /**
     * Permet la modification des privilège d'un utilisateur
     * @param pseudo pseudo pour appliquer la modification
     * @param privilege privilège à appliquer
     */
    @PutMapping(value = "/utilisateur/utilisateur/{pseudo}/{privilege}")
    public UtilisateurBean modificationPrivilege(@PathVariable String pseudo, @PathVariable String privilege);


}
