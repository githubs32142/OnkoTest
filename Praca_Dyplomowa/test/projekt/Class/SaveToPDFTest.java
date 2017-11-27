/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.Class;

import java.io.File;
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
public class SaveToPDFTest {

    public SaveToPDFTest() {
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
     * Test of createPdf method, of class SaveToPDF.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreatePdf() throws Exception {
        System.out.println("Sprawdzenie, czy dla podanej ścieżki zapizę się plik pdf");
        File file = new File("1.pdf");
        SaveToPDF.createPdf(file);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Prawidłowe zachowanie aplikacji.");
    }

    @Test
    public void testCreatePdf2() throws Exception {
        System.out.println("Sprawdzenie, czy dla pustej ścieżki wykona się aplikacjia");
        File file = new File("");
        SaveToPDF.createPdf(file);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("Prawidłowe zachowanie apliacji");
    }

}
