/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameControlTest {
    
    public GameControlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Case by Darren
     * Test of getNPCName method, of class GameControl.
     */
    @Test
    public void testGetNPCName() {
        System.out.println("getNPCName");
        String expResult = "";
        String[] names = {"Dolores","Arando","Bram","Cale","Dalkon","Daylen",
            "Dodd","Dungarth","Dyrk","Eandro","Falken","Feck","Fenton","Gryphero",
            "Hagar","Jeras","Krynt","Lavant","Leyten","Madian","Malfier","Markus",
            "Meklan","Namen","Navaren","Nerle","Nilus","Ningyan","Norris","Quentin",
            "Semil","Sevenson","Steveren","Talfen","Tamond","Taran","Tavon","Tegan",
            "Vanan","Vincent", "Taimi", "Braham", "Kasmeer", "Marjory", "Scruffy", "Canach"};
        String result = GameControl.getNPCName();
        assertTrue(expResult, Arrays.asList(names).contains(result));
    }

     /* Test of incrementDrawStack method - cant be done since the drawStack is private */

    /**
     * Case by Darren
     * Test of setPaused and isPaused methods, of class GameControl.
     */
    @Test
    public void testSetPaused() {
        System.out.println("setPaused");
        GameControl instance = new GameControl();
        instance.setPaused(false);
        boolean result = instance.isPaused();
        assertEquals(result,false);
        instance.setPaused(true);
        result = instance.isPaused();
        assertEquals(result,true);
    }

    /**
     * Case by Darren
     * Test of getPlayers and setPlayer methods, of class GameControl.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        GameControl instance = new GameControl();
        instance.createPlayers(new String[]{"Bob","Bill","Barry"}, new boolean[]{false,false,true});
        List<Player> expResult = new ArrayList();
        expResult.add(new Player("Bob",false));
        expResult.add(new Player("Bill",false));
        expResult.add(new Player("Barry",true));
        List<Player> result = instance.getPlayers();
        for(int i=0;i<result.size();i++){
                assertEquals(result.get(i).getName(),expResult.get(i).getName());
                assertEquals(result.get(i).isNPC(),expResult.get(i).isNPC());
        }
    }

    /**
     * Case by Darren
     * Test of getNumberOfHumans method, of class GameControl.
     */
    @Test
    public void testGetNumberOfHumans() {
        System.out.println("getNumberOfHumans");
        GameControl instance = new GameControl();
        instance.createPlayers(new String[]{"Bob","Bill","Barry"}, new boolean[]{false,false,true});
        int expResult =2;
        int result = instance.getNumberOfHumans();
        assertEquals(expResult, result);
    }

    /**
     * Case by Darren
     * Test of setDifficulty and getDifficulty methods, of class GameControl.
     */
    @Test
    public void testSetDifficulty() {
        System.out.println("setDifficulty");
        int diff = 1;
        GameControl instance = new GameControl();
        instance.setDifficulty(diff);
        assertEquals(diff,instance.getDifficulty());
        diff = 0;
        instance.setDifficulty(diff);
        assertEquals(diff,instance.getDifficulty());
    }


    /**
     * Case by Darren
     * Test of getTopCard and addToPile methods, of class GameControl.
     */
    @Test
    public void testGetTopCard() {
        System.out.println("getTopCard");
        GameControl instance = new GameControl();
        instance.addToPile(new Card("4","green",false));
        instance.addToPile(new Card("3","red",false));
        Card expResult = new Card("3","red",false);
        Card result = instance.getTopCard();
        assertTrue(expResult.getColor().equals(result.getColor()));
        assertTrue(expResult.getValue().equals(result.getValue()));
        assertTrue(expResult.isSpecial()== (result.isSpecial()));
    }

    /**
     * Case by Darren
     * Test of isClockwise and reverse methods, of class GameControl.
     */
    @Test
    public void testIsClockwise() {
        System.out.println("isClockwise");
        GameControl instance = new GameControl();
        boolean expResult = true;
        boolean result = instance.isClockwise();
        assertEquals(expResult, result);
        instance.reverse();
        expResult = false;
        result = instance.isClockwise();
        assertEquals(expResult, result);

    }

    /**
     * Case by Darren
     * Test of initializeGamePlay method, of class GameControl.
     */
    @Test
    public void testInitializeGamePlay() {
        System.out.println("initializeGamePlay");
        GameControl instance = new GameControl();
        instance.createPlayers(new String[]{"Bob","Bill","Barry"}, new boolean[]{false,false,true});
        instance.initializeGamePlay();
        for(Player p:instance.getPlayers()){
            assertEquals(p.getHand().size(),7);
            for(Object c:p.getHand()){
                assertThat((Card)c,instanceOf(Card.class));
            }
        }
    }

    /**
     * Case by Darren
     * Test of getPile method, of class GameControl.
     */
    @Test
    public void testGetPile() {
        System.out.println("getPile");
        GameControl instance = new GameControl();
        List<Card> expResult = new ArrayList();
        expResult.add(new Card("4","green",false));
        expResult.add(new Card("3","red",false));
        instance.addToPile(new Card("4","green",false));
        instance.addToPile(new Card("3","red",false));
        List<Card> result = instance.getPile();
        for(int i=0;i<result.size();i++){
            assertTrue(expResult.get(i).getColor().equals(result.get(i).getColor()));
            assertTrue(expResult.get(i).getValue().equals(result.get(i).getValue()));
            assertTrue(expResult.get(i).isSpecial()== (result.get(i).isSpecial()));
        }
    }

    /**
     * Case by Darren
     * Test of getDeck method, of class GameControl.
     */
    @Test
    public void testGetDeck() {
        System.out.println("getDeck");
        GameControl instance = new GameControl();
        instance.createPlayers(new String[]{"Bob","Bill","Barry"}, new boolean[]{false,false,true});
        instance.initializeGamePlay();
        Deck result = instance.getDeck();
        assertThat(result,instanceOf(Deck.class));
    }

    /**
     * Test of setTurn method, of class GameControl.
     */
    @Test
    public void testSetTurn() {
        System.out.println("setTurn");
        boolean state = false;
        GameControl instance = new GameControl();
        instance.setTurn(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Case by Darren
     * Test of getNotification and setNotification methods, of class GameControl.
     */
    @Test
    public void testGetNotification() {
        System.out.println("getNotification");
        GameControl instance = new GameControl();
        String expResult = "Hello World";
        instance.setNotification(expResult);
        String result = instance.getNotification();
        assertEquals(expResult, result);
    }

    /**
     * Test of isTurnEnded method, of class GameControl.
     */
    @Test
    public void testIsTurnEnded() {
        System.out.println("isTurnEnded");
        GameControl instance = new GameControl();
        boolean expResult = false;
        boolean result = instance.isTurnEnded();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivePlayer method, of class GameControl.
     */
    @Test
    public void testGetActivePlayer() {
        System.out.println("getActivePlayer");
        GameControl instance = new GameControl();
        Player expResult = null;
        Player result = instance.getActivePlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInactivePlayers method, of class GameControl.
     */
    @Test
    public void testGetInactivePlayers() {
        System.out.println("getInactivePlayers");
        GameControl instance = new GameControl();
        List<Player> expResult = null;
        List<Player> result = instance.getInactivePlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawCards method, of class GameControl.
     */
    @Test
    public void testDrawCards() {
        System.out.println("drawCards");
        int num = 0;
        GameControl instance = new GameControl();
        instance.drawCards(num);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rotatePlayers method, of class GameControl.
     */
    @Test
    public void testRotatePlayers() {
        System.out.println("rotatePlayers");
        GameControl instance = new GameControl();
        instance.rotatePlayers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of NpcPlayByColor method, of class GameControl.
     */
    @Test
    public void testNpcPlayByColor() {
        System.out.println("NpcPlayByColor");
        List<Card> playable = null;
        GameControl instance = new GameControl();
        boolean expResult = false;
        boolean result = instance.NpcPlayByColor(playable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of NpcPlayByValue method, of class GameControl.
     */
    @Test
    public void testNpcPlayByValue() {
        System.out.println("NpcPlayByValue");
        List<Card> playable = null;
        GameControl instance = new GameControl();
        boolean expResult = false;
        boolean result = instance.NpcPlayByValue(playable);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of NpcPlayFirst method, of class GameControl.
     */
    @Test
    public void testNpcPlayFirst() {
        System.out.println("NpcPlayFirst");
        List<Card> playable = null;
        GameControl instance = new GameControl();
        instance.NpcPlayFirst(playable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of npcAction method, of class GameControl.
     */
    @Test
    public void testNpcAction() {
        System.out.println("npcAction");
        GameControl instance = new GameControl();
        int expResult = 0;
        boolean result = instance.npcAction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextPlayer method, of class GameControl.
     */
    @Test
    public void testNextPlayer() {
        System.out.println("nextPlayer");
        GameControl instance = new GameControl();
        instance.nextPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextPlayer method, of class GameControl.
     */
    @Test
    public void testGetNextPlayer() {
        System.out.println("getNextPlayer");
        GameControl instance = new GameControl();
        Player expResult = null;
        Player result = instance.getNextPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayableCards method, of class GameControl.
     */
    @Test
    public void testGetPlayableCards() {
        System.out.println("getPlayableCards");
        GameControl instance = new GameControl();
        List<Card> expResult = null;
        List<Card> result = instance.getPlayableCards();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * (MIGHT BE HARD TO TEST, IF EVEN POSSIBLE)
     * Test of handleCard method, of class GameControl.
     */
    @Test
    public void testHandleCard() {
        System.out.println("handleCard");
        Card current = null;
        List<Card> hand = null;
        GameControl instance = new GameControl();
        instance.handleCard(current, hand);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
