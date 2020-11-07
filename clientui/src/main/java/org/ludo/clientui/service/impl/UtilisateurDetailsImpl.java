package org.ludo.clientui.service.impl;


import org.ludo.clientui.beans.UtilisateurBean;
import org.ludo.clientui.proxies.MicroserviceUtilisateurProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurDetailsImpl implements UserDetailsService {

    @Autowired
    MicroserviceUtilisateurProxy utilisateurProxy ;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        UtilisateurBean utilisateur = utilisateurProxy.login(pseudo);

        if (utilisateur != null){
            return utilisateur;
        }else{
            throw new UsernameNotFoundException(String.format("Utilisateur[%s] non trouvé"));
        }
    }
}