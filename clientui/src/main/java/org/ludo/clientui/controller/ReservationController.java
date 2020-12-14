package org.ludo.clientui.controller;

import org.ludo.clientui.beans.EmpruntBean;
import org.ludo.clientui.beans.ReservationBean;
import org.ludo.clientui.beans.UtilisateurBean;
import org.ludo.clientui.proxies.MicroserviceBibliothequeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    MicroserviceBibliothequeProxy reservationProxy ;

    @GetMapping(value = "/reservation/user")
    public String listeReservationUtilisateur(Model model, HttpServletRequest request){

        if (request.getRemoteUser() == null) {
            return "connexion";
        } else {

            UtilisateurBean utilDet = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String pseudoEmprunteur = utilDet.getUsername();

            List<ReservationBean> listeReservationUtilisateur = reservationProxy.getAllReservationForUtlisateur(pseudoEmprunteur);
            model.addAttribute("listeReservationUtilisateur", listeReservationUtilisateur);

            return "/listeReservations";
        }
    }

    @GetMapping(value = "/reservation/accepter/{idReservation}")
    public String acceptReservation(@PathVariable long idReservation, HttpServletRequest request) {

        if (request.getRemoteUser() == null) {
            return "connexion";
        } else {

            reservationProxy.acceptReservation(idReservation);

            return "redirect:/listeReservations";
        }
    }
}
