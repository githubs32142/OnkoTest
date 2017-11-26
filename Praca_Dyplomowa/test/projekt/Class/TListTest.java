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
     * Test of readInput method, of class TList.
     */
    @Test
    public void testReadInput() {
        System.out.println("readInput");
        String path = "";
        TList instance = new TList();
        String expResult = "";
        String result = instance.readInput(path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readData method, of class TList.
     */
    @Test
    public void testReadData() {
        System.out.println("readData");
        String path = "";
        TList instance = new TList();
        instance.readData(path);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class TList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TList instance = new TList();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class TList.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String s = "";
        TList instance = new TList();
        boolean expResult = false;
        boolean result = instance.contains(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeAssert method, of class TList.
     */
    @Test
    public void testMakeAssert() {
        System.out.println("makeAssert");
        String s = "";
        TList instance = new TList();
        String expResult = "";
        String result = instance.makeAssert(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeOperation method, of class TList.
     */
    @Test
    public void testMakeOperation() {
        System.out.println("makeOperation");
        List<String> list = null;
        TList instance = new TList();
        instance.makeOperation(list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
