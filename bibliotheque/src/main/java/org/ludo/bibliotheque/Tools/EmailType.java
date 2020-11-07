package org.ludo.bibliotheque.Tools;

public class EmailType {

    String email;
    String titre;
    String dateFinPret;
    String nomUtilisateur;

    public EmailType() { super();
    }

    public EmailType(String email, String titre, String dateFinPret, String nomUtilisateur) {
        this.email = email;
        this.titre = titre;
        this.dateFinPret = dateFinPret;
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateFinPret() {
        return dateFinPret;
    }

    public void setDateFinPret(String dateFinPret) {
        this.dateFinPret = dateFinPret;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }
}
