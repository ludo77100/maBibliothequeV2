package org.ludo.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.ludo.bibliotheque.Enums.EtatReservationEnums;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReservation;

    private Date dateDemandeReservation;

    private String pseudoDemandeur;

    private EtatReservationEnums etatReservationEnums;

    @Transient
    private int positionFileAttente ;

    @Transient
    private Date dateDisponibiliteEstimee ;

    private Date dateCloture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idExemplaire")
    private Exemplaire exemplaire;

    @JsonManagedReference
    @ManyToOne
    private Livre livre;

    public Reservation() {
        super();
    }

    public Reservation(long idReservation, Date dateDemandeReservation, String pseudoDemandeur, EtatReservationEnums etatReservationEnums, int positionFileAttente, Date dateDisponibiliteEstimee, Date dateCloture, Exemplaire exemplaire, Livre livre) {
        this.idReservation = idReservation;
        this.dateDemandeReservation = dateDemandeReservation;
        this.pseudoDemandeur = pseudoDemandeur;
        this.etatReservationEnums = etatReservationEnums;
        this.positionFileAttente = positionFileAttente;
        this.dateDisponibiliteEstimee = dateDisponibiliteEstimee;
        this.dateCloture = dateCloture;
        this.exemplaire = exemplaire;
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

    public EtatReservationEnums getEtatReservationEnums() {
        return etatReservationEnums;
    }

    public void setEtatReservationEnums(EtatReservationEnums etatReservationEnums) {
        this.etatReservationEnums = etatReservationEnums;
    }

    public int getPositionFileAttente() {
        return positionFileAttente;
    }

    public void setPositionFileAttente(int positionFileAttente) {
        this.positionFileAttente = positionFileAttente;
    }

    public Date getDateDisponibiliteEstimee() {
        return dateDisponibiliteEstimee;
    }

    public void setDateDisponibiliteEstimee(Date dateDisponibiliteEstimee) {
        this.dateDisponibiliteEstimee = dateDisponibiliteEstimee;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
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
                ", etatReservationEnums=" + etatReservationEnums +
                ", positionFileAttente=" + positionFileAttente +
                ", dateDisponibiliteEstimee=" + dateDisponibiliteEstimee +
                ", dateCloture=" + dateCloture +
                ", exemplaire=" + exemplaire +
                ", livre=" + livre +
                '}';
    }
}