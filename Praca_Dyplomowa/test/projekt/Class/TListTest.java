/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

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
public class TListTest {

    public TListTest() {
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
        TList instance = new TList();
        String expResult = "";
        String result = instance.readInput(path);
        assertEquals(expResult, result);
    }

    @Test
    public void testReadData() {
        System.out.println("Sprawdzenie poprawności działania metody readData przy podaniu nieprawidłowej ścieżki");
        String path = "";
        TList instance = new TList();
        instance.readData(path);
        int expResult = 0;
        assertNotNull(instance.size());
    }

}
