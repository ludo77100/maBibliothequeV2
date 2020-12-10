package org.ludo.clientui.beans;

import java.util.Date;

public class ReservationBean {

    private long idReservation;

    private Date dateDemandeReservation;

    private String pseudoDemandeur;

    private String etatReservationEnums;

    private Date dateCloture;

    private ExemplaireBean exemplaire;

    private LivreBean livre;

    public ReservationBean(long idReservation, Date dateDemandeReservation, String pseudoDemandeur, String etatReservationEnums, Date dateCloture, ExemplaireBean exemplaire, LivreBean livre) {
        this.idReservation = idReservation;
        this.dateDemandeReservation = dateDemandeReservation;
        this.pseudoDemandeur = pseudoDemandeur;
        this.etatReservationEnums = etatReservationEnums;
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

    public String getEtatReservationEnums() {
        return etatReservationEnums;
    }

    public void setEtatReservationEnums(String etatReservationEnums) {
        this.etatReservationEnums = etatReservationEnums;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public ExemplaireBean getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(ExemplaireBean exemplaire) {
        this.exemplaire = exemplaire;
    }

    public LivreBean getLivre() {
        return livre;
    }

    public void setLivre(LivreBean livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "ReservationBean{" +
                "idReservation=" + idReservation +
                ", dateDemandeReservation=" + dateDemandeReservation +
                ", pseudoDemandeur='" + pseudoDemandeur + '\'' +
                ", etatReservationEnums='" + etatReservationEnums + '\'' +
                ", dateCloture=" + dateCloture +
                ", exemplaire=" + exemplaire +
                ", livre=" + livre +
                '}';
    }
}
