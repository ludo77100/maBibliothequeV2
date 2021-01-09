package org.ludo.bibliotheque.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
    private Long idLivre;

    /**
     * Titre du livre
     */
    private String titre;

    /**
     * auteur du livre
     */
    private String auteur;

    /**
     * editeur du livre
     */
    private String editeur;

    /**
     * description du livre
     */
    private String decription;

    /**
     * Nombre de pages du livre
     */
    @Range(min = 1, message = "Le nombre de pages ne peut être inférieur à 1")
    private int nombrePages;

    /**
     * Quantité disponible du livre pour emprunt
     */
    private int quantiteDispo;

    /**
     * Url de l'image du livre
     */
    private String urlImage;

    @Transient
    private Date dateRetourPlusProche ;

    @Transient
    private int tailleListeReservationEnCours ;

    /**
     * Relation avec la table emprunt
     */
    @JsonIgnore
    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    private Set<Exemplaire> exemplaires;

    @JsonIgnore
    @OneToMany(mappedBy = "livre")
    private Set<Reservation> reservations;

    public Livre() {
        super();
    }

    public Livre(Long idLivre, String titre, String auteur, String editeur, String decription, @Range(min = 1, message = "Le nombre de pages ne peut être inférieur à 1") int nombrePages, @Range(min = 0, message = "La quantité disponible ne peut être inférieur à zéro") int quantiteDispo, String urlImage, Set<Exemplaire> exemplaires, Set<Reservation> reservations) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.decription = decription;
        this.nombrePages = nombrePages;
        this.quantiteDispo = quantiteDispo;
        this.urlImage = urlImage;
        this.exemplaires = exemplaires;
        this.reservations = reservations;
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Set<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Date getDateRetourPlusProche() {
        return dateRetourPlusProche;
    }

    public void setDateRetourPlusProche(Date dateRetourPlusProche) {
        this.dateRetourPlusProche = dateRetourPlusProche;
    }

    public int getTailleListeReservationEnCours() {
        return tailleListeReservationEnCours;
    }

    public void setTailleListeReservationEnCours(int tailleListeReservationEnCours) {
        this.tailleListeReservationEnCours = tailleListeReservationEnCours;
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
                ", urlImage='" + urlImage + '\'' +
                ", dateRetourPlusProche=" + dateRetourPlusProche +
                ", tailleListeReservation=" + tailleListeReservationEnCours +
                '}';
    }
}