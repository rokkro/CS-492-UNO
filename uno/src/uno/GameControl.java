package uno;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameControl {
    private Deck deck;
    private List<Player> players;
    private Player activePlayer;
    private List<Player> inactivePlayers = new ArrayList();
    private boolean paused = false;
    private List<Card> pile = new ArrayList();
    private boolean clockwise = true;
    private int difficulty;
    private int rotateFactor = 1; // How many players are skipped/rotated
    private int drawStack = 0; // How many cards next player is required to draw (d2, d4)
    private boolean turnEnded = false;
    private String notification = "";
    private int notificationDecay = 0;
    
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

    public void incrementDrawStack(int num){
        this.drawStack += num;
    }
    public void setGameState(boolean state){
        this.paused = state;
    }
    public List<Player> getPlayers(){
        return this.players;
    }
    public int getNumberOfHumans(){
        int count = 0;
        for(Player p:this.players){
            if(!p.isNPC())
                count+=1;
        }
        return count;
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
    public int getDifficulty() {
        return this.difficulty;
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
    public void setTurn(boolean state){
        this.turnEnded = state;
    }
    public String getNotification(){
        return this.notification;
    }
    public boolean isTurnEnded(){
        return this.turnEnded;
    }
    public Player getActivePlayer(){
        return this.activePlayer;
    }
    public List<Player> getInactivePlayers(){
        return this.inactivePlayers;
    }
    public void setNotification(String s){
        this.notificationDecay = 0;
        this.notification = s;
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
            this.getActivePlayer().setUNO(false);
            for(int i=0;i<this.players.size();i++){
                if(this.players.get(i).getHand().size()==0){
                    this.inactivePlayers.add(this.players.get(i));
                    this.players.remove(i);
                    this.paused = true;
                }
            }
            if(this.players.size() == 0)
                return;
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
            if(this.drawStack > 0){
                this.drawCards(this.drawStack);
                this.drawStack = 0;
                this.rotateFactor+=1;
            }
            this.notificationDecay+=1;
        }
        if(this.notificationDecay >= 5){
            this.setNotification("");
        }
        this.rotateFactor = 1;
    }
    public boolean NpcPlayByColor(List<Card> playable){
        for(Card c: playable){ //Prefer eliminating same color before switching colors
            if(c.getColor().equals(this.getTopCard().getColor()) && !c.getColor().equals("wild")){
                System.out.println(this.getActivePlayer().getName() + " is playing: " + c); 
                this.handleCard(c, this.getActivePlayer().getHand());
                return true;
            }
        }
        return false;
    }
    public boolean NpcPlayByValue(List<Card> playable){
        for(Card c: playable){ //Prefer eliminating same color before switching colors
            if(c.getValue().equals(this.getTopCard().getValue()) && !c.getColor().equals("wild")){
                System.out.println(this.getActivePlayer().getName() + " is playing: " + c); 
                this.handleCard(c, this.getActivePlayer().getHand());
                return true;
            }
        }
        return false;
    }
    public void NpcPlayFirst(List<Card> playable){
        System.out.println(this.getActivePlayer().getName() + " is playing: " + playable.get(0)); 
        this.handleCard(playable.get(0), this.getActivePlayer().getHand());
    }
    public int npcAction(){      
        //Actual Logic
        List<Card> playable = this.getPlayableCards();
        if(playable.size() == 0){
            System.out.println(this.getActivePlayer().getName() + " is drawing!");
            this.getActivePlayer().setPlayed(true);
            return 1;
        }
        else{
            if(this.difficulty == 0){
                this.NpcPlayFirst(playable);
            }
            else{
                if(!this.NpcPlayByColor(playable))
                    if(!this.NpcPlayByValue(playable))
                        this.NpcPlayFirst(playable);
            }
        }
        if(this.getActivePlayer().getHand().size() == 1){
            if(this.getDifficulty() == 0){
                if(this.choice())
                    this.getActivePlayer().setUNO(true);
            }
            else{
                this.getActivePlayer().setUNO(true);
            }
        }
        this.nextPlayer();
        return 0;
    }
        
    private boolean choice() {
        return Math.random() < 0.5;
    }
    
    public void nextPlayer(){
        Player current = this.getActivePlayer();
        if(current.getHand().size() == 1 && !current.hasDeclaredUNO()){
            if(this.choice()){
                this.setNotification(current.getName() + " draws 2. Forgot to declare UNO!");
                this.drawCards(2);
            }
        }
        this.setTurn(true);
    }
    public Player getNextPlayer(){
        if(this.clockwise){
            if(this.players.size() > 1)
                return (Player)this.players.get(1);
            else
                return (Player)this.players.get(0);
        }
        else{
            if(this.players.size() > 1)
                return (Player)this.players.get(this.players.size()-1);
            else
                return (Player)this.players.get(this.players.size()-1);
        }
    }
    public void createPlayers(String[] pnames, boolean[] NPC){
        // Create the players
        this.players = new ArrayList();
        for(int i=0;i<pnames.length;i++){
            this.players.add(new Player(pnames[i],NPC[i]));
        }
        this.activePlayer = this.players.get(0);
    }
    private boolean isCardPlayable(Card c){
        if(c.getColor().equals(this.getTopCard().getColor()))
            return true;
        else if(c.getValue().equals(this.getTopCard().getValue()))
            return true;
        else if(c.getColor().equals("wild"))
            return true;
        return false;
    }
    public List<Card> getPlayableCards(){
        List<Card> playable = new ArrayList();
        for(int i = 0; i < this.getActivePlayer().getHand().size();i++){
            Card current = (Card)this.activePlayer.getHand().get(i);
            if(this.isCardPlayable(current))
                playable.add(current);
        }
        return playable;
    }
    public void handleCard(Card current, List<Card> hand){
        // Compare and place card. Handle special cards appropriately
        if(!hand.equals(this.getActivePlayer().getHand())){ // If you're clicking a card belonging to a non-active player.
            return;
        }
        else if(this.getActivePlayer().hasPlayed()){
            return;
        }
        Card top = this.getPile().get(this.getPile().size()-1);
        if((current.getColor().equals(top.getColor()) || current.getValue() == top.getValue()) &&
                !current.getColor().equals("wild")){
            this.addToPile(current);
            hand.remove(current); // Remove card from hand
            if(current.getValue().equals("reverse")){
                this.reverse(); // Reverse direction
                this.setNotification("Direction reversed.");
            }
            else if(current.getValue().equals("skip")){
                this.setRotationFactor(2);
                this.setNotification(this.getNextPlayer().getName() + " was skipped.");
            }
            else if(current.getValue().equals("d2")){ // Draw two
                this.incrementDrawStack(2);
                this.setNotification(this.getNextPlayer().getName() + " draws 2.");
            }
            this.getActivePlayer().setPlayed(true);
        }
        else if(current.getColor().equals("wild") && !this.getActivePlayer().isNPC()){
            this.addToPile(current);
            hand.remove(current);
            if(current.getValue().equals("d4") && this.getPlayers().size() > 1){
                this.incrementDrawStack(4);
            }
        }
        else if(current.getColor().equals("wild") && this.getActivePlayer().isNPC()){
            int red = 0;
            int yell = 0;
            int blue = 0;
            int green = 0;
            this.addToPile(current);
            hand.remove(current);
            for(int i=0;i<this.getActivePlayer().getHand().size();i++){
                switch(((Card)this.getActivePlayer().getHand().get(i)).getColor()){
                    case "red":
                        red+=1;
                        break;
                    case "yellow":
                        yell+=1;
                        break;
                    case "blue":
                        blue+=1;
                        break;
                    case "green":
                        green+=1;
                        break;
                    default:
                        break;
                }
            }
            if(red >= yell && red >= blue && red >= green){
                this.getTopCard().setColor("red");
            }
            else if(yell >= red && yell >= blue && red >=green){
                this.getTopCard().setColor("yellow");
            }
            else if(blue >= red && blue >= yell && blue>=green){
                this.getTopCard().setColor("blue");
            }
            else{
                this.getTopCard().setColor("green");
            }
            if(this.getTopCard().getValue().equals("d4")){
                this.incrementDrawStack(4);
                this.setNotification(this.getNextPlayer().getName() + " draws 4. Color: " + this.getTopCard().getColor() + ".");
            }
            else{
                this.setNotification("Color set to " + this.getTopCard().getColor() + ".");
            }
        }
    }
}
