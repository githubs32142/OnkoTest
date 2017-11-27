/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class FCListTest {

    public FCListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readInput method, of class FCList.
     */
    @Test
    public void testReadInput() {
        System.out.println("Sprawdzenie poprawności działania metody readInput przy podaniu nieprawidłowej ścieżki");
        String path = "";
        FCList instance = new FCList();
        String expResult = "";
        String result = instance.readInput(path);
        assertEquals(expResult, result);
        System.out.println("Poprawne zachowanie programu");
    }

    @Test
    public void testReadData() {
        System.out.println("Sprawdzenie poprawności działania metody readData przy podaniu nieprawidłowej ścieżki");
        String path = "";
        FCList instance = new FCList();
        instance.readData(path);
        int expResult = 0;
        assertNotNull(instance.list);
        System.out.println("Poprawne zachowanie programu");
    }

    @Test
    public void testGetAlias() {
        System.out.println("Sprawdzenie działania metody wywołując index który nie znajduje się na liście");
        String path = "";
        FCList instance = new FCList();
        instance.getAlias(50);
        System.out.println("Poprawne zachowanie programu");

    }

}
