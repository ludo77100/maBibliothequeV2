package org.ludo.bibliotheque.BibliothequeTestUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.ludo.bibliotheque.dao.ExemplaireRepository;
import org.ludo.bibliotheque.entities.Livre;
import org.ludo.bibliotheque.service.ExemplaireService;
import org.ludo.bibliotheque.service.service.impl.ExemplaireServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ExemplaireServiceTestUnit {

    @Mock
    ExemplaireRepository exemplaireRepository;

    @Autowired
    @InjectMocks
    private ExemplaireServiceImpl exemplaireServiceImpl ;

    /**
     *
     */
    @Test
    public void compositionReferenceExemplaire() {
        Livre livre = new Livre();
        livre.setAuteur("Abc");
        livre.setDecription("Bcd");
        livre.setEditeur("Cde");
        livre.setNombrePages(200);
        livre.setTitre("Def");
        livre.setQuantiteDispo(5);
        livre.setUrlImage("Efg");
        Assert.assertEquals("DAC1", exemplaireServiceImpl.compositionIdentifiant(livre)); }
}