package org.ludo.clientui.beans;

public class ExemplaireBean {

    private long idExemplaire;

    private String etat;

    private String identifiant;

    private LivreBean livre;

    private EmpruntBean emprunt;

    public ExemplaireBean(long idExemplaire, String etat, String identifiant, LivreBean livre, EmpruntBean emprunt) {
        this.idExemplaire = idExemplaire;
        this.etat = etat;
        this.identifiant = identifiant;
        this.livre = livre;
        this.emprunt = emprunt;
    }

    public long getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(long idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public LivreBean getLivre() {
        return livre;
    }

    public void setLivre(LivreBean livre) {
        this.livre = livre;
    }

    public EmpruntBean getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(EmpruntBean emprunt) {
        this.emprunt = emprunt;
    }

    @Override
    public String toString() {
        return "ExemplaireBean{" +
                "idExemplaire=" + idExemplaire +
                ", etat='" + etat + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", livre=" + livre +
                ", emprunt=" + emprunt +
                '}';
    }
}
