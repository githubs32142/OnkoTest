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
public class CheckRegTest {

    public CheckRegTest() {
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
     * Test of checkEmail method, of class CheckReg.
     */
    @Test
    public void testCheckEmail1() {
        System.out.println("Sprawdzanie adresu email : 123Q?@ocs.eu");
        String mail = "123Q?@ocs.eu";
        boolean expResult = false;
        boolean result = CheckReg.checkEmail(mail);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckEmail2() {
        System.out.println("Sprawdzanie adresu email : 123Q?@ocs@.eu.pl");
        String mail = "123Q?@ocs@.eu.pl";
        boolean expResult = false;
        boolean result = CheckReg.checkEmail(mail);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckEmail3() {
        System.out.println("Sprawdzanie adresu email : tmpemail@gmail.com");
        String mail = "tmpemail@gmail.com";
        boolean expResult = true;
        boolean result = CheckReg.checkEmail(mail);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkHeightCent method, of class CheckReg.
     */
    @Test
    public void testCheckHeightCent1() {
        System.out.println("Sprawdzenie wzrostu w centymetrach: 300");
        String word = "250";
        boolean expResult = false;
        boolean result = CheckReg.checkHeightCent(word);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckHeightCent2() {
        System.out.println("Sprawdzenie wzrostu w centymetrach: 220");
        String word = "249";
        boolean expResult = true;
        boolean result = CheckReg.checkHeightCent(word);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckHeightCent3() {
        System.out.println("Sprawdzenie wzrostu w centymetrach: 110");
        String word = "110";
        boolean expResult = true;
        boolean result = CheckReg.checkHeightCent(word);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckHeightCent4() {
        System.out.println("Sprawdzenie wzrostu w centymetrach: 60");
        String word = "60";
        boolean expResult = true;
        boolean result = CheckReg.checkHeightCent(word);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckHeightCent5() {
        System.out.println("Sprawdzenie wzrostu w centymetrach: -60");
        String word = "-60";
        boolean expResult = false;
        boolean result = CheckReg.checkHeightCent(word);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkWeight method, of class CheckReg.
     */
    @Test
    public void testCheckWeight1() {
        System.out.println("Sprawdzenie wagi w kilogramach: 53.00");
        String word = "53.00";
        boolean expResult = true;
        boolean result = CheckReg.checkWeight(word);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckWeight2() {
        System.out.println("Sprawdzenie wagi w kilogramach: 249.999");
        String word = "249.999";
        boolean expResult = true;
        boolean result = CheckReg.checkWeight(word);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckWeight3() {
        System.out.println("Sprawdzenie wagi w kilogramach: 250.001");
        String word = "250.001";
        boolean expResult = false;
        boolean result = CheckReg.checkWeight(word);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckAge1() {
        System.out.println("Sprawdzenie wieku w latach: 150");
        String word = "150";
        boolean expResult = false;
        boolean result = CheckReg.checkAge(word);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckAge2() {
        System.out.println("Sprawdzenie wieku w latach: 149");
        String word = "149";
        boolean expResult = true;
        boolean result = CheckReg.checkAge(word);
        assertEquals(expResult, result);
    }

}
