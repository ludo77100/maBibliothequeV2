package org.ludo.bibliotheque.BibliothequeTestUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ludo.bibliotheque.Enums.EtatEnums;
import org.ludo.bibliotheque.dao.EmpruntRepository;
import org.ludo.bibliotheque.dao.ExemplaireRepository;
import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.entities.Emprunt;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.service.service.impl.EmpruntServiceImpl;
import org.ludo.bibliotheque.service.service.impl.ExemplaireServiceImpl;
import org.ludo.bibliotheque.service.service.impl.LivreServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class EmpruntServiceUnitTest {

    @Mock
    private ExemplaireRepository exemplaireRepository;

    @Mock
    private LivreRepository livreRepository ;

    @Mock
    private EmpruntRepository empruntRepository ;

    @Autowired
    @InjectMocks
    private LivreServiceImpl livreService ;

    @Autowired
    @InjectMocks
    private ExemplaireServiceImpl exemplaireService;

    @Autowired
    @InjectMocks
    private EmpruntServiceImpl empruntService ;

    private List<Emprunt> emprunts = new ArrayList<>();
    private List<Livre> livres = new ArrayList<>();
    private List<Exemplaire> exemplaireSet1 = new ArrayList<>();
    private List<Exemplaire> exemplaireSet2 = new ArrayList<>();

    public Date date1 = new Date();

    public Date ajouter4Semaines(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 28);
        return calendar.getTime();
    }

    @Before
    public void setUp(){

        Date date = new Date();

        Livre livre1 = new Livre();
        livre1.setIdLivre(1L);
        livre1.setTitre("1a");
        livre1.setAuteur("1b");
        livre1.setEditeur("1c");
        livre1.setDecription("1d");
        livre1.setNombrePages(10);
        livre1.setUrlImage("1e");
        livre1.setQuantiteDispo(1);
        livre1.setDateRetourPlusProche(date);
        livre1.setTailleListeReservationEnCours(0);
        livres.add(livre1);

        Livre livre2 = new Livre();
        livre2.setIdLivre(2L);
        livre2.setTitre("2a");
        livre2.setAuteur("2b");
        livre2.setEditeur("2c");
        livre2.setDecription("2d");
        livre2.setNombrePages(10);
        livre2.setUrlImage("2e");
        livre2.setQuantiteDispo(1);
        livre2.setDateRetourPlusProche(date);
        livre2.setTailleListeReservationEnCours(0);
        livres.add(livre2);

        Exemplaire exemplaire1 = new Exemplaire();
        exemplaire1.setEtat(EtatEnums.DISPONIBLE);
        exemplaire1.setIdentifiant("3Lid");
        exemplaire1.setIdExemplaire(3L);
        exemplaireSet1.add(exemplaire1);

        Exemplaire exemplaire2 = new Exemplaire();
        exemplaire2.setEtat(EtatEnums.EMPRUNTE);
        exemplaire2.setIdentifiant("4Lid");
        exemplaire2.setIdExemplaire(4L);
        exemplaireSet2.add(exemplaire2);

        Exemplaire exemplaire3 = new Exemplaire();
        exemplaire3.setEtat(EtatEnums.DISPONIBLE);
        exemplaire3.setIdentifiant("5Lid");
        exemplaire3.setIdExemplaire(5L);
        exemplaireSet1.add(exemplaire3);

        Exemplaire exemplaire4 = new Exemplaire();
        exemplaire4.setEtat(EtatEnums.EMPRUNTE);
        exemplaire4.setIdentifiant("6Lid");
        exemplaire4.setIdExemplaire(6L);
        exemplaireSet2.add(exemplaire4);

        livre1.setExemplaires(exemplaireSet1);
        livre2.setExemplaires(exemplaireSet2);

        Emprunt emprunt1 = new Emprunt();
        emprunt1.setIdEmprunt(7L);
        emprunt1.setPseudoEmprunteur("ludo");
        emprunt1.setDateDebut(date1);
        emprunt1.setDateFin(this.ajouter4Semaines(date1));
        emprunt1.setEnCours(true);
        emprunt1.setProlongeable(true);
        emprunt1.setExemplaire(exemplaire1);
        emprunts.add(emprunt1);

        Emprunt emprunt2 = new Emprunt();
        emprunt2.setIdEmprunt(8L);
        emprunt2.setPseudoEmprunteur("aurelie");
        emprunt2.setDateDebut(date1);
        emprunt2.setDateFin(this.ajouter4Semaines(date1));
        emprunt2.setEnCours(true);
        emprunt2.setProlongeable(true);
        emprunt2.setExemplaire(exemplaire3);
        emprunts.add(emprunt2);

        Emprunt emprunt3 = new Emprunt();
        emprunt3.setIdEmprunt(9L);
        emprunt3.setPseudoEmprunteur("ludo");
        emprunt3.setDateDebut(date1);
        emprunt3.setDateFin(this.ajouter4Semaines(date1));
        emprunt3.setEnCours(true);
        emprunt3.setProlongeable(true);
        emprunt3.setExemplaire(exemplaire3);
        emprunts.add(emprunt3);

        Emprunt emprunt4 = new Emprunt();
        emprunt4.setIdEmprunt(9L);
        emprunt4.setPseudoEmprunteur("aurelie");
        emprunt4.setDateDebut(date1);
        emprunt4.setDateFin(this.ajouter4Semaines(date1));
        emprunt4.setEnCours(true);
        emprunt4.setProlongeable(true);
        emprunt4.setExemplaire(exemplaire3);
        emprunts.add(emprunt4);


        Mockito.when(livreRepository.findById(1L)).thenReturn(Optional.of(livre1));

        Mockito.when(exemplaireRepository.findByIdentifiant("6Lid")).thenReturn(exemplaire4);

        Mockito.when(exemplaireRepository.findAllByLivre_titre("1a")).thenReturn(livre1.getExemplaires());
        Mockito.when(exemplaireRepository.findAllByLivre_titre("2a")).thenReturn(livre2.getExemplaires());

        Mockito.when(empruntRepository.findById(8L)).thenReturn(Optional.of(emprunt1));
        Mockito.when(empruntRepository.findAllByPseudoEmprunteurAndEnCoursIsTrue("ludo")).thenReturn(emprunts);
    }

    @Test
    public void findEmpruntById_test(){
        Emprunt empruntTest = empruntService.findById(8L).get();
        assertThat(empruntTest.getPseudoEmprunteur()).isEqualTo("ludo");
        assertThat(empruntTest.getDateDebut()).isEqualTo(date1);
        assertThat(empruntTest.isEnCours()).isEqualTo(true);
        assertThat(empruntTest.isProlongeable()).isEqualTo(true);
    }

}
