package org.ludo.bibliotheque.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(String email, String objet, String contenu) throws MessagingException;

    void envoyerEmailRelance() throws MessagingException;

}
