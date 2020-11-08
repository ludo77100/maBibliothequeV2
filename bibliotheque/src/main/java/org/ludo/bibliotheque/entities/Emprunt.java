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
@Data
@NoArgsConstructor
public class Emprunt implements Serializable {

    //todo ajouter lombok

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

    @OneToOne(mappedBy = "emprunt")
    private Exemplaire exemplaire ;

}