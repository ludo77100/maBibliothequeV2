package org.ludo.bibliotheque.service.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ludo.bibliotheque.BibliothequeApplication;
import org.ludo.bibliotheque.Enums.EtatEnums;
import org.ludo.bibliotheque.Enums.EtatReservationEnums;
import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.dao.ReservationRepository;
import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.entities.Reservation;
import org.ludo.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class LivreServiceImpl implements LivreService {

    private static final Logger logger = LogManager.getLogger(BibliothequeApplication.class);

    @Autowired
    LivreRepository livreRepository ;

    @Autowired
    ReservationRepository reservationRepository ;

    /**
     * Trouve tous les livres
     * @return liste des livres
     */
    @Override
    public List<Livre> findAll() {

        logger.debug("Appel LivreServiceImpl méthode findAll");

        List<Livre> listeLivre = livreRepository.findAll();
        for (Livre e : listeLivre){

            List<Reservation> listeReservation = reservationRepository.findAllByLivreAndEtatReservationEnums(e, EtatReservationEnums.ENCOURS);
            e.setTailleListeReservationEnCours(listeReservation.size());

            Set<Exemplaire> listeExemplaires =  e.getExemplaires();
            Date dateRetour = new Date();
            dateRetour.setDate(2999-12-12);

            for (Exemplaire f : listeExemplaires){
                System.out.println(f.getEtat());
                if (f.getEtat().equals(EtatEnums.EMPRUNTE) && f.getEmprunt().getDateFin().before(dateRetour)){
                    dateRetour = f.getEmprunt().getDateFin();
                    e.setDateRetourPlusProche(dateRetour);
                }
            }
        }

        return listeLivre ;
    }

    /**
     * Trouve les livres contenant dans leur titre un mot clé
     * @param mc mot clé
     * @return la liste des livres
     */
    @Override
    public List<Livre> findByTitreContainingIgnoreCase(String mc) {

        logger.debug("Appel LivreServiceImpl méthode findByTitreContainingIgnoreCase avec paramètre mc : " +mc );

        return livreRepository.findByTitreContainingIgnoreCase(mc);
    }

    /**
     * Trouve un livre par son id
     * @param idLivre id du livre à trouver
     * @return le livre
     */
    @Override
    public Livre findLivreById(Long idLivre) {

        logger.debug("Appel LivreServiceImpl méthode findLivreById avec paramètre idLidvre : " +idLivre );

        return livreRepository.findById(idLivre).get();
    }

    /**
     * Enregistre un nouveau livre
     * @param livre paramètre du livre à enregistrer
     * @return un nouveau livre
     */
    @Override
    public Livre enregistrerNouveauLivre(Livre livre) {

        logger.debug("Appel LivreServiceImpl méthode enregistrerNouveauLivre");

        Livre nouveauLivre = new Livre() ;

        nouveauLivre.setAuteur(livre.getAuteur());
        nouveauLivre.setDecription(livre.getDecription());
        nouveauLivre.setEditeur(livre.getEditeur());
        nouveauLivre.setNombrePages(livre.getNombrePages());
        nouveauLivre.setQuantiteDispo(livre.getQuantiteDispo());
        nouveauLivre.setTitre(livre.getTitre());
        nouveauLivre.setUrlImage(livre.getUrlImage());

        return livreRepository.save(nouveauLivre);
    }

    /**
     * Supprime un livre par son id
     * @param idLivre id du livre à supprimer
     */
    @Override
    public void supprimerLivre(Long idLivre) {

        logger.debug("Appel LivreServiceImpl méthode supprimerLivre avec paramètre idLidvre : " + idLivre );

        livreRepository.deleteById(idLivre);
    }


}
