package org.ludo.bibliotheque.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ludo.bibliotheque.Enums.EtatEnums;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Exemplaire implements Serializable {

    //TODO création de l'entité Exemplaire >> FAIT

    private long id;

    private String etat;

    private String identifiant;

    /**
     * Relation avec la table Livre
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_livre")
    private Livre livre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_emprunt")
    private Emprunt emprunt;


    /**
     * Enumération pour les rôles/privilèges
     */
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<EtatEnums> etatEnumsList;

}