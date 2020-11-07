package org.ludo.bibliotheque.proxies;

import org.ludo.bibliotheque.beans.UtilisateurBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "utilisateur", url = "localhost:8081")
public interface MicroserviceUtilisateurProxy {

    @GetMapping(value = "/listeUtilisateur")
    List<UtilisateurBean> listeUtilisateur();

    @GetMapping(value = "/utilisateur/{pseudo}")
    UtilisateurBean login(@PathVariable String pseudo);

    @PutMapping(value = "/utilisateur/{pseudo}/{privilege}")
    public UtilisateurBean modificationPrivilege(@PathVariable String pseudo, @PathVariable String privilege);

}
