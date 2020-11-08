package org.ludo.bibliotheque.entities;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * Entity livre pour le microservice bibliotheque
 */
@Entity
public class Livre implements Serializable {

    /**
     * id de livre
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLivre ;

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
    @Range(min = 1, message = "Le nombre de pages ne peut être inférieur à 1")
    private int nombrePages ;

    /**
     * Quantité disponible du livre pour emprunt
     */
    @Range(min = 0, message = "La quantité disponible ne peut être inférieur à zéro")
    private int quantiteDispo ;


    /**
     * Quantité d'exemplaire possédé par la bibliothèque
     */
    @Range(min = 0, message = "Le nombre d'exemplaire possédés ne peut être inférieur à zéro")
    private int nombreExemplaires ;

    /**
     * Url de l'image du livre
     */
    private String urlImage ;

    /**
     * Relation avec la table emprunt
     */
    @JsonIgnore
    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    private Set<Exemplaire> exemplaires;

    /**
     * Instanciation de livre
     */
    public Livre() {
        super();
    }

    /**
     * Instanciation de livre
     * @param idLivre id de livre
     * @param titre titre du livre
     * @param auteur auteur du livre
     * @param editeur editeur du livre
     * @param decription description du livre
     * @param nombrePages nombre de pages du livre
     * @param quantiteDispo Quantité disponible du livre pour emprunt
     * @param exemplaires avec la table emprunt
     * @param urlImage Url de l'image du livre
     */
    public Livre(Long idLivre, String titre, String auteur, String editeur, String decription, int nombrePages, int quantiteDispo, int nombreExemplaires, Set<Exemplaire> exemplaires, String urlImage) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.decription = decription;
        this.nombrePages = nombrePages;
        this.quantiteDispo = quantiteDispo;
        this.exemplaires = exemplaires;
        this.urlImage = urlImage;
        this.nombreExemplaires = nombreExemplaires;
    }

    public Long getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Long idLivre) {
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

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    public Set<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "idLivre=" + idLivre +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", editeur='" + editeur + '\'' +
                ", decription='" + decription + '\'' +
                ", nombrePages=" + nombrePages +
                ", quantiteDispo=" + quantiteDispo +
                ", nombreExemplaires=" + nombreExemplaires +
                ", urlImage='" + urlImage + '\'' +
                ", emprunt=" + exemplaires +
                '}';
    }
}
