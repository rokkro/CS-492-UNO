/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bagels
 */
public class CardTest {
    
    public CardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isSpecial method, of class Card.
     */
    @Test
    public void testIsSpecial() {
        System.out.println("isSpecial");
        Card instance = new Card("d2","red",true);
        boolean expResult = true;
        boolean result = instance.isSpecial();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class Card.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Card instance = new Card("3","red",false);
        String expResult = "3";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getColor method, of class Card.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Card instance = new Card("5","green",false);
        String expResult = "green";
        String result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setColor method, of class Card.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        String s = "red";
        Card instance = new Card("5","wild",true);
        instance.setColor(s);
        assertEquals(s,instance.getColor());
    }

    /**
     * Test get and set of button methods
    */
    @Test
    public void testSetButton() {
        System.out.println("setButton");
        Object button = new Object();
        Card instance = new Card("3","red",false);
        instance.setButton(button);
        assertEquals(button,instance.getButton());
    }

    /**
     * Test of getImage method, of class Card.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Card instance = new Card("3","red",false);
        String expResult = "/resources/cards/" + instance.getColor() + "_" + instance.getValue() + ".png";
        String result = instance.getImage();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Card instance = new Card("3","red",false);
        String expResult = instance.getColor() + " " + instance.getValue()+ " special:" + instance.isSpecial() + " image:" + instance.getImage();
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
