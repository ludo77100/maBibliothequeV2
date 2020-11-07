package org.ludo.Utilisateur.dto;

public class UtilisateurDto {

    private String pseudo;
    private String motDePass;
    private String email;

    public UtilisateurDto(String pseudo, String motDePass, String email) {
        this.pseudo = pseudo;
        this.motDePass = motDePass;
        this.email = email;
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
    public String toString() {
        return "UtilisateurDto{" +
                "pseudo='" + pseudo + '\'' +
                ", motDePass='" + motDePass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
