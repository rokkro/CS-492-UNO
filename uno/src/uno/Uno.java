package uno;
//MAIN CLASS + METHOD
public class Uno {
    public static void main(String[] args) {
        Menu menu = new Menu(new GameControl());
        menu.setVisible(true);
    }
}
