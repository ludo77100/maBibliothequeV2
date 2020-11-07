package org.ludo.bibliotheque.entities;

import org.ludo.bibliotheque.Enums.EtatEnums;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.List;

public class Exemplaire implements Serializable {

    //TODO création de l'entité Exemplaire >> FAIT

    private long id ;

    private String etat ;

    private String identifiant ;

    /**
     * Enumération pour les rôles/privilèges
     */
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<EtatEnums> etatEnumsList;

    public Exemplaire(){
        super();
    }

    public Exemplaire(long id, String etat, String identifiant, List<EtatEnums> etatEnumsList) {
        this.id = id;
        this.etat = etat;
        this.identifiant = identifiant;
        this.etatEnumsList = etatEnumsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<EtatEnums> getEtatEnumsList() {
        return etatEnumsList;
    }

    public void setEtatEnumsList(List<EtatEnums> etatEnumsList) {
        this.etatEnumsList = etatEnumsList;
    }

    @Override
    public String toString() {
        return "Exemplaire{" +
                "id=" + id +
                ", etat='" + etat + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", etatEnumsList=" + etatEnumsList +
                '}';
    }
}
