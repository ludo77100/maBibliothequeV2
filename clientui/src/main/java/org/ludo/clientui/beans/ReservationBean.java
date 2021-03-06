package org.ludo.clientui.beans;

import java.util.Date;

public class ReservationBean {

    private long idReservation;

    private Date dateDemandeReservation;

    private String pseudoDemandeur;

    private String etatReservationEnums;

    private int positionFileAttente ;

    private Date dateDisponibiliteEstimee ;

    private Date dateCloture;

    private ExemplaireBean exemplaire;

    private LivreBean livre;

    public ReservationBean() {
        super();
    }

    public ReservationBean(long idReservation, Date dateDemandeReservation, String pseudoDemandeur, String etatReservationEnums, int positionFileAttente, Date dateDisponibiliteEstimee,Date dateCloture, ExemplaireBean exemplaire, LivreBean livre) {
        this.idReservation = idReservation;
        this.dateDemandeReservation = dateDemandeReservation;
        this.pseudoDemandeur = pseudoDemandeur;
        this.etatReservationEnums = etatReservationEnums;
        this.positionFileAttente = positionFileAttente;
        this.dateDisponibiliteEstimee = dateDisponibiliteEstimee ;
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
                ", positionFileAttente=" + positionFileAttente +
                ", dateDisponibiliteEstimee=" + dateDisponibiliteEstimee +
                ", dateCloture=" + dateCloture +
                ", exemplaire=" + exemplaire +
                ", livre=" + livre +
                '}';
    }
}
