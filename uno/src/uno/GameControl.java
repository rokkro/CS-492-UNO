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
    
    public static String getNPCName(){ // Generate a random name
        String[] s = {"Dolores","Arando","Bram","Cale","Dalkon","Daylen","Dodd","Dungarth","Dyrk","Eandro","Falken","Feck","Fenton","Gryphero","Hagar","Jeras","Krynt","Lavant","Leyten","Madian","Malfier","Markus","Meklan","Namen","Navaren","Nerle","Nilus","Ningyan","Norris","Quentin","Semil","Sevenson","Steveren","Talfen","Tamond","Taran","Tavon","Tegan","Vanan","Vincent"};
        String result = s[(int)(Math.random() * s.length)];
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
    public void setDifficulty(int diff){
        this.difficulty = diff; //0 = easy, 3 = hardest
    }
    public boolean isClockwise(){
        return this.clockwise;
    }
    public List<Card> getPile(){
        return this.pile;
    }
    public void addToPile(Card c){
        this.pile.add(c);
    }
    public Deck getDeck(){
        return this.deck;
    }
    public Player getActivePlayer(){
        return this.activePlayer;
    }
    public void rotatePlayers(){
        // Rotate to the next player. Should be called from GameScreen's nextPlayer()
        while(this.paused){} // Pause game while this.paused == true
        this.getActivePlayer().setPlayed(false);
        for(int i=0;i<this.players.size();i++){
            if(this.players.get(i).getHand().size()==0){
                this.players.remove(i);
            }
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
}
