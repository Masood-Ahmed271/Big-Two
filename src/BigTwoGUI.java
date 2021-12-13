// Libraries being Used
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;




/**
 * This is BigTwoGUI class which builds a GUI for the game and handles all
 * the user actions. This class implements the CardGameUI interface and has
 * a number of inner classes responsible for rendering the user interface and
 * handling user actions.
 *   
 * @author Masood Ahmed
 * @version 2.0 
 *
 */
public class BigTwoGUI implements CardGameUI{
	
	// *************** Instance Variables *************************************
	

	private BigTwo game;         // A Big Two card game associates with this GUI. 

	private boolean[] selected;  //a boolean array indicating which cards are being selected.
	
	private int activePlayer;    // An integer specifying the index of the active player.
	 
	private JFrame frame;        // The main window of the application.
	
	private JPanel bigTwoPanel;  // a panel for showing the cards of each player and the cards played
	                             // on the table.

	private JButton playButton;  // A “Play” button for the active player to play the selected cards.
	
	private JButton passButton;  // A “Pass” button for the active player to pass his/her turn to the 
	                             // next player. 

	private JTextArea msgArea;   // A text area for showing the current game status as well as end of 
	                             // game messages.
	
	private JTextArea chatArea;  // A text area for showing chat messages sent by the players. 
	
	private JTextField chatInput; // A text field for players to input chat messages. 
	
	 // My added instance variables 
	

	private JPanel rightPanel;       //a right panel to setup the message and chat area

	private Image[][] imagesOfCards; // A 2-dimensional array storing images of the 52 playing poker cards.

	private Image backOfCard;     // This instance variable stores the image of the back of the card.

	private Image[] playerAvatars;   // An array to store avatars of the players
	
	private Image emptyAvatar;      //an image for empty player slot
	
	private Image imageOfLogo;       // An instance variable that saves the image of the icon of the application
	
	private JPanel chatInputPanel;  // A panel that holds text-field and label.
	
	private PlayButtonListener playButtonListener;  // This is the listener for the Play Button
	
	private PassButtonListener passButtonListener;  // This is the listener for the Pass Button
	
	
	// *************************************************************************
	
	// *************** Big Two Constructor *************************************
	
	/**
	 * 
	 * A constructor for creating a BigTwoGUI. The parameter game is a
	 * reference to a Big Two card game associates with this GUI.
	 * 
	 * @param game a reference to a card game associates with this table.
	 * 
	 */
	
	public BigTwoGUI(BigTwo game) {
		this.game = game;   // create the game object
		LoadImages();       //To load necessary images
		SettingGUI();     // Setup GUI
		
	}
	
	// *************************************************************************
	
	
	// *************** Public Methods of the class *************************************

	
	/**
	 * A method for setting the index of the 
	 * active player (i.e. the player having control of the GUI).  
	 * 
	 * @param activePlayer  an integer value representing the index of the active player
	 *            
	 */
	
	@Override
	public void setActivePlayer(int activePlayer) {

		if (activePlayer < 0 || activePlayer >= game.getPlayerList().size()) {
			this.activePlayer = -1;
		} else {
			this.activePlayer = activePlayer;     // setting active Player
		}
	}

	/**
	 * A method for repainting the GUI.
	 *   
	 */
	
	@Override
	public void repaint() {
		
		// setting the content pane
		frame.setContentPane(new JPanel(new GridLayout(1,2)));
		
		// Initialize the menu-bar for the frame, including the menus and the menu-items.
		JMenuBar menuBar = new JMenuBar();
		JMenu menuGame = new JMenu("Game");
		JMenuItem quitItem, connectItem;
		quitItem = new JMenuItem("Quit");
		connectItem = new JMenuItem("Connect");
		
		// Adding listener
		ConnectMenuItemListener connectListener = new ConnectMenuItemListener();
		QuitMenuItemListener quitListener = new QuitMenuItemListener();
		connectItem.addActionListener(connectListener);
		quitItem.addActionListener(quitListener);
		
		// Add the menus items to the menus and the menu-bars
		menuGame.add(connectItem); menuGame.add(quitItem); 
		menuBar.add(menuGame);
		frame.setJMenuBar(menuBar);
		
		// Adding Message component
		JMenu menuMessage = new JMenu("Message");
		JMenuItem clearChat = new JMenuItem("Clear Chat Box");
		ClearChatListener clearListener = new ClearChatListener();
		clearChat.addActionListener(clearListener);
		menuMessage.add(clearChat);
		menuBar.add(menuMessage);
		
		// Setting BigTwoPanel background and layout
		bigTwoPanel = new TableBackground("arena/CardGameTable.jpg");
		bigTwoPanel.setLayout(new BorderLayout());
		
		// Panel for buttons
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1,2));
		
		// Add the the initialized buttons to the bottom panel
		bottom.add(playButton); bottom.add(passButton);
		
		// enabling and disabling the game
		if(game.getCurrentPlayerIdx() == activePlayer) {
			enable();
		}
		else {
			disable();
		}
		
		// Adding bottom panel to the south of the layout
		bigTwoPanel.add(bottom, BorderLayout.SOUTH);
		
		
		//players panel to have each player # panel
		JPanel playerPanel;
		playerPanel = new JPanel();
		playerPanel.setBackground(new Color(0, 0, 0, 0));
		playerPanel.setLayout(new GridLayout(5, 1));
		
		
		//printing things on the GUI message accordingly.
		if(game.endOfGame() != true  && game.getStarted() == true) {
			resetSelected();
			if(game.getCurrentPlayerIdx() == activePlayer) {
				promptActivePlayer();
			}
			else {
				printMsg(game.getPlayerList().get(game.getCurrentPlayerIdx()).getName() + "'s turn:");
			}
		}
		else {
			resetSelected();
			disable();
		}
		
		//player 0 panel
		JPanel player0Panel; JPanel player1Panel; JPanel player2Panel; JPanel player3Panel;
		if(game.getPlayerList().get(0).getName() != null && game.getPlayerList().get(0).getName() != "") {
			player0Panel = new BigTwoPanel(playerAvatars[0]);
		}
		else {
			player0Panel = new BigTwoPanel(emptyAvatar);
		}
		player0Panel.setBackground(new Color(213, 134, 145, 50));
		player0Panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		player0Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel player0Label = new JLabel();
		if(game.getPlayerList().get(0).getName() != null) {
			if(game.getClient().getPlayerID() == 0) {
				player0Label.setText("You");
			}
			else {
				player0Label.setText(game.getPlayerList().get(0).getName());
			}
		}
		player0Label.setForeground(Color.WHITE);
		player0Panel.add(player0Label);
		JLayeredPane layeredPane0 = new JLayeredPane();
		layeredPane0.setPreferredSize(new Dimension(1000, 1000));
		printerCardPanel(0, layeredPane0, player0Panel);
		
		
		//player 1 panel
		if(game.getPlayerList().get(1).getName() != null && game.getPlayerList().get(1).getName() != "") {
			player1Panel = new BigTwoPanel(playerAvatars[1]);
		}
		else {
			player1Panel = new BigTwoPanel(emptyAvatar);
		}
		player1Panel.setBackground(new Color(0, 134, 145, 50));
		player1Panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		player1Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel player1Label = new JLabel();
		if(game.getPlayerList().get(1).getName() != null) {
			if(game.getClient().getPlayerID() == 1) {
				player1Label.setText("You");
			}
			else {
				player1Label.setText(game.getPlayerList().get(1).getName());
			}
		}
		player1Label.setForeground(Color.WHITE);
		player1Panel.add(player1Label);
		JLayeredPane layeredPane1 = new JLayeredPane();
		layeredPane1.setPreferredSize(new Dimension(1000, 1000));
		printerCardPanel(1, layeredPane1, player1Panel);
		
		
		//player 2 panel
		if(game.getPlayerList().get(2).getName() != null && game.getPlayerList().get(2).getName() != "") {
			player2Panel = new BigTwoPanel(playerAvatars[2]);
		}
		else {
			player2Panel = new BigTwoPanel(emptyAvatar);
		}
		player2Panel.setBackground(new Color(213, 0, 145, 50));
		player2Panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		player2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel player2Label = new JLabel();
		if(game.getPlayerList().get(2).getName() != null) {
			if(game.getClient().getPlayerID() == 2) {
				player2Label.setText("You");
			}
			else {
				player2Label.setText(game.getPlayerList().get(2).getName());
			}
		}
		player2Label.setForeground(Color.WHITE);
		player2Panel.add(player2Label);
		JLayeredPane layeredPane2 = new JLayeredPane();
		layeredPane2.setPreferredSize(new Dimension(1000, 1000));
		printerCardPanel(2, layeredPane2, player2Panel);
		
		
		//player 3 panel
		if(game.getPlayerList().get(3).getName() != null && game.getPlayerList().get(3).getName() != "") {
			player3Panel = new BigTwoPanel(playerAvatars[3]);
		}
		else {
			player3Panel = new BigTwoPanel(emptyAvatar);
		}
		player3Panel.setBackground(new Color(213, 134, 0, 50));
		player3Panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		player3Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel player3Label = new JLabel();
		if(game.getPlayerList().get(3).getName() != null) {
			if(game.getClient().getPlayerID() == 3) {
				player3Label.setText("You");
			}
			else {
				player3Label.setText(game.getPlayerList().get(3).getName());
			}
		}
		player3Label.setForeground(Color.WHITE);
		player3Panel.add(player3Label);
		JLayeredPane layeredPane3 = new JLayeredPane();
		layeredPane3.setPreferredSize(new Dimension(1000, 1000));
		printerCardPanel(3, layeredPane3, player3Panel);
		
		
		//table panel
		JPanel table = new JPanel();
		table.setBackground(new Color(213, 134, 0, 0));
		table.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		table.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel tableLabel = new JLabel();
		tableLabel.setForeground(Color.WHITE);
		table.add(tableLabel);
		JLayeredPane layeredPaneTable = new JLayeredPane();
		Hand lastHandOnTable;
		if(game.getHandsOnTable().isEmpty()) {
			lastHandOnTable = null;
			tableLabel.setText("Empty");
		}
		else {
			lastHandOnTable = game.getHandsOnTable().get(game.getHandsOnTable().size() - 1);
			tableLabel.setText("Played by " + lastHandOnTable.getPlayer().getName());
		}
		layeredPaneTable.setPreferredSize(new Dimension(1000, 1000));
		if(lastHandOnTable != null) {
			for(int i = 0; i < lastHandOnTable.size(); i ++) {
				CardPanel cardPanel;
				Card card = lastHandOnTable.getCard(i);
				int x = 20 + 27 * i;
				int y = 5;
				cardPanel = new CardPanel(imagesOfCards[card.getSuit()][card.getRank()], x, y, activePlayer, card, i);	
				cardPanel.setBounds(x, y, 80, 110);
				layeredPaneTable.add(cardPanel, 0);
			}
			table.add(layeredPaneTable);
		}
		
		// Adding player panels to playerPanel
		playerPanel.add(player0Panel);
		playerPanel.add(player1Panel);
		playerPanel.add(player2Panel);
		playerPanel.add(player3Panel);
		playerPanel.add(table);
		
		// Adding playerPanel to bigTwoPanel
		bigTwoPanel.add(playerPanel);
		
		// Adding bigTwoPanel to frame
		frame.add(bigTwoPanel);
		
		// Adding scrollers
		JScrollPane msgScrollPane1 = new JScrollPane(msgArea);
		JScrollPane chatScrollPane2 = new JScrollPane(chatArea);
		

		// To add message and chat area
		JPanel otherPanel = new JPanel();
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(2,1));
		rightPanel.add(msgScrollPane1);
		rightPanel.add(chatScrollPane2);
		otherPanel.setLayout(new BorderLayout());
		otherPanel.add(rightPanel);
		otherPanel.add(chatInputPanel, BorderLayout.SOUTH);
		frame.add(otherPanel);
		msgArea.setCaretPosition(msgArea.getDocument().getLength());
		chatArea.setCaretPosition(chatArea.getDocument().getLength());
		
		// making the panel visible
		frame.setVisible(true);
	}
	
	/**
	 * A method for printing the specified string to the message area of the GUI.
	 * 
	 * @param msg  the string to be printed to the message area of the Big Two Game          
	 *            
	 */
	
	@Override
	public void printMsg(String msg) {

		msgArea.append(msg + "\n");
	}
	
	/**
	 * A method for clearing the message area of the GUI. 
	 *  
	 */
	@Override
	public void clearMsgArea() {

		msgArea.setText(null);
	}
	
	/**
	 * A method for printing messages on the chat area of the GUI.
	 * 
	 * @param msg  the string to be printed to the chat area of the Big Two Game 
	 */
	
	public void printChatMsg(String msg) {
		chatArea.append(msg + "\n");
	}
	
	/**
	 * A method for clearing the chat area of the GUI. 
	 *  
	 */
	
	public void clearChatMsgArea() {

		chatArea.setText(null);
	}
	
	/**
	 * A method for resetting the GUI. You should 
	 * (i) reset the list of selected cards; 
	 * (ii) clear the message area; and 
	 * (iii) enable user interactions.
	 * 
	 */
	
	@Override
	public void reset() {

		resetSelected(); // (i)
		clearMsgArea();  // (ii)
		enable();        // (iii)
	}
	
	
	/**
	 * A method for enabling user interactions with the GUI. You should 
	 * (i) enable the “Play” button and “Pass” button (i.e., making them click-able); 
	 * (ii) enable the BigTwoPanel for selection of cards through mouse clicks.
	 * 
	 */
	
	@Override
	public void enable() {
		
		playButton.setEnabled(true);  // (i)
		passButton.setEnabled(true);  // (i)
		bigTwoPanel.setEnabled(true); // (iii)
	}
	
	
	/**
	 * A method for disabling user interactions with the GUI.  You should 
	 * (i) disable the “Play” button and “Pass” button (i.e. making them not click-able);  
	 * (ii) disable the BigTwoPanel for selection of cards through mouse clicks.
	 *  
	 */
	
	@Override
	public void disable() {
		
		playButton.setEnabled(false);  // (i)
		passButton.setEnabled(false);  // (i)
		bigTwoPanel.setEnabled(false); // (ii)
		
	}
	
	/**
	 *  A method to display A message in the message area showing it 
	 *  is the the player’s turn.
	 *  
	 */
	
	@Override
	public void promptActivePlayer() {
		
		printMsg("Your turn:");
		
	}
	
	
	// **********************************************************************************
	
	// *************** Private Methods of the class *************************************
	
	
    // A method to load and put essential Images
	private void LoadImages() {
		
		// To put Avatars in the list
		playerAvatars = new Image[4];
		
		playerAvatars[0] = new ImageIcon("avatars/Ash.png").getImage();
		playerAvatars[1] = new ImageIcon("avatars/Goku.png").getImage();
		playerAvatars[2] = new ImageIcon("avatars/Joker.png").getImage();
		playerAvatars[3] = new ImageIcon("avatars/Xmen.png").getImage();
		
		// Putting image of Empty avatar
		emptyAvatar = new ImageIcon("avatars/empty.png").getImage();
		
		// Setting up the ImageIcon
		imageOfLogo = new ImageIcon("avatars/LOGOICON.png").getImage();  // creating an ImageIcon object
		
		// To Put card images in the 2-dimensional list
		imagesOfCards = new Image[4][13];
		for(int i = 0; i < 4; i ++) {
			for (int j = 0; j < 13; j++) {
				Image source = new ImageIcon("cards/" + i + "-" + j + ".png").getImage();
				Image resized = resizer(source, 80, 110);
				imagesOfCards[i][j] = new ImageIcon(resized).getImage();
			}
		}
		
		// To put Image of the back of the card
		Image cardBackSource = new ImageIcon("cards/backcard.png").getImage();
		backOfCard = new ImageIcon(resizer(cardBackSource, 80, 110)).getImage();
		
	}
	

	// A private method to setup the Graphical User Interface for the game
	private void SettingGUI() {
	
	// Create the frame for the GUI
	frame = new JFrame("Big Two Game");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setIconImage(imageOfLogo);  //change icon of frame
	frame.setLocation(130, 100);      // setting the position on the screen
	frame.setSize(new Dimension(1200, 850));   // giving the frame size
	GridLayout layout = new GridLayout(1,2);
	frame.setLayout(layout);   // Setting GridLayout
	
	// Initialize the message area and it's properties
	msgArea = new JTextArea("Welcome to the BigTwo game!\nFor the best experience, do not resize the screen.\nHave fun playing this game.\n");
	msgArea.setFont(new Font("Titillium", Font.PLAIN, 15));
	msgArea.setLineWrap(true);
	msgArea.setEditable(false);
	msgArea.setBackground(new Color(178,255,255));;

			
			
	// Initialize the chat area and its properties.
	chatArea = new JTextArea("Welcome to the Chat Box\n");
	chatArea.setLineWrap(true);
	chatArea.setEditable(false);
    Font font = new Font("Segoe Script", Font.PLAIN, 15);
    chatArea.setFont(font);
    chatArea.setBackground(Color.LIGHT_GRAY);
    
    // Initialize the panel for chat input
    chatInputPanel = new JPanel();
    chatInputPanel.setLayout(new FlowLayout());
    JLabel chatLabel = new JLabel("Message: ");
 		
    chatInput = new JTextField();
 		
    // Adding listener for chat panel
    chatInput.addActionListener(new ChatInputActionListener());
    chatInput.setPreferredSize(new Dimension(310,30));
 		
    chatInputPanel.add(chatLabel);
    chatInputPanel.add(chatInput);
    chatInputPanel.setMaximumSize(new Dimension(400,30));
    
			
    // Initialize the play and pass buttons for the GUI
	playButton = new JButton("Play"); passButton = new JButton("Pass");
	
	// Initialize the listener for the buttons
	playButtonListener = new PlayButtonListener();
	passButtonListener = new PassButtonListener();
	
	// Add the listener for the buttons.
	playButton.addActionListener(playButtonListener);
	passButton.addActionListener(passButtonListener);
			
	}
	
	//a method to resize an image
	private Image resizer(Image source, int width, int height) {
		
		source = new ImageIcon(source).getImage();
		Image resized = source.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return resized;
		
	}
	
	//a method to print the cards of each player on each player panel
	private void printerCardPanel(int playerIdx, JLayeredPane layeredPane, JPanel eachPlayerPanel) {
		
		// getting number of cards in hand
		int numOfCardsInHand = game.getPlayerList().get(playerIdx).getNumOfCards();
		
		for(int i = 0; i < numOfCardsInHand; i ++) {
			CardPanel button;
			Card card = game.getPlayerList().get(playerIdx).getCardsInHand().getCard(i);
			int x = 170 + 27 * i;
			int y = 20;
			//opens up the cards of active player and makes them click-able
			if(playerIdx == activePlayer && game.getStarted() == true) { 
				button = new CardPanel(imagesOfCards[card.getSuit()][card.getRank()], x, y, playerIdx, card, i);
				if(playerIdx == game.getCurrentPlayerIdx()) {
					button.addMouseListener(button);
				}
			}
			else {
				//opens up all cards when the game ends
				if(game.endOfGame() == true) { 
					button = new CardPanel(imagesOfCards[card.getSuit()][card.getRank()], x, y, playerIdx, card, i);
				}
				//close all cards for all non active players
				else { 
					button = new CardPanel(backOfCard, x, y, playerIdx, card, i);
				}
			}
			button.setBounds(x, y, 80, 110);
			layeredPane.add(button, 0);
		}
		eachPlayerPanel.add(layeredPane);
	}
	
	

	// A method that returns an array of indices of the cards selected.
	private int[] getSelected() {

		ArrayList<Integer> intSelected = new ArrayList<Integer>();
		for(int i = 0; i < selected.length; i++) {
			if(selected[i] == true) {
				intSelected.add(i);
			}
		}
		
		// Put the index of the selected cards into the initialized array
		int size = intSelected.size();
		int[] intArraySelected = new int[size];
		for(int i = 0; i <size; i++) {
			intArraySelected[i] = intSelected.get(i); 
		}
		return intArraySelected;
	}
	

	// A method that resets the list of selected cards to an empty list.
	private void resetSelected() {

		 // resets the selected cards
		selected = new boolean[game.getPlayerList().get(activePlayer).getNumOfCards()];
	}
	
	
	// ***********************************************************************************
	
	
	// *************** Inner Classes *****************************************************
	
	/**
	 * An inner class that extends JPanel and implements MouseListener.
	 * This class designs a card that is printed on the Graphical User Interface.
	 * printed on the GUI.
	 * 
	 * @author Masood Ahmed
	 *
	 */
	class CardPanel extends JPanel implements MouseListener{
		
		// Instance variables
		private boolean raised = false;
		private Image image;
		private int x;
		private int y;
		private int playerIdx;
		private Card card;
		private int index;
		
		// Constructor
		/**
		 * This is a constructor that allots values to the instance variabels
		 * 
		 * @param image Image of the card
		 * @param x  x-coordinate of the card
		 * @param y  y-coordinate of the card
		 * @param playerIdx Index of the player that holds the card
		 * @param card   Playing card object
		 * @param index  Playing card index
		 */
		public CardPanel(Image image, int x, int y, int playerIdx, Card card, int index) {
			this.card = card;
			this.index = index;
			this.playerIdx = playerIdx;
			this.x = x;
			this.y = y;
			this.image = image;
			Dimension size = new Dimension(80, 110);
			setPreferredSize(size);
			setMinimumSize(size);
	        setMaximumSize(size);
	        setSize(size);
		}
		
		// Methods
		/**
		 * The method to redraws the image of card.
		 * 
		 * @param g  the Graphics object to protect
		 * 
		 */
		@Override
		
		public void paintComponent(Graphics g) {
	        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

	    }
		
		/**
		 * This method raises the card if the card is clicked.
		 * 
		 * @param e A semantic event which indicates that a component-defined Mouse action occurred. 
		 * 
		 */
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(!raised) {
				setBounds(x, 0, 80, 110);
				selected[index] = true;
				raised = true;
			}
			else {
				setBounds(x, 20, 80, 110);
				selected[index] = false;
				raised = false;
			}

			frame.repaint();
		}
		
		/*
		 * This method does nothing in this class
		 * 
		 * @param e A semantic event which indicates that a component-defined Mouse action occurred. 
		 * 
		 */
		
		@Override
		public void mousePressed(MouseEvent e) {}
		
		/*
		 * This method does nothing in this class
		 * 
		 * @param e A semantic event which indicates that a component-defined Mouse action occurred. 
		 * 
		 */
		
		@Override
		public void mouseReleased(MouseEvent e) {}
		
		/*
		 * This method changes the border so that we can know which card is selected.
		 * 
		 * @param e A semantic event which indicates that a component-defined Mouse action occurred. 
		 * 
		 */
		
		@Override
		
		public void mouseEntered(MouseEvent e) {
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		
		/*
		 * This method removes border when mouse is not above the card.
		 * 
		 * @param e A semantic event which indicates that a component-defined Mouse action occurred. 
		 * 
		 */
		
		@Override
		public void mouseExited(MouseEvent e) {
			this.setBorder(null);
		}
	}
	
	/**
	 * An inner class that extends the JPanel class and implements the 
	 * MouseListener interface. Overrides the paintComponent() method inherited 
	 * from the JPanel class to draw the card game table.
	 *
	 * @author Masood Ahmed
	 * @version 2.0 
	 * 
	 */
	
	class BigTwoPanel extends JPanel{
		
		private Image avatar;   // image of avatar
		
		/**
		 * It resizes and provides avatar image to the avatar 
		 * private instance variable.
		 * 
		 * @param avatar  Image of the player avatar.
		 * 
		 */
		BigTwoPanel(Image avatar) {
			Image resized = resizer(avatar, 150, 150);
			this.avatar = new ImageIcon(resized).getImage();
		}
		
		/**
		 * This method draws the image of the avatar on the bigTwoPanel.
		 * 
		 * @param g  the Graphics object to protect
		 * 
		 */
		
		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(avatar, 5, 20, avatar.getWidth(this), avatar.getHeight(this), this);

		}
	}
	
	/**
	 * An inner class that extends the JPanel class. 
	 * It overrides the paintComponent() to draw the 
	 * table. 
	 * 
	 * @author Masood Ahmed
	 *
	 */
	class TableBackground extends JPanel{
		
		private Image image;  // To store the image of the table

		/**
		 * A constructor that takes image of the table.
		 * 
		 * @param image A string variable having the location of the image.
		 */
		
	    public TableBackground(String image) {
	        this(new ImageIcon(image).getImage());
	    }

	    /**
	     *  A constructor that takes image of the table.
	     * 
	     * @param image An image object to having the image of the table.
	     */
	    public TableBackground(Image image) {
	        this.image = image;
	        Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
	        setPreferredSize(size);

	        setMinimumSize(size);
	        setMaximumSize(size);

	        setSize(size);
	        setLayout(null);
	    }
	    
		/**
		 * The method to the draw the image of the table.
		 * 
		 * @param g  the Graphics object to protect
		 * 
		 */
	    
		@Override
		
	    public void paintComponent(Graphics g) {
	        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	    }
	}
	
	/**
	 * An inner class that implements the ActionListener interface. Implements the 
	 * actionPerformed() method from the ActionListener interface to handle button-click 
	 * events for the “Play” button. When the “Play” button is clicked, it calls 
	 * the makeMove() method of your CardGame object to make a move.
	 * 
	 * @author Masood Ahmed
	 * @version 2.0 
	 * 
	 */
	
	class PlayButtonListener implements ActionListener {

		/**
		 * This method allows the player to pick the card and make a move in the 
		 * big two game.It is invoked when an action occurs.
		 * 
		 * @param e A semantic event which indicates that a component-defined action occurred. 
		 * 
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			game.makeMove(activePlayer, getSelected());
		}
	}
	
	/**
	 * An inner class that implements the ActionListener interface. Implements the actionPerformed()
	 * method from the ActionListener interface to handle button-click events for the “Pass” button. 
	 * When the “Pass” button is clicked, it should call the makeMove() method of your CardGame object
	 *  to make a move.
	 *
	 * @author Masood Ahmed
	 * @version 2.0 
	 * 
	 */
	
	class PassButtonListener implements ActionListener {

		/**
		 * This method allows the player to pass a move in the 
		 * big two game. It is invoked when an action occurs.
		 * 
		 * @param e A semantic event which indicates that a component-defined action occurred. 
		 * 
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			game.makeMove(activePlayer, null);
		}
	}
	
	/**
	 * An inner class that implements the ActionListener interface for handling the menu-item-click 
	 * events for the “Connect” menu item. When the “Connect” menu item is selected, it will call connect() 
	 * method from the client if the client is not currently connected to the server.
	 * 
	 * @author Masood Ahmed
	 *
	 */
	class ConnectMenuItemListener implements ActionListener {

		
		/**
		 * This method handles connection of local client with server.
		 * 
		 * @param e A semantic event which indicates that a component-defined action occurred. 
		 * 
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(game.getClient().getConnected() == true) {
				printMsg("You are already connected to the server!");
				frame.repaint();
			}
			else {
				game.getClient().connect();
			}	
		}
	}
	
	
	/**
	 * An inner class that implements ActionListener for putting messages from
	 * chat Input panel to the chat text area. 
	 * 
	 * @author Masood Ahmed
	 * @version 2.0 
	 *
	 */
	class ChatInputActionListener implements ActionListener {

		/**
		 * This method handles the input in the text box
		 * 
		 * @param e A semantic event which indicates that a component-defined action occurred. 
		 * 
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			CardGameMessage message = new CardGameMessage(CardGameMessage.MSG, -1, chatInput.getText());
			chatInput.setText("");
			chatInput.requestFocus();
			game.getClient().sendMessage(message);
		}
	}
	
	/**
	 * An inner class that implements the ActionListener interface. Implements the 
	 * actionPerformed() method from the ActionListener interface to handle menu-item-click
	 * events for the “Quit” menu item. When the “Quit” menu item is selected, it terminates
	 * the application.
	 *
	 * @author Masood Ahmed
	 * @version 2.0 
	 * 
	 */
	
	class QuitMenuItemListener implements ActionListener {
		
		/**
		 * This method exits the game when "Quit" is clicked.
		 * 
		 * @param e A semantic event which indicates that a component-defined action occurred. 
		 * 
		 */
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	
	/**
	 * An inner class that implements ActionListener for clearing the 
	 * chat area of the big two game.
	 * 
	 * @author Masood Ahmed
	 * @version 2.0 
	 *
	 */
	class ClearChatListener implements ActionListener {
		
		/**
		 * This method clear the chat area of the Big Two Game.
		 * 
		 * @param e A semantic event which indicates that a component-defined action occurred.
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			chatArea.setText("");
		}
		
	}
	
	// ***********************************************************************************
	
	
	// ********************** Stay Safe and Stay Happy ************************************
}
