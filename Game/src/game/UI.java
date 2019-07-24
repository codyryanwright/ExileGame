package game;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.SwingConstants;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class UI {

	final private ImageIcon imgIconButton = new ImageIcon(UI.class.getResource("/images/buttonPaper.png"));

	protected JFrame frame;
	protected CardLayout cardlayout;
	private JPanel mainMenuPanel, rulesPanel, settingsPanel, choosePanel, playPanel, endPanel;
	private JButton btnNewGameStart, btnNewGameEnd, btnContinue, btnDeck1, btnDeck2, btnDeck3, btnCard1, btnCard2, btnCard3, btnPlayCard;
	private JSpinner spinnerDifficulty;
	private Sequencer sequencer;
	private InputStream is;

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
		
		// set music
		try {
			sequencer = MidiSystem.getSequencer();
 			sequencer.open();
 			is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("music/bjorn__lynne-_proud_warriors.mid"));
			sequencer.setSequence(is);
			sequencer.start(); // default setting
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void show(String panel) {
		cardlayout.show(frame.getContentPane(), panel);
	}
	
	/**
	 * Add Controller Listeners
	 */
	protected void addNewGameListener(ActionListener newGameListener) {
		btnNewGameStart.addActionListener(newGameListener);
		btnNewGameEnd.addActionListener(newGameListener);
	}

	public void addDeck0Listener(ActionListener deck0Listener) {
		btnDeck3.addActionListener(deck0Listener);
	}
	
	protected void addDeck1Listener(ActionListener deck1Listener) {
		btnDeck1.addActionListener(deck1Listener);
	}

	public void addDeck2Listener(ActionListener deck2Listener) {
		btnDeck2.addActionListener(deck2Listener);		
	}
	
	protected void addCard0Listener(ActionListener card0Listener) {
		btnCard3.addActionListener(card0Listener);
	}
	
	protected void addCard1Listener(ActionListener card1Listener) {
		btnCard1.addActionListener(card1Listener);
	}
	
	protected void addCard2Listener(ActionListener card2Listener) {
		btnCard2.addActionListener(card2Listener);
	}
	
	protected void addPlayCardListener(ActionListener playCardListener) {
		btnPlayCard.addActionListener(playCardListener);
	}
	
	protected void addContinueListener(ActionListener ContinueListener) {
		btnContinue.addActionListener(ContinueListener);
	}
	// For difficulty setting, unsure how to do
//		protected void addDifficultChangeListener(PropertyChange listener) {
//			spinnerDifficulty.addChangeListener(listener);
//		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initMainMenu() {
		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(null);
		frame.getContentPane().add(mainMenuPanel, "mainMenuPanel");
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		panelTitle.setLayout(null);
		mainMenuPanel.add(panelTitle);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(72, 10, 332, 65);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		panelTitle.add(lblTitle);
		
		ImageIcon imgMainMenuScreen = new ImageIcon(UI.class.getResource("/images/mainMenuScreen.jpg"));				
		JLabel lblMainMenuScreen = new JLabel("");
		lblMainMenuScreen.setBounds(0, 0, 477, 675);
		lblMainMenuScreen.setBackground(Color.BLACK);
		lblMainMenuScreen.setIcon(new ImageIcon(getScaledImage(imgMainMenuScreen.getImage(), 477, 675)));
		panelTitle.add(lblMainMenuScreen);
		
		JPanel panelContent = new JPanel();
		panelContent.setBorder(null);
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		mainMenuPanel.add(panelContent);

		btnNewGameStart = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnNewGameStart.setBounds(189, 103, 220, 40);
		btnNewGameStart.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGameStart.setText("New Game");
		btnNewGameStart.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGameStart.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnNewGameStart.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnNewGameStart);
		
		JButton btnRules = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnRules.setBounds(189, 246, 220, 40);
		btnRules.setVerticalTextPosition(SwingConstants.CENTER);
		btnRules.setText("Rules");
		btnRules.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRules.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnRules.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnRules);
		btnRules.addActionListener(new ActionListener() { //TODO change to GC
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "rulesPanel");
			}
		});
		
		JButton btnSettings = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnSettings.setBounds(189, 389, 220, 40);
		btnSettings.setVerticalTextPosition(SwingConstants.CENTER);
		btnSettings.setText("Settings");
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnSettings.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnSettings);
		btnSettings.addActionListener(new ActionListener() { //TODO change to GC
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "settingsPanel");
			}
		});
		
		btnContinue = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnContinue.setBounds(189, 532, 220, 40);
		btnContinue.setVerticalTextPosition(SwingConstants.CENTER);
		btnContinue.setText("Continue Game");
		btnContinue.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContinue.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnContinue.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnContinue);
	}
	
	private void initRules() {
		rulesPanel = new JPanel();
		rulesPanel.setBackground(Color.BLACK);
		rulesPanel.setLayout(null);
		frame.getContentPane().add(rulesPanel, "rulesPanel");
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		panelTitle.setLayout(null);
		rulesPanel.add(panelTitle);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(72, 10, 332, 65);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		panelTitle.add(lblTitle);
		
		ImageIcon imgRulesScreen = new ImageIcon(UI.class.getResource("/images/rulesScreen.jpg"));		
		JLabel lblRulesScreen = new JLabel("");
		lblRulesScreen.setBounds(0, 0, 477, 675);
		lblRulesScreen.setIcon(new ImageIcon(getScaledImage(imgRulesScreen.getImage(), 477, 675)));
		panelTitle.add(lblRulesScreen);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		rulesPanel.add(panelContent);
		
		JLabel lblRules = new JLabel("RULES");
		lblRules.setBounds(225, 42, 148, 47);
		lblRules.setHorizontalAlignment(SwingConstants.CENTER);
		lblRules.setForeground(Color.WHITE);
		lblRules.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
		lblRules.setBackground(Color.BLACK);
		panelContent.add(lblRules);
		
		JButton btnMainMenu = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnMainMenu.setBounds(189, 593, 220, 40);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setText("Main Menu");
		btnMainMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setBorder(UIManager.getBorder("Button.border"));
		btnMainMenu.addActionListener(new ActionListener() { //TODO move to GC
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "mainMenuPanel");
			}
		});
		panelContent.add(btnMainMenu);
		
		JTextPane txtpnRules = new JTextPane();
		txtpnRules.setEditable(false);
		txtpnRules.setFont(new Font("SimSun", Font.PLAIN, 15));
		txtpnRules.setText("- Each player starts the game with a hand containing three cards and a full health bar (5 life points)\r\n\n" + 
				"- A player loses the game when he or she runs out of health\r\n\n" + 
				"- Each turn each player chooses a card to play out of his or her hand\r\n\n" + 
				"- After each player has played his chosen card, the two cards will battle\r\n\n" + 
				"- The battle either ends in a win or a draw\r\n\n" + 
				"- If the battle ends in a draw, no health we will be diducted from any player\r\n\n" + 
				"- Otherwise, the player that lost the battle will lose a life point and their health bar will go down\r\n\n" + 
				"- At the end of each turn, each player will draw a card\r\n\n" + 
				"- The game continues until one of the player's health runs out"); // TODO missing card hierarchy explanation
		txtpnRules.setBackground(Color.BLACK);
		txtpnRules.setForeground(Color.WHITE);
		txtpnRules.setBounds(144, 0, 330, 335);
		txtpnRules.setCaretPosition(0);
		rulesPanel.add(txtpnRules);
		
		JScrollPane scrollPane = new JScrollPane(txtpnRules);
		scrollPane.setBounds(16, 131, 567, 420);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelContent.add(scrollPane);
	}
	
	private void initSettings() {
		settingsPanel = new JPanel();
		settingsPanel.setLayout(null);
		frame.getContentPane().add(settingsPanel, "settingsPanel");
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		panelTitle.setLayout(null);
		settingsPanel.add(panelTitle);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(72, 10, 332, 65);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		panelTitle.add(lblTitle);
		
		ImageIcon imgSettingsScreen = new ImageIcon(UI.class.getResource("/images/settingsScreen.jpg"));		
		JLabel lblSettingsScreen = new JLabel("");
		lblSettingsScreen.setBounds(0, 0, 477, 675);
		lblSettingsScreen.setIcon(new ImageIcon(getScaledImage(imgSettingsScreen.getImage(), 477, 675)));
		panelTitle.add(lblSettingsScreen);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(new Color(0, 0, 0));
		panelContent.setBounds(477, 0, 599, 675);
		settingsPanel.add(panelContent);
		panelContent.setLayout(null);
		
		JLabel lblSettings = new JLabel("SETTINGS");
		lblSettings.setBounds(225, 106, 148, 47);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
		lblSettings.setBackground(Color.BLACK);
		panelContent.add(lblSettings);
		
		JPanel panel = new JPanel();
		panel.setBounds(163, 259, 246, 164);
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		panelContent.add(panel);
		
		JLabel lblSound = new JLabel("Sound");
		lblSound.setBounds(41, 7, 83, 27);
		lblSound.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSound.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		lblSound.setBackground(Color.BLACK);
		lblSound.setForeground(Color.WHITE);
		panel.add(lblSound);
		
		JSpinner spinnerSound = new JSpinner();
		spinnerSound.setBounds(146, 0, 100, 40);
		spinnerSound.setFont(new Font("SimSun", Font.PLAIN, 26));
		spinnerSound.setModel(new SpinnerListModel(new String[] {"On", "Off"}));
		spinnerSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(spinnerSound.getValue() == "On") sequencer.start();
				else sequencer.stop();
			}
		});
		panel.add(spinnerSound);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setBounds(0, 131, 124, 27);
		lblDifficulty.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDifficulty.setForeground(Color.WHITE);
		lblDifficulty.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		lblDifficulty.setBackground(Color.BLACK);
		panel.add(lblDifficulty);
		
		spinnerDifficulty = new JSpinner();
		spinnerDifficulty.setBounds(146, 124, 100, 40);
		spinnerDifficulty.setModel(new SpinnerListModel(new String[] {"Easy", "Normal", "Hard"}));
		spinnerDifficulty.setFont(new Font("SimSun", Font.PLAIN, 26));
		panel.add(spinnerDifficulty);
		spinnerDifficulty.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) { // TODO fill in difficulty changes & move to GC
				if(spinnerDifficulty.getValue() == "Easy");
				else if(spinnerDifficulty.getValue() == "Normal");
				else;
			}
		});

		
		JButton btnMainMenu = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnMainMenu.setBounds(189, 529, 220, 40);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setText("Main Menu");
		btnMainMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnMainMenu);
		btnMainMenu.addActionListener(new ActionListener() { // TODO move to GC
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "mainMenuPanel");
			}
		});
	}
	
	private void initChoose() {
		choosePanel = new JPanel();
		choosePanel.setLayout(null);
		frame.getContentPane().add(choosePanel, "choosePanel");
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		panelTitle.setLayout(null);
		choosePanel.add(panelTitle);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(72, 10, 332, 65);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		panelTitle.add(lblTitle);
		
		ImageIcon imgChooseScreen = new ImageIcon(UI.class.getResource("/images/chooseScreen.jpg"));		
		JLabel lblChooseScreen = new JLabel("");
		lblChooseScreen.setBounds(0, 0, 477, 675);
		lblChooseScreen.setIcon(new ImageIcon(getScaledImage(imgChooseScreen.getImage(), 477, 675)));
		lblChooseScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblChooseScreen.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.add(lblChooseScreen);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		choosePanel.add(panelContent);
		
		JLabel lblChooseYourDeck = new JLabel("Choose Your Deck");
		lblChooseYourDeck.setBounds(108, 144, 382, 66);
		lblChooseYourDeck.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseYourDeck.setBackground(Color.BLACK);
		lblChooseYourDeck.setForeground(Color.WHITE);
		lblChooseYourDeck.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));
		panelContent.add(lblChooseYourDeck);
		
		JPanel deckPanel = new JPanel();
		deckPanel.setBounds(108, 250, 382, 175);
		deckPanel.setBorder(null);
		deckPanel.setBackground(Color.BLACK);
		deckPanel.setLayout(new GridLayout(0, 3, 0, 0));
		panelContent.add(deckPanel);		

		ImageIcon imgIconDeck1 = new ImageIcon(UI.class.getResource("/images/deck1.png"));		
		ImageIcon imgIconDeck2 = new ImageIcon(UI.class.getResource("/images/deck2.png"));
		ImageIcon imgIconDeck3 = new ImageIcon(UI.class.getResource("/images/deck3.png"));
		
		btnDeck1 = new JButton(new ImageIcon(getScaledImage(imgIconDeck1.getImage(),127, 175)));
		deckPanel.add(btnDeck1);

		btnDeck2 = new JButton(new ImageIcon(getScaledImage(imgIconDeck2.getImage(),127, 175)));
		deckPanel.add(btnDeck2);
		btnDeck2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");
				// TODO move to GC
			}
		});
		
		btnDeck3 = new JButton(new ImageIcon(getScaledImage(imgIconDeck3.getImage(),127, 175)));
		deckPanel.add(btnDeck3);
		btnDeck3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");
				// TODO move to GC
			}
		});
	}
	
	private void initPlay() {
		playPanel = new JPanel();
		playPanel.setLayout(null);
		frame.getContentPane().add(playPanel, "playPanel");
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		panelTitle.setLayout(null);
		playPanel.add(panelTitle);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(72, 10, 332, 65);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		panelTitle.add(lblTitle);
		
		ImageIcon imgPlayScreen = new ImageIcon(UI.class.getResource("/images/playScreen.jpg"));		
		JLabel lblPlayScreen = new JLabel("");
		lblPlayScreen.setBounds(0, 0, 477, 675);
		lblPlayScreen.setIcon(new ImageIcon(getScaledImage(imgPlayScreen.getImage(), 477, 675)));
		lblPlayScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayScreen.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.add(lblPlayScreen);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		playPanel.add(panelContent);
		
		ImageIcon imgIconDragon = new ImageIcon(UI.class.getResource("/images/dragon.jpg")); //TODO image should be variable
		ImageIcon imgIconWizard = new ImageIcon(UI.class.getResource("/images/wizard.jpg")); //TODO image should be variable
		ImageIcon imgIconKnight = new ImageIcon(UI.class.getResource("/images/knight.jpg")); //TODO image should be variable
		
		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(UIManager.getBorder("InternalFrame.border"));
		panelStatus.setBackground(Color.BLACK);
		panelStatus.setBounds(85, 32, 428, 75);
		panelStatus.setLayout(null);
		panelContent.add(panelStatus);
		
		JLabel lblPlayerHealth = new JLabel("Player Health");
		lblPlayerHealth.setBounds(0, 14, 115, 18);
		lblPlayerHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerHealth.setForeground(Color.LIGHT_GRAY);
		lblPlayerHealth.setFont(new Font("SimSun", Font.PLAIN, 14));
		panelStatus.add(lblPlayerHealth);
		
		JProgressBar barPlayerHealth = new JProgressBar();
		barPlayerHealth.setBounds(125, 14, 111, 18);
		barPlayerHealth.setValue(100);  //TODO this should be variable
		barPlayerHealth.setForeground(Color.GREEN);
		panelStatus.add(barPlayerHealth);
		
		JLabel lblOpponentHealth = new JLabel("Opponent Health");
		lblOpponentHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpponentHealth.setForeground(Color.LIGHT_GRAY);
		lblOpponentHealth.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblOpponentHealth.setBounds(0, 45, 115, 18);
		panelStatus.add(lblOpponentHealth);
		
		JProgressBar barOpponentHealth = new JProgressBar();
		barOpponentHealth.setValue(100);
		barOpponentHealth.setForeground(Color.GREEN);
		barOpponentHealth.setBounds(125, 46, 111, 18);
		panelStatus.add(barOpponentHealth);
		barPlayerHealth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (barPlayerHealth.getValue() == 0)
					cardlayout.show(frame.getContentPane(), "endPanel");
					// TODO set game outcome win/loss variable
			}
		});
		
		JButton btnMenu = new JButton("Main Menu");
		btnMenu.setBounds(287, 17, 100, 40);
		btnMenu.setBackground(Color.LIGHT_GRAY);
		btnMenu.setFont(new Font("SimSun", Font.PLAIN, 14));
		panelStatus.add(btnMenu);
		btnMenu.addActionListener(new ActionListener() { // TODO move to GC
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "mainMenuPanel");
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 117, 428, 244);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelContent.add(scrollPane);
		
		JTextPane txtpnGame = new JTextPane();
		txtpnGame.setLocation(97, 0);
		txtpnGame.setText(""); // TODO this needs to be variable and append card action text
		txtpnGame.setFont(new Font("SimSun", Font.PLAIN, 7));
		txtpnGame.setEditable(false);
		scrollPane.setViewportView(txtpnGame);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBounds(81, 371, 437, 200);
		cardPanel.setBorder(null);
		cardPanel.setBackground(Color.BLACK);
		cardPanel.setLayout(new GridLayout(0, 3, 0, 0));
		panelContent.add(cardPanel);
		
		btnCard1 = new JButton(new ImageIcon(getScaledImage(imgIconDragon.getImage(), 146, 200)));
		cardPanel.add(btnCard1);
		btnCard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO move to GC
			}
		});
		
		btnCard2 = new JButton(new ImageIcon(getScaledImage(imgIconWizard.getImage(), 146, 200)));
		cardPanel.add(btnCard2);
		btnCard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO move to GC
			}
		});
		
		btnCard3 = new JButton(new ImageIcon(getScaledImage(imgIconKnight.getImage(), 146, 200)));
		cardPanel.add(btnCard3);
		btnCard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO move to GC
			}
		});
		
		btnPlayCard = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(),220, 40)));
		btnPlayCard.setBounds(189, 603, 220, 40);
		btnPlayCard.setVerticalTextPosition(SwingConstants.CENTER);
		btnPlayCard.setText("Play Card");
		btnPlayCard.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPlayCard.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnPlayCard.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnPlayCard);
		btnPlayCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO move to GC
			}
		});
	}
	
	private void initEnd() {
		endPanel = new JPanel();
		endPanel.setLayout(null);
		frame.getContentPane().add(endPanel, "endPanel");
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		panelTitle.setLayout(null);
		endPanel.add(panelTitle);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(72, 10, 332, 65);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		panelTitle.add(lblTitle);
		
		ImageIcon imgEndScreen = new ImageIcon(UI.class.getResource("/images/endScreen.jpg"));		
		JLabel lblEndScreen = new JLabel("");
		lblEndScreen.setBounds(0, 0, 477, 675);
		lblEndScreen.setIcon(new ImageIcon(getScaledImage(imgEndScreen.getImage(), 477, 675)));
		lblEndScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEndScreen.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.add(lblEndScreen);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		endPanel.add(panelContent);
		
		btnNewGameEnd = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnNewGameEnd.setBounds(189, 324, 220, 40);
		btnNewGameEnd.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGameEnd.setText("New Game");
		btnNewGameEnd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGameEnd.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnNewGameEnd.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnNewGameEnd);
		btnNewGameEnd.addActionListener(new ActionListener() { //TODO move to GC
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "choosePanel");	
			}
		});
		
		JButton btnExit = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnExit.setBounds(189, 499, 220, 40);
		btnExit.setVerticalTextPosition(SwingConstants.CENTER);
		btnExit.setText("Exit");
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnExit.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnExit);
		btnExit.addActionListener(new ActionListener() {// TODO move to GC
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JTextField txtYouWin = new JTextField();
		txtYouWin.setBounds(189, 135, 220, 54);
		txtYouWin.setEditable(false);
		txtYouWin.setBorder(null);
		txtYouWin.setHorizontalAlignment(SwingConstants.CENTER);
		txtYouWin.setBackground(Color.BLACK);
		txtYouWin.setText("YOU WIN!");  //TODO this should be variable
		txtYouWin.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));
		txtYouWin.setForeground(Color.WHITE);
		panelContent.add(txtYouWin);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setMaximumSize(new Dimension(1080, 699));
		frame.setMinimumSize(new Dimension(1080, 699));
	    cardlayout = new CardLayout(0, 0);
		frame.getContentPane().setLayout(cardlayout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initMainMenu();
	    initRules();
	    initSettings();
	    initChoose();
		initPlay();
	    initEnd();

		// Commands used to switch which Panel is viewed for debug
//		cardlayout.show(frame.getContentPane(), "mainMenuPanel");
//		cardlayout.show(frame.getContentPane(), "rulesPanel");
//		cardlayout.show(frame.getContentPane(), "settingsPanel");
//		cardlayout.show(frame.getContentPane(), "choosePanel");
//		cardlayout.show(frame.getContentPane(), "playPanel");
//		cardlayout.show(frame.getContentPane(), "endPanel");
	}
	
	private Image getScaledImage(Image srcImg, int w, int h) {
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
}