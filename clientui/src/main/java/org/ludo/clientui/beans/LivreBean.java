package org.ludo.clientui.beans;

import java.util.Collection;

/**
 * Bean pour entity livre
 */
public class LivreBean {

    /**
     * id de livre
     */
    private int idLivre ;

    /**
     * Titre du livre
     */
    private String titre ;

    /**
     * auteur du livre
     */
    private String auteur ;

    /**
     * editeur du livre
     */
    private String editeur ;

    /**
     * description du livre
     */
    private String decription ;

    /**
     * Nombre de pages du livre
     */
    private int nombrePages ;

    /**
     * Quantité disponible du livre pour emprunt
     */
    private int quantiteDispo ;

    /**
     * Url de l'image du livre
     */
    private String urlImage ;

    /**
     * Relation avec la table emprunt
     */
    private Collection<EmpruntBean> emprunt;

    /**
     * Instanciation de livre
     * @param idLivre id de livre
     * @param titre titre du livre
     * @param auteur auteur du livre
     * @param editeur editeur du livre
     * @param decription description du livre
     * @param nombrePages nombre de pages du livre
     * @param quantiteDispo Quantité disponible du livre pour emprunt
     * @param emprunt Relation avec la table emprunt
     * @param urlImage Url de l'image du livre
     */
    public LivreBean(int idLivre, String titre, String auteur, String editeur, String decription, int nombrePages, int quantiteDispo, Collection<EmpruntBean> emprunt, String urlImage) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.decription = decription;
        this.nombrePages = nombrePages;
        this.quantiteDispo = quantiteDispo;
        this.emprunt = emprunt;
        this.urlImage = urlImage;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public int getNombrePages() {
        return nombrePages;
    }

    public void setNombrePages(int nombrePages) {
        this.nombrePages = nombrePages;
    }

    public int getQuantiteDispo() {
        return quantiteDispo;
    }

    public void setQuantiteDispo(int quantiteDispo) {
        this.quantiteDispo = quantiteDispo;
    }

    public Collection<EmpruntBean> getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Collection<EmpruntBean> emprunt) {
        this.emprunt = emprunt;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "LivreBean{" +
                "idLivre=" + idLivre +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", editeur='" + editeur + '\'' +
                ", decription='" + decription + '\'' +
                ", nombrePages=" + nombrePages +
                ", quantiteDispo=" + quantiteDispo +
                ", urlImage='" + urlImage + '\'' +
                ", emprunt=" + emprunt +
                '}';
    }
}
