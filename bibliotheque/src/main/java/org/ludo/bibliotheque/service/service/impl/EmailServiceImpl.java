package org.ludo.bibliotheque.service.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.bibliotheque.BibliothequeApplication;
import org.ludo.bibliotheque.beans.UtilisateurBean;
import org.ludo.bibliotheque.dao.EmailRepository;
import org.ludo.bibliotheque.entities.Email;
import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.proxies.MicroserviceUtilisateurProxy;
import org.ludo.bibliotheque.service.EmailService;
import org.ludo.bibliotheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LogManager.getLogger(BibliothequeApplication.class);

    @Autowired
    private JavaMailSenderImpl sender;
    
    @Autowired
    private EmpruntService empruntService ;

    @Autowired
    MicroserviceUtilisateurProxy microserviceUtilisateurProxy ;

    @Autowired
    EmailRepository emailRepository ;

    /**
     * Envoi un email
     * @param email adresse email
     * @param objet objet de l'email
     * @param contenu contenu de l'email
     */
    @Override
    public void sendSimpleMessage(String email, String objet, String contenu) throws MessagingException {

        logger.debug("Appel EmailServiceImpl méthode sendSimpleMessage à l'adresse : " + email);

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setSubject(objet);
        helper.setText(contenu);

        sender.send(message);
    }

    /**
     * Permet la composition d'un message email de relance
     */
    @Override
    public void envoyerEmailRelance() throws MessagingException {

        Email email = emailRepository.findByNom("relance");
        List<Emprunt> listeEmpruntNonRendue = empruntService.listeLivreNonRendueApresDateFin();

        for (Emprunt e: listeEmpruntNonRendue) {



            Date datefin = e.getDateFin();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sdf.format(datefin);

            UtilisateurBean utilisateur = microserviceUtilisateurProxy.login(e.getPseudoEmprunteur());

            logger.debug("Appel EmailServiceImpl méthode envoyerEmailRelance à l'adresse : " + utilisateur.getEmail() + " pour le livre : " +e.getLivre().getTitre() + " pour l'emprunt id : " + e.getIdEmprunt());

            String text = email.getContenu()
                    .replace("[NOMUTILISATEUR]", e.getPseudoEmprunteur())
                    .replace("[TITRELIVRE]", e.getLivre().getTitre())
                    .replace("[DATEFIN]", strDate);

            sendSimpleMessage(utilisateur.getEmail(), email.getObjet(), text);
        }
    }
}
