/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;
/*
TODO: drawStack implementation


*/
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/*
isPlayableCard(Card current) - returns bool. Whether or not you can play a card
handleCard(Card current, List<Card> hand) Deals with placing card onto pile, checking conditions, and handling various UI elements + state changes from special cards
refreshCardLabel() Updates label above the pile.
PlaceCard(Card current) Deals with UI updating when placing a card. Reccomend you call handleCard() instead to place cards!
nextPlayer() - Rotates to next player
updatePlayerUI() - Updates hands UI, creates card buttons, other UI elements
showOrHide() - Determines when to show 'SHOW' button, and when to show 'HIDE' button
drawFromDeck(int n) - Draws n number of cards and adds them to hand, removes them from the deck
displayRanking() - Deals with UI of pause screen rankings
resumeGame() - unfreezes rotations when coming from pause screen/Wild card screen
colorButtonAction(string color) - Makes the current wild card set to decided color
XXXButtonActionPerformed() - buttons that do stuff when clicked
*/

public class GameScreen extends javax.swing.JFrame {

    private GameControl controller;
    public GameScreen(GameControl c){
        initComponents();
        //bgCover.setVisible(false); // Hide blurry overlay
        this.controller = c;
        p1hand.setVisible(false); // Make main player's hand hidden to avoid peeking
        // Put down a first card
        Deck d = this.controller.getDeck(); 
        Card current = d.drawCard();
        while(current.isSpecial()) // Prevent first card from being special
            current = d.AddandRedraw(current);
        this.placeNewCard(current);
        this.refreshPlayerUI();
        this.controlLoop();
    }    
    public void controlLoop(){
        Thread thread = new Thread(new Runnable(){
            public void run(){
                while(true){
                    while(!GameScreen.this.controller.getTurnState()){
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                    GameScreen.this.controller.endTurn(false);
                    GameScreen.this.controller.rotatePlayers();
                    GameScreen.this.showOrHideElements();
                    refreshPlayerUI();
                }
            }
        });
        thread.start();
    }
    public GameScreen() {
        initComponents();
        this.controller = new GameControl();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorSelect = new javax.swing.JInternalFrame();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)colorSelect.getUI()).setNorthPane(null);
        buttonBlue = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonGreen = new javax.swing.JButton();
        buttonYellow = new javax.swing.JButton();
        buttonRed = new javax.swing.JButton();
        colorSelLabel = new javax.swing.JLabel();
        bgLabel5 = new javax.swing.JLabel();
        pauseScreen = new javax.swing.JInternalFrame();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)pauseScreen.getUI()).setNorthPane(null);
        rank4 = new javax.swing.JLabel();
        rank3 = new javax.swing.JLabel();
        rank2 = new javax.swing.JLabel();
        rank1 = new javax.swing.JLabel();
        rank4name = new javax.swing.JLabel();
        rank1name = new javax.swing.JLabel();
        rank2name = new javax.swing.JLabel();
        rank3name = new javax.swing.JLabel();
        RankingLabel = new javax.swing.JLabel();
        quitButton = new javax.swing.JButton();
        resumeButton = new javax.swing.JButton();
        mmButton = new javax.swing.JButton();
        bgLabel4 = new javax.swing.JLabel();
        bgCover = new javax.swing.JLabel();
        currentCard = new javax.swing.JPanel();
        deckButton = new javax.swing.JButton();
        hideButton = new javax.swing.JToggleButton();
        p1name = new javax.swing.JLabel();
        p3hand = new javax.swing.JScrollPane();
        p3handcontainer = new javax.swing.JPanel();
        p4hand = new javax.swing.JScrollPane();
        p4handcontainer = new javax.swing.JPanel();
        p2hand = new javax.swing.JScrollPane();
        p2handcontainer = new javax.swing.JPanel();
        p1hand = new javax.swing.JScrollPane();
        p1handcontainer = new javax.swing.JPanel();
        showButton = new javax.swing.JToggleButton();
        colorName = new javax.swing.JLabel();
        p4name = new javax.swing.JLabel();
        p3name = new javax.swing.JLabel();
        p2name = new javax.swing.JLabel();
        pauseButton = new javax.swing.JButton();
        turnButton = new javax.swing.JButton();
        unoButton = new javax.swing.JButton();
        hudBG = new javax.swing.JLabel();
        bgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setForeground(java.awt.Color.red);
        setMinimumSize(getPreferredSize());
        setName("Uno-Game"); // NOI18N
        setPreferredSize(new java.awt.Dimension(905, 656));
        setResizable(false);
        setSize(getPreferredSize());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        colorSelect.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        colorSelect.setToolTipText("");
        colorSelect.setVisible(false);
        colorSelect.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonBlue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/blue.png"))); // NOI18N
        buttonBlue.setBorderPainted(false);
        buttonBlue.setContentAreaFilled(false);
        buttonBlue.setFocusable(false);
        buttonBlue.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bluepush.png"))); // NOI18N
        buttonBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBlueActionPerformed(evt);
            }
        });
        colorSelect.getContentPane().add(buttonBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 270, -1));

        buttonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cancel.png"))); // NOI18N
        buttonCancel.setBorderPainted(false);
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setFocusable(false);
        buttonCancel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cancelpush.png"))); // NOI18N
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });
        colorSelect.getContentPane().add(buttonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 140, -1));

        buttonGreen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/green.png"))); // NOI18N
        buttonGreen.setBorderPainted(false);
        buttonGreen.setContentAreaFilled(false);
        buttonGreen.setFocusable(false);
        buttonGreen.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/greenpush.png"))); // NOI18N
        buttonGreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGreenActionPerformed(evt);
            }
        });
        colorSelect.getContentPane().add(buttonGreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 270, -1));

        buttonYellow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/yellow.png"))); // NOI18N
        buttonYellow.setBorderPainted(false);
        buttonYellow.setContentAreaFilled(false);
        buttonYellow.setFocusable(false);
        buttonYellow.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/yellowpush.png"))); // NOI18N
        buttonYellow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonYellowActionPerformed(evt);
            }
        });
        colorSelect.getContentPane().add(buttonYellow, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 270, -1));

        buttonRed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/red.png"))); // NOI18N
        buttonRed.setBorderPainted(false);
        buttonRed.setContentAreaFilled(false);
        buttonRed.setFocusable(false);
        buttonRed.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/redpush.png"))); // NOI18N
        buttonRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRedActionPerformed(evt);
            }
        });
        colorSelect.getContentPane().add(buttonRed, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 270, -1));

        colorSelLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        colorSelLabel.setForeground(new java.awt.Color(255, 217, 0));
        colorSelLabel.setText("Select a Color");
        colorSelLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        colorSelect.getContentPane().add(colorSelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 220, 40));

        bgLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bgLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bgblurred.jpg"))); // NOI18N
        bgLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        colorSelect.getContentPane().add(bgLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -70, 747, 540));

        getContentPane().add(colorSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 100, 530, 400));

        pauseScreen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        pauseScreen.setToolTipText("");
        pauseScreen.setVisible(false);
        pauseScreen.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rank4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rank4.setForeground(new java.awt.Color(255, 217, 0));
        rank4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rank4.setText("4th:");
        rank4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(rank4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 220, 40));

        rank3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rank3.setForeground(new java.awt.Color(255, 217, 0));
        rank3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rank3.setText("3rd:");
        rank3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(rank3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 230, 40));

        rank2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rank2.setForeground(new java.awt.Color(255, 217, 0));
        rank2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rank2.setText("2nd:");
        rank2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(rank2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 220, 40));

        rank1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rank1.setForeground(new java.awt.Color(255, 217, 0));
        rank1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        rank1.setText("1st:");
        rank1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(rank1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 220, 40));

        rank4name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rank4name.setForeground(new java.awt.Color(255, 217, 0));
        rank4name.setText("?");
        rank4name.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(rank4name, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, 110, 40));

        rank1name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rank1name.setForeground(new java.awt.Color(255, 217, 0));
        rank1name.setText("?");
        rank1name.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(rank1name, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 110, 40));

        rank2name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rank2name.setForeground(new java.awt.Color(255, 217, 0));
        rank2name.setText("?");
        rank2name.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(rank2name, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 110, 40));

        rank3name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        rank3name.setForeground(new java.awt.Color(255, 217, 0));
        rank3name.setText("?");
        rank3name.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(rank3name, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 110, 40));

        RankingLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        RankingLabel.setForeground(new java.awt.Color(255, 217, 0));
        RankingLabel.setText("Card Count");
        RankingLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(RankingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 160, 40));

        quitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/quitsmall.png"))); // NOI18N
        quitButton.setBorderPainted(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setFocusable(false);
        quitButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/quitsmallpush.png"))); // NOI18N
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });
        pauseScreen.getContentPane().add(quitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, -1, -1));

        resumeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/resume.png"))); // NOI18N
        resumeButton.setBorderPainted(false);
        resumeButton.setContentAreaFilled(false);
        resumeButton.setFocusable(false);
        resumeButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/resumepush.png"))); // NOI18N
        resumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resumeButtonActionPerformed(evt);
            }
        });
        pauseScreen.getContentPane().add(resumeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, -1));

        mmButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mm.png"))); // NOI18N
        mmButton.setBorderPainted(false);
        mmButton.setContentAreaFilled(false);
        mmButton.setFocusable(false);
        mmButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mmpush.png"))); // NOI18N
        mmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmButtonActionPerformed(evt);
            }
        });
        pauseScreen.getContentPane().add(mmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        bgLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bgLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bgblurred.jpg"))); // NOI18N
        bgLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pauseScreen.getContentPane().add(bgLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -70, 747, 540));

        getContentPane().add(pauseScreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 100, 530, 400));

        bgCover.setBackground(new java.awt.Color(0, 0, 0));
        bgCover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bgCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bggameblurred.jpg"))); // NOI18N
        bgCover.setMaximumSize(new java.awt.Dimension(900, 636));
        bgCover.setMinimumSize(new java.awt.Dimension(900, 636));
        bgCover.setOpaque(true);
        bgCover.setPreferredSize(new java.awt.Dimension(900, 636));
        bgCover.setVisible(false);
        getContentPane().add(bgCover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, -1));

        currentCard.setPreferredSize(new java.awt.Dimension(97, 136));
        currentCard.setLayout(new javax.swing.BoxLayout(currentCard, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(currentCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, -1, -1));

        deckButton.setBackground(new java.awt.Color(0, 0, 0));
        deckButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cards/card_back_alt.png"))); // NOI18N
        deckButton.setBorder(null);
        deckButton.setBorderPainted(false);
        deckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deckButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deckButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, -1, -1));

        hideButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/showhide.png"))); // NOI18N
        hideButton.setToolTipText("");
        hideButton.setBorderPainted(false);
        hideButton.setContentAreaFilled(false);
        hideButton.setEnabled(false);
        hideButton.setFocusable(false);
        hideButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/showhidePush.png"))); // NOI18N
        hideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideButtonActionPerformed(evt);
            }
        });
        getContentPane().add(hideButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 500, 120, -1));

        p1name.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        p1name.setForeground(new java.awt.Color(255, 217, 0));
        p1name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1name.setText("(Name)");
        getContentPane().add(p1name, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 530, 40));

        p3hand.setBackground(new java.awt.Color(255, 255, 255));
        p3hand.setBorder(null);
        p3hand.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        p3hand.setAutoscrolls(true);
        p3hand.setOpaque(false);
        p3hand.getViewport().setOpaque(false);
        p3hand.setOpaque(false);
        p3handcontainer.setOpaque(false);

        p3handcontainer.setOpaque(false);
        p3handcontainer.setLayout(new javax.swing.BoxLayout(p3handcontainer, javax.swing.BoxLayout.X_AXIS));
        p3hand.setViewportView(p3handcontainer);

        getContentPane().add(p3hand, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -20, 520, 130));

        p4hand.setBackground(new java.awt.Color(255, 255, 255));
        p4hand.setBorder(null);
        p4hand.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        p4hand.setAutoscrolls(true);
        p4hand.setOpaque(false);
        p4hand.getViewport().setOpaque(false);
        p4hand.setOpaque(false);
        p4handcontainer.setOpaque(false);

        p4handcontainer.setOpaque(false);
        p4handcontainer.setLayout(new javax.swing.BoxLayout(p4handcontainer, javax.swing.BoxLayout.Y_AXIS));
        p4hand.setViewportView(p4handcontainer);

        getContentPane().add(p4hand, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 110, 320));

        p2hand.setBackground(new java.awt.Color(255, 255, 255));
        p2hand.setBorder(null);
        p2hand.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        p2hand.setAutoscrolls(true);
        p2hand.setOpaque(false);
        p2hand.getViewport().setOpaque(false);
        p2hand.setOpaque(false);
        p2handcontainer.setOpaque(false);

        p2handcontainer.setOpaque(false);
        p2handcontainer.setLayout(new javax.swing.BoxLayout(p2handcontainer, javax.swing.BoxLayout.Y_AXIS));
        p2hand.setViewportView(p2handcontainer);

        getContentPane().add(p2hand, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, 110, 320));

        p1hand.setBackground(new java.awt.Color(255, 255, 255));
        p1hand.setBorder(null);
        p1hand.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        p1hand.setAutoscrolls(true);
        p1hand.setOpaque(false);
        p1hand.getViewport().setOpaque(false);
        p1hand.setOpaque(false);
        p1handcontainer.setOpaque(false);

        p1handcontainer.setOpaque(false);
        p1handcontainer.setLayout(new javax.swing.BoxLayout(p1handcontainer, javax.swing.BoxLayout.LINE_AXIS));
        p1hand.setViewportView(p1handcontainer);

        getContentPane().add(p1hand, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 487, 520, 130));

        showButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/show.png"))); // NOI18N
        showButton.setToolTipText("");
        showButton.setBorderPainted(false);
        showButton.setContentAreaFilled(false);
        showButton.setFocusable(false);
        showButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/showpush.png"))); // NOI18N
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });
        getContentPane().add(showButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 250, -1));

        colorName.setForeground(new java.awt.Color(255, 217, 0));
        colorName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        colorName.setText("(Color)");
        getContentPane().add(colorName, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 100, -1));

        p4name.setForeground(new java.awt.Color(255, 217, 0));
        p4name.setText("(Name)");
        getContentPane().add(p4name, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        p3name.setForeground(new java.awt.Color(255, 217, 0));
        p3name.setText("(Name)");
        getContentPane().add(p3name, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, -1, -1));

        p2name.setForeground(new java.awt.Color(255, 217, 0));
        p2name.setText("(Name)");
        getContentPane().add(p2name, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, -1, -1));

        pauseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pause.png"))); // NOI18N
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusable(false);
        pauseButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pausepush.png"))); // NOI18N
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });
        getContentPane().add(pauseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 120, 40));

        turnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/turn.png"))); // NOI18N
        turnButton.setAlignmentX(0.5F);
        turnButton.setBorderPainted(false);
        turnButton.setContentAreaFilled(false);
        turnButton.setEnabled(false);
        turnButton.setFocusable(false);
        turnButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        turnButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/turnpush.png"))); // NOI18N
        turnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turnButtonActionPerformed(evt);
            }
        });
        getContentPane().add(turnButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 550, 120, -1));

        unoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/uno.png"))); // NOI18N
        unoButton.setBorderPainted(false);
        unoButton.setContentAreaFilled(false);
        unoButton.setEnabled(false);
        unoButton.setFocusable(false);
        unoButton.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/unopush.png"))); // NOI18N
        unoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoButtonActionPerformed(evt);
            }
        });
        getContentPane().add(unoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 120, 40));

        hudBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/hudbg.png"))); // NOI18N
        getContentPane().add(hudBG, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 1030, 180));

        bgLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bgClockwise.jpg"))); // NOI18N
        getContentPane().add(bgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void exitGame(){
        // Exit the game to the main menu after clicking button
        Menu m = new Menu();
        m.setVisible(true);
        dispose();
    }
    private void handleCard(Card current, List<Card> hand){
        // Compare and place card. Handle special cards appropriately
        if(!hand.equals(this.controller.getActivePlayer().getHand())){ // If you're clicking a card belonging to a non-active player.
            return;
        }
        else if(this.controller.getActivePlayer().hasPlayed()){
            return;
        }
        Card top = this.controller.getPile().get(this.controller.getPile().size()-1);
        if(current.getColor().equals(top.getColor()) || current.getValue() == top.getValue()){
            deckButton.setEnabled(false); // Placing a card, so cant draw another
            this.placeNewCard(current); // Place card onto pile
            hand.remove(current); // Remove card from hand
            if(current.getValue().equals("reverse"))
                this.controller.reverse(); // Reverse direction
            else if(current.getValue().equals("skip"))
                this.controller.setRotationFactor(2);
            else if(current.getValue().equals("d2")){ // Draw two
                this.controller.incrementDrawStack(2);
            }
            this.controller.getActivePlayer().setPlayed(true);
        }
        else if(current.getColor().equals("wild") && !this.controller.getActivePlayer().isNPC()){
            deckButton.setEnabled(false);
            this.placeNewCard(current);
            hand.remove(current);
            this.displayColorSelect();
            if(current.getValue().equals("d4") && this.controller.getPlayers().size() > 1)
                this.controller.incrementDrawStack(4);
            this.controller.getActivePlayer().setPlayed(true);
        }
        /*
        else if(current.getColor().equals("wild") && this.controller.getActivePlayer().isNPC()){
            int red = 0;
            int yell = 0;
            int blue = 0;
            int green = 0;
            this.updatePileButton(current);
            hand.remove(current);
            for(int i=0;i<this.controller.getActivePlayer().getHand().size();i++){
                switch(((Card)this.controller.getActivePlayer().getHand().get(i)).getColor()){
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
                this.controller.getTopCard().setColor("red");
            }
            else if(yell >= red && yell >= blue && red >=green){
                this.controller.getTopCard().setColor("yellow");
            }
            else if(blue >= red && blue >= yell && blue>=green){
                this.controller.getTopCard().setColor("blue");
            }
            else{
                this.controller.getTopCard().setColor("green");
            }
            this.refreshCardLabel(); // Update label to reflect new color
            if(this.controller.getTopCard().getValue().equals("d4"))
                this.controller.incrementDrawStack(4);
            this.controller.getActivePlayer().setPlayed(true);            
        }*/
    }
    private void displayColorSelect(){
        // Hide everything to show the color selection overlay
        // Need to hide hands since they can show through bgCover with mouse hover
        bgCover.setVisible(true);
        p1hand.setVisible(false); 
        p2hand.setVisible(false);
        p3hand.setVisible(false);
        p4hand.setVisible(false);
        colorSelect.setVisible(true);
    }
    private void refreshCardLabel(){
        // Update label above pile to reflect current card state
        this.colorName.setText(this.controller.getPile().get(this.controller.getPile().size()-1).getColor() + " " + this.controller.getPile().get(this.controller.getPile().size()-1).getValue());
    }
    private void refreshBgArrow(){
        // Update BG image for rotation direction
        if(this.controller.isClockwise()){
            this.bgLabel.setIcon(new ImageIcon(this.getClass().getResource("/resources/bgClockwise.jpg")));
        }
        else{
            this.bgLabel.setIcon(new ImageIcon(this.getClass().getResource("/resources/bgCounterclock.jpg")));
        }
    }
    private void placeNewCard(Card current){
        // Placing card onto pile
        currentCard.removeAll(); // Need to do this to place new button
        current.setButton(new JButton(new ImageIcon(this.getClass().getResource(current.getImage()))));
        JButton b = (JButton)current.getButton();
        b.setBorder(null); // Remove weird borders around button
        currentCard.add(b);
        currentCard.revalidate();
        currentCard.repaint();
        this.controller.addToPile(current);
        this.refreshCardLabel();
    }   

    private void nextPlayer(){
        this.controller.endTurn(true);
    }
    

    private void refreshPlayerUI(){
        // For updating various UI elements
        this.refreshBgArrow();
        //Turn Button:
        if(!this.controller.getActivePlayer().isNPC()){
            // If not NPC
            // If has played card vs hasnt enable turn button
            if(this.controller.getActivePlayer().hasPlayed() || !deckButton.isVisible())
                turnButton.setEnabled(true);
            else
                turnButton.setEnabled(false);
        }
        else{ // TEMPORARY!! - IF NPC, HAVE TURN BUTTON ENABLED
            turnButton.setEnabled(true);
        }
        // UNO Button - Enabled when active player has 1 card
        if(this.controller.getActivePlayer().getHand().size()==1){
            unoButton.setEnabled(true);
        }
        else{
            unoButton.setEnabled(false);
        }
        // For updating the Hand UI and player names
        JLabel[] names = new JLabel[]{p1name,p2name,p3name,p4name}; //hand and names must be same size
        JPanel[] containers = new JPanel[]{p1handcontainer,p2handcontainer,p3handcontainer,p4handcontainer};
        List p = this.controller.getPlayers();
        for (int i=0;i<names.length;i++){  
            containers[i].removeAll(); //remove other player's cards
            if(i>=p.size()){
                names[i].setText("");
                continue;
            }
            Player uiPlayer  = ((Player)p.get(i)); //get player at current ordering index
            String NPC = uiPlayer.isNPC() ? "(NPC)" : "";
            names[i].setText(uiPlayer.getName() + " " + NPC); //update UI name display text
            List<Card> phand = uiPlayer.getHand(); //get hand of cards from player          
            for(int j=0;j<phand.size();j++){
                String image;
                if(i==0){ //if visible, current player
                    image = phand.get(j).getImage();
                    names[i].setText("Current Player: " + uiPlayer.getName() + " " + NPC);
                }
                else{
                    image = "/resources/cards/card_back_alt.png";
                }
                try{
                    phand.get(j).setButton((Object)new JButton(new ImageIcon(this.getClass().getResource(image))));
                }catch(NullPointerException e){
                    System.out.println("ERROR" + " " + this.getClass().getResource(image) + " " + image);
                }
                JButton b = (JButton)(phand.get(j).getButton());
                b.setBorder(null);
                final int current = j;
                // Generating hand buttons with equivilant of onClick methods
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(phand.get(current));
                        handleCard(phand.get(current),phand);
                        refreshPlayerUI();
                    }
                });
                containers[i].add(b);
            }
            containers[i].revalidate();
            containers[i].repaint();
        }
    }
    private void showOrHideElements(){
        // Controlling when active hand is visible/hidden + show/hide buttons
        if(((Player)(this.controller.getPlayers().get(0))).isNPC()){
            p1hand.setVisible(false); 
            hideButton.setEnabled(false);
            showButton.setVisible(false);
            deckButton.setEnabled(false);
        }
        else{                
            if(this.controller.getActivePlayer().hasPlayed()){
                p1hand.setVisible(true);
                deckButton.setEnabled(false);
                hideButton.setEnabled(true);
                showButton.setVisible(false);
            }
            else{
                p1hand.setVisible(false);
                deckButton.setEnabled(true);
                hideButton.setEnabled(false);
                showButton.setVisible(true);
            }
        }
    }

    private void hideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideButtonActionPerformed
        //this.showOrHideElements();
        this.p1hand.setVisible(false);
        this.hideButton.setEnabled(false);
        this.showButton.setVisible(true);
    }//GEN-LAST:event_hideButtonActionPerformed

    private void unoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoButtonActionPerformed
        if(this.controller.getActivePlayer().isNPC()){ 
            // If you catch an NPC before they end their turn, they draw 2
            this.drawFromDeck(2);
        }
        else{
            // Have yet to verify if this is necessary
            this.controller.getActivePlayer().setPlayed(false);
        }
        this.nextPlayer(); // Ends turn
    }//GEN-LAST:event_unoButtonActionPerformed

    private void mmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmButtonActionPerformed
        // Main menu button
        Menu m = new Menu();
        m.setVisible(true);
        dispose();
    }//GEN-LAST:event_mmButtonActionPerformed

    private void drawFromDeck(int count){
        // Draws from deck and adds new card(s) to hand
        this.controller.drawCards(count);
        if(this.controller.getDeck().deckSize() == 0){
            deckButton.setVisible(false);
        }
        // If the single drawn card is playable, then the player can play it (or any other cards technically)
        // Will see if this is exploitable, but cards are generally drawn when no other cards are playable
        //if(count == 1 && this.isPlayableCard(c)){
        //    this.controller.getActivePlayer().setPlayed(false);
        //}
        this.turnButton.setEnabled(true);
        this.refreshPlayerUI();
    }
    private void deckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckButtonActionPerformed
        // Draw a new card
        this.controller.getActivePlayer().setPlayed(true);
        deckButton.setEnabled(false); // Disable deck button since it's been used once
        drawFromDeck(1);
    }//GEN-LAST:event_deckButtonActionPerformed

    private void turnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turnButtonActionPerformed
        // For ending turns and switching players
        this.controller.getActivePlayer().setPlayed(false);
        //List<Player> npcs = this.controller.getActiveNPCs();
        // If player forgets to click uno and instead clicks end turn, they have to draw 2,
        // LOOKING INTO ALTERNATIVE OPTIONS here - maybe should be by chance based on difficulty
        if(this.controller.getActivePlayer().getHand().size() == 1 &&
                !this.controller.getActivePlayer().isNPC()){
            this.drawFromDeck(2);
        }
        this.nextPlayer();
    }//GEN-LAST:event_turnButtonActionPerformed

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed
        p1hand.setVisible(true);
        showButton.setVisible(false);
        hideButton.setEnabled(true);
    }//GEN-LAST:event_showButtonActionPerformed

    private void displayRanking(){
        // Basically a card count.
        JLabel[] labels = new JLabel[]{rank1,rank2,rank3,rank4};
        JLabel[] labelnames = new JLabel[]{rank1name,rank2name,rank3name,rank4name};
        for(int i=0;i<this.controller.getPlayers().size();i++){
            Player p = this.controller.getPlayers().get(i);
            String NPC = p.isNPC() ? "(NPC)" : "";
            labels[i].setText(p.getName() + " " + NPC);
            labelnames[i].setText(Integer.toString(p.getHand().size()));
        }
    }
    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        // Pause the game rotation and show the pause screen + rankings
        this.controller.pauseGame(true);
        bgCover.setVisible(true);
        currentCard.setVisible(false);
        p1hand.setVisible(false);
        p1handcontainer.setVisible(false);
        p2hand.setVisible(false);
        p3hand.setVisible(false);
        p4hand.setVisible(false);
        pauseScreen.setVisible(true);
        this.displayRanking();
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void resumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resumeButtonActionPerformed
        this.controller.pauseGame(false);
        this.resumeGame();
        pauseScreen.setVisible(false);
        currentCard.setVisible(true);
    }//GEN-LAST:event_resumeButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed
    private void resumeGame(){
        p1handcontainer.setVisible(true);
        if(!this.controller.getActivePlayer().isNPC()){
            showButton.setVisible(true);
            hideButton.setEnabled(false);
        }
        bgCover.setVisible(false);
        p2hand.setVisible(true);
        p3hand.setVisible(true);
        p4hand.setVisible(true);
    }
    private void buttonRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRedActionPerformed
        this.colorButtonAction("red");
    }//GEN-LAST:event_buttonRedActionPerformed

    private void buttonGreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGreenActionPerformed
        this.colorButtonAction("green");
    }//GEN-LAST:event_buttonGreenActionPerformed

    private void buttonBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBlueActionPerformed
        this.colorButtonAction("blue");
    }//GEN-LAST:event_buttonBlueActionPerformed

    private void buttonYellowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonYellowActionPerformed
        this.colorButtonAction("yellow");
    }//GEN-LAST:event_buttonYellowActionPerformed
    private void colorButtonAction(String color){
        // Resume game after selecting a color, set color of wild card
        this.resumeGame();
        this.colorSelect.setVisible(false);
        this.controller.getPile().get(this.controller.getPile().size()-1).setColor(color);
        this.refreshCardLabel(); // Update label to reflect new color
    }
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        // Canceling a wild card
        this.resumeGame(); // Resume game
        p1hand.setVisible(true);
        // Undo playing of wild card and re-add it to player's hand
        this.controller.getActivePlayer().setPlayed(false);
        this.colorSelect.setVisible(false);
        deckButton.setEnabled(true);
        // Replace card on pile before wild card
        Card top = this.controller.getPile().get(this.controller.getPile().size()-1);
        if(top.getValue().equals("d4")){
            this.controller.incrementDrawStack(-4);
        }
        this.controller.getActivePlayer().getHand().add(top);
        this.controller.getPile().remove(this.controller.getPile().size()-1);
        this.refreshPlayerUI();
        this.placeNewCard(this.controller.getPile().get(this.controller.getPile().size()-1));
    }//GEN-LAST:event_buttonCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameScreen().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RankingLabel;
    private javax.swing.JLabel bgCover;
    private javax.swing.JLabel bgLabel;
    private javax.swing.JLabel bgLabel4;
    private javax.swing.JLabel bgLabel5;
    private javax.swing.JButton buttonBlue;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonGreen;
    private javax.swing.JButton buttonRed;
    private javax.swing.JButton buttonYellow;
    private javax.swing.JLabel colorName;
    private javax.swing.JLabel colorSelLabel;
    private javax.swing.JInternalFrame colorSelect;
    private javax.swing.JPanel currentCard;
    private javax.swing.JButton deckButton;
    private javax.swing.JToggleButton hideButton;
    private javax.swing.JLabel hudBG;
    private javax.swing.JButton mmButton;
    private javax.swing.JScrollPane p1hand;
    private javax.swing.JPanel p1handcontainer;
    private javax.swing.JLabel p1name;
    private javax.swing.JScrollPane p2hand;
    private javax.swing.JPanel p2handcontainer;
    private javax.swing.JLabel p2name;
    private javax.swing.JScrollPane p3hand;
    private javax.swing.JPanel p3handcontainer;
    private javax.swing.JLabel p3name;
    private javax.swing.JScrollPane p4hand;
    private javax.swing.JPanel p4handcontainer;
    private javax.swing.JLabel p4name;
    private javax.swing.JButton pauseButton;
    private javax.swing.JInternalFrame pauseScreen;
    private javax.swing.JButton quitButton;
    private javax.swing.JLabel rank1;
    private javax.swing.JLabel rank1name;
    private javax.swing.JLabel rank2;
    private javax.swing.JLabel rank2name;
    private javax.swing.JLabel rank3;
    private javax.swing.JLabel rank3name;
    private javax.swing.JLabel rank4;
    private javax.swing.JLabel rank4name;
    private javax.swing.JButton resumeButton;
    private javax.swing.JToggleButton showButton;
    private javax.swing.JButton turnButton;
    private javax.swing.JButton unoButton;
    // End of variables declaration//GEN-END:variables
}
