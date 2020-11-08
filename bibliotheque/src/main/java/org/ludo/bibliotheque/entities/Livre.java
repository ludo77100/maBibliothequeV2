package org.ludo.bibliotheque.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
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
    @Range(min = 0, message = "La quantité disponible ne peut être inférieur à zéro")
    private int quantiteDispo;


    /**
     * Quantité d'exemplaire possédé par la bibliothèque
     */
    @Range(min = 0, message = "Le nombre d'exemplaire possédés ne peut être inférieur à zéro")
    private int nombreExemplaires;

    /**
     * Url de l'image du livre
     */
    private String urlImage;

    /**
     * Relation avec la table emprunt
     */
    @JsonIgnore
    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    private Set<Exemplaire> exemplaires;

}