package org.ludo.clientui.beans;

import java.util.Date;

/**
 * Bean pour emprunt
 */
public class EmpruntBean {

    /**
     * id de emprunt
     */
    private Long idEmprunt;

    /**
     * Pseudo de l'emprunteur
     */
    private String pseudoEmprunteur ;

    /**
     * Date du jour
     */
    private Date dateDebut;

    /**
     * Date du jour + 4 semaines
     */
    private Date dateFin;

    /**
     * Emprunt prolongeable ou non
     */
    private boolean prolongeable;

    /**
     * Emprunt en cours ou non
     */
    private boolean enCours ;

    /**
     * Relation avec la table Livre
     */
    private LivreBean livre ;

    /**
     * instanciation de emprunt
     * @param idEmprunt id emprunt
     * @param pseudoEmprunteur pseudo de l'emprunteur
     * @param dateDebut date de début de l'emprunt
     * @param dateFin date de fin de l'emprunt
     * @param prolongeable prolongeable ou non
     * @param enCours en cours ou non
     * @param livre relation avec la table livre
     */
    public EmpruntBean(Long idEmprunt, String pseudoEmprunteur, Date dateDebut, Date dateFin, boolean prolongeable, boolean enCours, LivreBean livre) {
        this.idEmprunt = idEmprunt;
        this.pseudoEmprunteur = pseudoEmprunteur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prolongeable = prolongeable;
        this.enCours = enCours;
        this.livre = livre;
    }

    public Long getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Long idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public String getPseudoEmprunteur() {
        return pseudoEmprunteur;
    }

    public void setPseudoEmprunteur(String pseudoEmprunteur) {
        this.pseudoEmprunteur = pseudoEmprunteur;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isProlongeable() {
        return prolongeable;
    }

    public void setProlongeable(boolean prolongeable) {
        this.prolongeable = prolongeable;
    }

    public boolean isEnCours() {
        return enCours;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    public LivreBean getLivre() {
        return livre;
    }

    public void setLivre(LivreBean livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "EmpruntBean{" +
                "idEmprunt=" + idEmprunt +
                ", pseudoEmprunteur='" + pseudoEmprunteur + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prolongeable=" + prolongeable +
                ", enCours=" + enCours +
                ", livre=" + livre +
                '}';
    }
}
