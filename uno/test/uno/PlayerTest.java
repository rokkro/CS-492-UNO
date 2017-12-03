/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Case by Darren
     * Test of hasDeclaredUNO and setUNO methods, of class Player.
     */
    @Test
    public void testHasDeclaredUNO() {
        System.out.println("hasDeclaredUNO");
        Player instance = new Player("name");
        boolean expResult = false;
        boolean result = instance.hasDeclaredUNO();
        assertEquals(expResult, result);
        instance.setUNO(true);
        expResult = true;
        result = instance.hasDeclaredUNO();
        assertEquals(expResult, result);
    }

    /**
     * Case by Darren
     * Test of hasPlayed and setPlayed methods, of class Player.
     */
    @Test
    public void testHasPlayed() {
        System.out.println("hasPlayed");
        Player instance = new Player("name");
        boolean expResult = false;
        boolean result = instance.hasPlayed();
        assertEquals(expResult, result);
        instance.setPlayed(true);
        expResult = true;
        result = instance.hasPlayed();
        assertEquals(expResult, result);
    }

    /**
     * Case by Darren
     * Test of isNPC method, of class Player.
     */
    @Test
    public void testIsNPC() {
        System.out.println("isNPC");
        Player instance  = new Player("name",false);;
        boolean expResult = false;
        boolean result = instance.isNPC();
        assertEquals(expResult, result);
        instance  = new Player("name",true);
        expResult = true;
        result = instance.isNPC();
        assertEquals(expResult, result);
    }

    /**
     * Case by Darren
     * Test of addCard method, of class Player.
     */
    @Test
    public void testAddCard() {
        System.out.println("addCard");
        Card c = new Card("3","red",false);
        Player instance = new Player("name");
        instance.addCard(c);
        Card d = (Card)instance.getHand().get(0);
        assertEquals(d,c);
    }

    /**
     * Case by Luke
     * Test of removeCard method, of class Player.
     */
    @Test
    public void testRemoveCard() {
        System.out.println("removeCard");
        int index = 0;
        Player instance = null;
        instance.removeCard(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Case by Luke
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Case by Luke
     * Test of getHand method, of class Player.
     */
    @Test
    public void testGetHand() {
        System.out.println("getHand");
        Player instance = null;
        List expResult = null;
        List result = instance.getHand();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Case by Luke
     * Test of toString method, of class Player.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Player instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
