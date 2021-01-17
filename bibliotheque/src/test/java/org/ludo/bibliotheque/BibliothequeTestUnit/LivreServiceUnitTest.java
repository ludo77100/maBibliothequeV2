package org.ludo.bibliotheque.BibliothequeTestUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ludo.bibliotheque.Enums.EtatEnums;
import org.ludo.bibliotheque.dao.EmpruntRepository;
import org.ludo.bibliotheque.dao.ExemplaireRepository;
import org.ludo.bibliotheque.dao.LivreRepository;
import org.ludo.bibliotheque.entities.Exemplaire;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.service.service.impl.LivreServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.Silent.class)
public class LivreServiceUnitTest {

    @Mock
    private LivreRepository livreRepository;
    @Mock
    private ExemplaireRepository exemplaireRepository;
    @Mock
    private EmpruntRepository empruntRepository;
    @Autowired
    @InjectMocks
    private LivreServiceImpl livreService;

    private List<Livre> livres = new ArrayList<>();
    private List<Exemplaire> exemplaireSet1 = new ArrayList<>();
    private List<Exemplaire> exemplaireSet2 = new ArrayList<>();


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

        Mockito.when(livreRepository.findById(1L)).thenReturn(Optional.of(livre1));
        Mockito.when(livreRepository.findById(2L)).thenReturn(Optional.of(livre2));

        Mockito.when(livreRepository.findByTitreContainingIgnoreCase("a")).thenReturn(livres);

        Mockito.when(exemplaireRepository.findAllByLivre_titre("1a")).thenReturn(livre1.getExemplaires());
        Mockito.when(exemplaireRepository.findAllByLivre_titre("2a")).thenReturn(livre2.getExemplaires());

    }


    @Test
    public void findLivreByID_test(){
        Livre livre1Test = livreService.findLivreById(1L);
        assertThat(livre1Test.getTitre()).isEqualTo("1a");
        assertThat(livre1Test.getAuteur()).isEqualTo("1b");
        assertThat(livre1Test.getEditeur()).isEqualTo("1c");
        assertThat(livre1Test.getDecription()).isEqualTo("1d");
        assertThat(livre1Test.getQuantiteDispo()).isEqualTo(1);

        Livre livre2Test = livreService.findLivreById(2L);
        assertThat(livre2Test.getTitre()).isEqualTo("2a");
        assertThat(livre2Test.getAuteur()).isEqualTo("2b");
        assertThat(livre2Test.getEditeur()).isEqualTo("2c");
        assertThat(livre2Test.getDecription()).isEqualTo("2d");
        assertThat(livre2Test.getQuantiteDispo()).isEqualTo(1);
    }

    @Test
    public void searchLivresMethod_test(){
        List<Livre> listeLivres = livreService.findByTitreContainingIgnoreCase("a");
        org.junit.jupiter.api.Assertions.assertEquals(2, listeLivres.size());
    }

}
