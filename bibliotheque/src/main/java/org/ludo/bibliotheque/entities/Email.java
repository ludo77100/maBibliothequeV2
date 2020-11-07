package org.ludo.bibliotheque.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity Email pour le microservice bibliotheque
 */
@Entity
public class Email {

    /**
     * id de email
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idMail ;

    /**
     * Nom de email
     */
    private String nom ;

    /**
     * Objet de email
     */
    private String objet ;

    /**
     * contenu de email
     */
    private String contenu ;

    /**
     * instanciation de email
     */
    public Email() {
    }

    /**
     * Instanciation de email
     * @param idMail id email
     * @param nom nom email
     * @param objet objet email
     * @param contenu contenu email
     */
    public Email(Long idMail, String nom, String objet, String contenu) {
        this.idMail = idMail;
        this.nom = nom;
        this.objet = objet;
        this.contenu = contenu;
    }

    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "email{" +
                "idMail=" + idMail +
                ", nom='" + nom + '\'' +
                ", objet='" + objet + '\'' +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}
