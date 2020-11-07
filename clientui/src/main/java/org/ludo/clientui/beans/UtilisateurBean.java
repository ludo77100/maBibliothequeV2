package org.ludo.clientui.beans;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Bean pour entity Utilisateur
 */
public class UtilisateurBean implements UserDetails {

    /**
     * id de l'utilisateur
     */
    private Long idUtilisateur ;

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
     * Instanciation de l'utilisateur
     * @param idUtilisateur id de l'utilisateur
     * @param pseudo pseudo de l'utilisateur
     * @param motDePass mot de pass de l'utilisateur
     * @param email email de l'utilisateur
     */
    public UtilisateurBean(Long idUtilisateur, String pseudo, String motDePass, String email) {
        this.idUtilisateur = idUtilisateur;
        this.pseudo = pseudo;
        this.motDePass = motDePass;
        this.email = email;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return motDePass;
    }

    @Override
    public String getUsername() {
        return pseudo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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