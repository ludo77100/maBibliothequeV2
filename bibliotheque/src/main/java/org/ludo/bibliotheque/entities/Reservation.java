package org.ludo.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReservation ;

    private Date dateDemandeReservation ;

    private String pseudoDemandeur ;

    @JsonManagedReference
    @ManyToOne
    private Livre livre;

    public Reservation() {
        super();
    }

    public Reservation(long idReservation, Date dateDemandeReservation, String pseudoDemandeur, Livre livre) {
        this.idReservation = idReservation;
        this.dateDemandeReservation = dateDemandeReservation;
        this.pseudoDemandeur = pseudoDemandeur;
        this.livre = livre;
    }

    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDateDemandeReservation() {
        return dateDemandeReservation;
    }

    public void setDateDemandeReservation(Date dateDemandeReservation) {
        this.dateDemandeReservation = dateDemandeReservation;
    }

    public String getPseudoDemandeur() {
        return pseudoDemandeur;
    }

    public void setPseudoDemandeur(String pseudoDemandeur) {
        this.pseudoDemandeur = pseudoDemandeur;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", dateDemandeReservation=" + dateDemandeReservation +
                ", pseudoDemandeur='" + pseudoDemandeur + '\'' +
                ", livre=" + livre +
                '}';
    }
}
