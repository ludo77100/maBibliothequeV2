package org.ludo.bibliotheque.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.ludo.bibliotheque.Enums.EtatEnums;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Exemplaire implements Serializable {

    //TODO création de l'entité Exemplaire >> FAIT

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idExemplaire;

    private EtatEnums etat; //TODO voir enums avec deyine

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

    public Exemplaire(){
        super();
    }

    public Exemplaire(long idExemplaire, EtatEnums etat, String identifiant, Livre livre, Emprunt emprunt, List<EtatEnums> etatEnumsList) {
        this.idExemplaire = idExemplaire;
        this.etat = etat;
        this.identifiant = identifiant;
        this.livre = livre;
        this.emprunt = emprunt;
        this.etatEnumsList = etatEnumsList;
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

    public List<EtatEnums> getEtatEnumsList() {
        return etatEnumsList;
    }

    public void setEtatEnumsList(List<EtatEnums> etatEnumsList) {
        this.etatEnumsList = etatEnumsList;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "idExemplaire=" + idExemplaire +
                ", etat=" + etat +
                ", identifiant='" + identifiant + '\'' +
                ", livre=" + livre +
                ", emprunt=" + emprunt +
                ", etatEnumsList=" + etatEnumsList +
                '}';
    }
}