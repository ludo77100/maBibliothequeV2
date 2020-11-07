package org.ludo.bibliotheque.beans;

public class UtilisateurBean {

    private Long idUtilisateur ;
    private String pseudo;
    private String motDePass;
    private String email;

    public UtilisateurBean(){
        super();
    }

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
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", motDePass='" + motDePass + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}