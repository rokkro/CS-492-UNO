package uno;
import java.util.ArrayList;
import java.util.List;

public class GameControl {
    private Deck deck;
    private List<Player> players;
    private Player activePlayer;
    private boolean paused = false;
    private List<Card> pile = new ArrayList();
    private boolean clockwise = true;
    private int difficulty;
    private int rotateFactor = 1; // How many players are skipped/rotated
    private int drawStack = 0; // How many cards next player is required to draw (d2, d4)
    private boolean turnEnded = false;
    
    public static String getNPCName(){ // Get a random name
        String[] names = {"Dolores","Arando","Bram","Cale","Dalkon","Daylen",
            "Dodd","Dungarth","Dyrk","Eandro","Falken","Feck","Fenton","Gryphero",
            "Hagar","Jeras","Krynt","Lavant","Leyten","Madian","Malfier","Markus",
            "Meklan","Namen","Navaren","Nerle","Nilus","Ningyan","Norris","Quentin",
            "Semil","Sevenson","Steveren","Talfen","Tamond","Taran","Tavon","Tegan",
            "Vanan","Vincent"};
        String result = names[(int)(Math.random() * names.length)];
        return result;
    }
    public void initializeGamePlay(){
        // Create a deck, and give all players 7 cards
        this.deck = new Deck();
        for(Player p:this.players){
            for(int i=0;i<7;i++){
                p.addCard(this.deck.drawCard());
            }
        }
    }
    public void reverse(){
        // Reverse game rotation flag
        if(this.clockwise)
            this.clockwise = false;
        else
            this.clockwise = true;
    }
    public void incrementDrawStack(int num){
        this.drawStack += num;
    }
    public void setDifficulty(int diff){
        this.difficulty = diff; //0 = easy, 1 = hardest
    }
    public boolean isPaused(){
        return this.paused;
    }
    public Card getTopCard(){
        return this.pile.get(this.pile.size()-1);
    }
    public boolean isClockwise(){
        return this.clockwise;
    }
    public List<Card> getPile(){
        return this.pile;
    }
    public void setRotationFactor(int f){
        this.rotateFactor = f;
    }
    public void addToPile(Card c){
        this.pile.add(c);
    }
    public Deck getDeck(){
        return this.deck;
    }
    public void endTurn(boolean state){
        this.turnEnded = state;
    }
    public boolean isTurnEnded(){
        return this.turnEnded;
    }
    public Player getActivePlayer(){
        return this.activePlayer;
    }
    public void drawCards(int num){
        for(int i=0;i<num;i++){
            if(this.deck.deckSize() == 0)
                return;
            List<Card> hand = this.activePlayer.getHand();
            Card c = this.deck.drawCard();
            hand.add(c);
        }
    }
    public void rotatePlayers(){
        // Rotate to the next player. Should be called from GameScreen's nextPlayer()
        for(int j=0;j<this.rotateFactor;j++){
            this.getActivePlayer().setPlayed(false);
            for(int i=0;i<this.players.size();i++){
                if(this.players.get(i).getHand().size()==0)
                    this.players.remove(i);
            }
            if(!this.clockwise){
                Player tmp = (Player)this.players.get(this.players.size()-1);
                this.players.add(0, tmp);
                this.players.remove(this.players.size()-1);
                this.activePlayer = this.players.get(0);
            }
            else{
                Player tmp = (Player)this.players.get(0);
                this.players.add(tmp);
                this.players.remove(0);
                this.activePlayer = this.players.get(0);
            }
            this.drawCards(this.drawStack);
            this.drawStack = 0;
        }
        this.rotateFactor = 1;
    }
    public void pauseGame(boolean state){
        this.paused = state;
    }
    public List<Player> getPlayers(){
        return this.players;
    }
    public void createPlayers(String[] pnames, boolean[] NPC){
        // Create the players
        this.players = new ArrayList();
        for(int i=0;i<pnames.length;i++){
            this.players.add(new Player(pnames[i],NPC[i]));
        }
        this.activePlayer = this.players.get(0);
    }
    public List<Player> getActiveNPCs(){
        // Get list of players who are NPCs (not used for anything right now)
        List<Player> lst = new ArrayList();
        for(int i=0;i<this.players.size();i++){
            if(this.players.get(i).isNPC() && this.players.get(i).getHand().size()>=1){
                lst.add(this.players.get(i));
            }
        }
        return lst;
    }

    /*
    private void npcAction(){      
        //Actual Logic
        for(int i = 0; i < this.getActivePlayer().getHand().size();i++){
            if(isPlayableCard((Card)this.getActivePlayer().getHand().get(i)) && !this.getActivePlayer().hasPlayed()){
                System.out.println(this.getActivePlayer().getName() + " is playing: " + (Card)this.getActivePlayer().getHand().get(i)); //Logging
                this.handleCard((Card)this.getActivePlayer().getHand().get(i),this.getActivePlayer().getHand());
                this.getActivePlayer().setPlayed(true);
            }
        }
        if(!this.getActivePlayer().hasPlayed()){
            System.out.println(this.getActivePlayer().getName() + " is drawing!");
            this.drawFromDeck(1);        
            this.getActivePlayer().setPlayed(true);
        }        
    }
    */
}
