package org.ludo.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity Emprunt pour le microservice biblioteque
 */
@Entity
public class Emprunt implements Serializable {

    /**
     * id de emprunt
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_exemplaire")
    private Exemplaire exemplaire ;

    public Emprunt() {
        super();
    }

    public Emprunt(Long idEmprunt, String pseudoEmprunteur, Date dateDebut, Date dateFin, boolean prolongeable, boolean enCours, Exemplaire exemplaire) {
        this.idEmprunt = idEmprunt;
        this.pseudoEmprunteur = pseudoEmprunteur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prolongeable = prolongeable;
        this.enCours = enCours;
        this.exemplaire = exemplaire;
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

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "idEmprunt=" + idEmprunt +
                ", pseudoEmprunteur='" + pseudoEmprunteur + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prolongeable=" + prolongeable +
                ", enCours=" + enCours +

                '}';
    }
}