package org.ludo.Utilisateur.entities;

import org.ludo.Utilisateur.emums.RoleEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity Utilisateur du microservice utilisateur
 */
@Entity
public class Utilisateur {

    /**
     * id de l'utilisateur
     */
    @Id
    @GeneratedValue
    private int idUtilisateur ;

    /**
     * pseudo de l'utilisateur
     */
    private String pseudo;

    /**
     * mot de passe de l'utilisateur
     */
    private String motDePass;

    /**
     * email de l'utilisateur
     */
    private String email;

    /**
     * Enumération pour les rôles/privilèges
     */
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<RoleEnum> userRoleList;

    /**
     * Instanciation de l'utilisateur
     */
    public Utilisateur() {
        super();
    }

    /**
     * Instanciation de l'utilisateur
     * @param idUtilisateur id de l'utilisateur
     * @param pseudo pseudo de l'utilisateur
     * @param motDePass mot de pass de l'utilisateur
     * @param email email de l'utilisateur
     */
    public Utilisateur(int idUtilisateur, String pseudo, String motDePass, String email) {
        this.idUtilisateur = idUtilisateur ;
        this.pseudo = pseudo ;
        this.motDePass = motDePass ;
        this.email = email ;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void grantAuthority(RoleEnum authority) {
        if (userRoleList == null) userRoleList = new ArrayList<>();
        userRoleList.add(authority);
    }

    public List<RoleEnum> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<RoleEnum> userRoleList) {
        this.userRoleList = userRoleList;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", motDePass='" + motDePass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
