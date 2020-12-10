package org.ludo.bibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ludo.bibliotheque.Enums.EtatEnums;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Exemplaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idExemplaire;

    private EtatEnums etat;

    private String identifiant;

    /**
     * Relation avec la table Livre
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_livre")
    private Livre livre;

    @JsonIgnore
    @OneToOne(mappedBy = "exemplaire", fetch = FetchType.EAGER)
    private Emprunt emprunt;


    public Exemplaire(){
        super();
    }

    public Exemplaire(long idExemplaire, EtatEnums etat, String identifiant, Livre livre, Emprunt emprunt) {
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

    public EtatEnums getEtat() {
        return etat;
    }

    public void setEtat(EtatEnums etat) {
        this.etat = etat;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Emprunt getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Emprunt emprunt) {
        this.emprunt = emprunt;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "idExemplaire=" + idExemplaire +
                ", etat=" + etat +
                ", identifiant='" + identifiant + '\'' +
                ", livre=" + livre +
                ", emprunt=" + emprunt +
                '}';
    }
}