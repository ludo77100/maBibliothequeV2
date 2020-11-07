package org.ludo.clientui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.clientui.ClientuiApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConnexionController {

    private static final Logger logger = LogManager.getLogger(ClientuiApplication.class);

    @GetMapping(value = "/connexion")
    public String login(){

        logger.debug("Appel ConnexionController méthode login");

        return "connexion";
    }

}
