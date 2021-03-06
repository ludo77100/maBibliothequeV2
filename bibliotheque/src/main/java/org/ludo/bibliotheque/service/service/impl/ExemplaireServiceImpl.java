package org.ludo.bibliotheque.service.service.impl;

import org.ludo.bibliotheque.Enums.EtatEnums;
import org.ludo.bibliotheque.dao.ExemplaireRepository;
import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExemplaireServiceImpl implements ExemplaireService {

    @Autowired
    ExemplaireRepository exemplaireRepository ;

    @Autowired
    LivreRepository livreRepository ;

    @Override
    public Exemplaire ajouterExemplaire(String titreLivre) {

        Livre livreEnCours = livreRepository.findByTitre(titreLivre);
        Exemplaire enregistrementExemplaire = new Exemplaire();

        enregistrementExemplaire.setEtat(EtatEnums.DISPONIBLE);
        enregistrementExemplaire.setIdentifiant(this.compositionIdentifiant(livreEnCours));
        enregistrementExemplaire.setLivre(livreEnCours);

        livreEnCours.setQuantiteDispo(livreEnCours.getQuantiteDispo()+1);
        livreRepository.save(livreEnCours);

        return exemplaireRepository.save(enregistrementExemplaire);
    }

    @Override
    public List<Exemplaire> getAllExemplaire() {
        return exemplaireRepository.findAll();
    }

    @Override
    public List<Exemplaire> getAllExemplaireForLivre(String titreLivre) {
        return exemplaireRepository.findAllByLivre_titre(titreLivre);
    }

    @Override
    public void deleteById(Long idExemplaire) {
        exemplaireRepository.deleteById(idExemplaire);
    }

    @Override
    public Exemplaire findByIdentifiant(String identifiant) {
        return exemplaireRepository.findByIdentifiant(identifiant);
    }

    @Override
    public List<Exemplaire> findAllByTitre(String titre) {
        return exemplaireRepository.findAllByLivre_titre(titre);
    }

    public Exemplaire changerEtatExemplaire(String identifiantExemplaire, String nouvelEtat){
        Exemplaire exemplaire = exemplaireRepository.findByIdentifiant(identifiantExemplaire) ;
        switch (nouvelEtat){
            case "DISPONIBLE":
                exemplaire.setEtat(EtatEnums.DISPONIBLE);
                break;
            case "INDISPONIBLE":
                exemplaire.setEtat(EtatEnums.INDISPONIBLE);
                break;
            case "ATTENTE":
                exemplaire.setEtat(EtatEnums.ATTENTE);
                break;
            case "EMPRUNTE":
                exemplaire.setEtat(EtatEnums.EMPRUNTE);
                break;
            case "RESERVE":
                exemplaire.setEtat(EtatEnums.RESERVE);
                break;
        }

        return exemplaireRepository.save(exemplaire);
    }

    /**
     * Méthode pour composer un identifiant pour un exemplaire
     * Un identifiant se compose de la première lettre du titre, puis auteur, puis editeur, puis un slash et pour finir un nombre qui est le nombre d'element dans le set pour le livre
     * @param livre le livre pour lequel il faut composer la référence
     * @return l'identifiant en String
     */
    public String compositionIdentifiant(Livre livre){
        String newRef ;
        List<Exemplaire> exemplaires = livre.getExemplaires();
        int sizeSet = exemplaires.size() + 1 ;
        newRef = livre.getIdLivre() + livre.getTitre().substring(0,1) + livre.getAuteur().substring(0,1) + livre.getEditeur().substring(0,1) + sizeSet;
        return newRef ;
    }
}
