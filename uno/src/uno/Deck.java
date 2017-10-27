/*
*So the Deck works as follows
*It is an array and basically will always be full
*Players can draw from the deck but this increases its topIndex
*Which Basically steps you through the deck an easy way.
*When cards are dealt to people a duplicate card is sent to their hand
*while maintaining the card still in the array
 */
package uno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
public class Deck {
    private List card_deck = new ArrayList();
    private String[] colors = new String[]{"red", "yellow", "green", "blue"};
    private String[] color_cards = new String[]{"0","1","2","3","4","5","6","7","8","9","skip","reverse","d2"};
    private String[] special_cards = new String[]{"d4","cc"};
    //25 of each color
    //  2 of each rank except zero
    //4 wild and 4 wild draw four

    public Deck(){
        makeDeck();
        deckShuffle();
    }
    public int deckSize(){
        return this.card_deck.size();
    }
    public Card drawCard(){
        Card c = (Card)this.card_deck.get(0);
        this.card_deck.remove(0);
        return c;
    }
    public Card AddandRedraw(Card c){
        this.card_deck.add(c);
        return this.drawCard();
    }
    private void makeDeck(){
        for(String color: colors){
            for(String type:color_cards){
                boolean isSpecial = false;
                if(type == "p2" || type == "skip" || type == "reverse"){
                    isSpecial = true;
                }
                this.card_deck.add(new Card(type,color,isSpecial));
                if(type!= "0")
                    this.card_deck.add(new Card(type,color,isSpecial));
            }
        }
        for(String special:special_cards){
            for(int i=0;i<4;i++){
                this.card_deck.add(new Card(special,"wild",true));
            }
        }
    }
    public void deckShuffle(){
        Collections.shuffle(this.card_deck);
    }

}
