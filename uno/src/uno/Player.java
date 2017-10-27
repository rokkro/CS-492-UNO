
package uno;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand = new ArrayList();    
    private boolean NPC;
    
    public Player(String name){
        this.name = name;
        this.NPC = false;
    }
    public Player(String name,boolean NPC){
        this.name = name;
        this.NPC = NPC;
    }
    public boolean isNPC(){
        return this.NPC;
    }
    public void addCard(Card c){
        this.hand.add(c);
    }
    public void removeCard(int index){
        this.hand.remove(index);
    }
    public String getName(){
        return this.name;
    }
    public List getHand(){
        return this.hand;
    }
    public String toString(){
        return this.name;
    }
}
