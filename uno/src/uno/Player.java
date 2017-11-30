
package uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Player {
    private String name; //Player's name
    private List<Card> hand = new ArrayList(); //Player's hand   
    private boolean NPC; //If they are an NPC or actual player
    private boolean Played; //If they have played a card
    private boolean declaredUNO;
    //Constructors
    public Player(String name){
        this.name = name;
        this.NPC = false;
        this.Played = false;
    }
    public Player(String name,boolean NPC){
        this.name = name;
        this.NPC = NPC;
        this.Played = false;
    }
    public boolean hasDeclaredUNO(){
        return this.declaredUNO;
    }

    public void sortHand(){
        Collections.sort(this.hand, new Comparator<Card>() {
            @Override
            public int compare(final Card c1, final Card c2) {
                return c1.getColor().compareTo(c2.getColor());
            }
        });
    }
    public void setUNO(boolean state){
        this.declaredUNO = state;
    }
    public boolean hasPlayed(){
        return this.Played;
    }
    public void setPlayed(boolean state){
        this.Played = state;
    }
    public boolean isNPC(){
        return this.NPC;
    }
    public void addCard(Card c){
        // Add a card to the player's hand ArrayList
        this.hand.add(c);
    }
    public void removeCard(int index){
        // Remove a card from the hand at an index
        this.hand.remove(index);
    }
    public String getName(){
        return this.name;
    }
    public List getHand(){
        return this.hand;
    }
    public String toString(){
        return this.name + " NPC: " + this.NPC + " Has Played: " + this.Played;
    }
}
