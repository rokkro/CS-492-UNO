package uno;

import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class DeckTest {
    
    public DeckTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Case by Darren
     * Test of deckSize method, of class Deck.
     */
    @Test
    public void testDeckSize() {
        System.out.println("deckSize");
        Deck instance = new Deck();
        int expResult = 108;
        int result = instance.deckSize();
        assertEquals(expResult, result);
    }

    /**
     * Case by Darren
     * Test of drawCard method, of class Deck.
     */
    @Test
    public void testDrawCard() {
        System.out.println("drawCard");
        Deck instance = new Deck();
        Card result = instance.drawCard();
        System.out.println(result);
        assertThat(result, instanceOf(Card.class));
    }

    /**
     * Case by Luke
     * Test of AddandRedraw method, of class Deck.
     */
    @Test
    public void testAddandRedraw() {
        System.out.println("AddandRedraw");
        Card c = null;
        Deck instance = new Deck();
        Card expResult = null;
        Card result = instance.AddandRedraw(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Case by Luke
     * Test of deckShuffle method, of class Deck.
     */
    @Test
    public void testDeckShuffle() {
        System.out.println("deckShuffle");
        Deck instance = new Deck();
        instance.deckShuffle();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
}
