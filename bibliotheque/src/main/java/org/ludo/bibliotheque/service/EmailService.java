package org.ludo.bibliotheque.service;

import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Reservation;

import javax.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(String email, String objet, String contenu) throws MessagingException;

    void envoyerEmailRelance() throws MessagingException;

    void envoyerEmailExemplaireDispo(Exemplaire exemplaire, Reservation reservation) throws MessagingException;


    }
