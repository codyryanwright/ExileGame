package view;

import java.awt.EventQueue;
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
import java.awt.Insets;
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


public class p1 {

	private JFrame frame;
	private CardLayout cardlayout;
	private JPanel mainMenuPanel, rulesPanel, settingsPanel, choosePanel, playPanel, endPanel;
	private Sequencer sequencer;
	private InputStream is;
	final private ImageIcon imgIconButton = new ImageIcon(p1.class.getResource("/images/buttonPaper.png"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					p1 window = new p1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public p1() {
		initialize();
		
		// set music
		try {
			sequencer = MidiSystem.getSequencer();
 			sequencer.open();
 			is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("music/bjorn__lynne-_proud_warriors.mid"));
			sequencer.setSequence(is);
			playMusic(true); // default setting
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playMusic(Boolean on) {
		if(on) sequencer.start();
		else sequencer.stop();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initMainMenu() {
		mainMenuPanel = new JPanel();
		frame.getContentPane().add(mainMenuPanel, "mainMenuPanel");
		mainMenuPanel.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		mainMenuPanel.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(73, 10, 330, 65);
		panelTitle.add(lblTitle);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		
		ImageIcon imgMainMenuScreen = new ImageIcon(p1.class.getResource("/images/mainMenuScreen.jpg"));		
		
		JLabel lblMainMenuScreen = new JLabel("");
		lblMainMenuScreen.setBounds(0, 0, 477, 675);
		panelTitle.add(lblMainMenuScreen);
		lblMainMenuScreen.setBackground(Color.BLACK);
		lblMainMenuScreen.setIcon(new ImageIcon(getScaledImage(imgMainMenuScreen.getImage(), 477, 675)));
		
		JPanel panelContent = new JPanel();
		panelContent.setBorder(null);
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		mainMenuPanel.add(panelContent);

		JButton btnNewGame = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnNewGame.setBounds(189, 103, 220, 40);
		panelContent.add(btnNewGame);
		btnNewGame.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGame.setText("New Game");
		btnNewGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGame.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnNewGame.setBorder(UIManager.getBorder("Button.border"));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");
				// TODO reset game environment
			}
		});
		
		JButton btnRules = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnRules.setBounds(189, 246, 220, 40);
		panelContent.add(btnRules);
		btnRules.setVerticalTextPosition(SwingConstants.CENTER);
		btnRules.setText("Rules");
		btnRules.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRules.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnRules.setBorder(UIManager.getBorder("Button.border"));
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "rulesPanel");
			}
		});
		
		JButton btnSettings = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnSettings.setBounds(189, 389, 220, 40);
		panelContent.add(btnSettings);
		btnSettings.setVerticalTextPosition(SwingConstants.CENTER);
		btnSettings.setText("Settings");
		btnSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSettings.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnSettings.setBorder(UIManager.getBorder("Button.border"));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "settingsPanel");
			}
		});
		
		JButton btnContinue = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnContinue.setBounds(189, 532, 220, 40);
		panelContent.add(btnContinue);
		btnContinue.setVerticalTextPosition(SwingConstants.CENTER);
		btnContinue.setText("Continue Game");
		btnContinue.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContinue.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnContinue.setBorder(UIManager.getBorder("Button.border"));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");
			}
		});
	}
	
	private void initRules() {
		rulesPanel = new JPanel();
		rulesPanel.setBackground(Color.BLACK);
		frame.getContentPane().add(rulesPanel, "rulesPanel");
		rulesPanel.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		rulesPanel.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(73, 10, 330, 65);
		panelTitle.add(lblTitle);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		
		ImageIcon imgRulesScreen = new ImageIcon(p1.class.getResource("/images/rulesScreen.jpg"));		
		JLabel lblRulesScreen = new JLabel("");
		lblRulesScreen.setBounds(0, 0, 477, 675);
		panelTitle.add(lblRulesScreen);
		lblRulesScreen.setIcon(new ImageIcon(getScaledImage(imgRulesScreen.getImage(), 477, 675)));
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		rulesPanel.add(panelContent);
		panelContent.setLayout(null);
		
		JLabel lblRules = new JLabel("RULES");
		lblRules.setBounds(225, 42, 148, 47);
		panelContent.add(lblRules);
		lblRules.setHorizontalAlignment(SwingConstants.CENTER);
		lblRules.setForeground(Color.WHITE);
		lblRules.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
		lblRules.setBackground(Color.BLACK);
		
		JButton btnMainMenu = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnMainMenu.setBounds(189, 593, 220, 40);
		panelContent.add(btnMainMenu);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setText("Main Menu");
		btnMainMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setBorder(UIManager.getBorder("Button.border"));
		
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
				"- The game continues until one of the player's health runs out"); // TODO missing card heirarchy explaination
		txtpnRules.setBackground(Color.BLACK);
		txtpnRules.setForeground(Color.WHITE);
		txtpnRules.setBounds(144, 0, 330, 335);
		txtpnRules.setCaretPosition(0);
		rulesPanel.add(txtpnRules);
		
		JScrollPane scrollPane = new JScrollPane(txtpnRules);
		scrollPane.setBounds(16, 131, 567, 420);
		panelContent.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "mainMenuPanel");
			}
		});
	}
	
	private void initSettings() {
		settingsPanel = new JPanel();
		frame.getContentPane().add(settingsPanel, "settingsPanel");
		settingsPanel.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		settingsPanel.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(73, 10, 330, 65);
		panelTitle.add(lblTitle);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		
		ImageIcon imgSettingsScreen = new ImageIcon(p1.class.getResource("/images/settingsScreen.jpg"));		
		JLabel lblSettingsScreen = new JLabel("");
		lblSettingsScreen.setBounds(0, 0, 477, 675);
		panelTitle.add(lblSettingsScreen);
		lblSettingsScreen.setIcon(new ImageIcon(getScaledImage(imgSettingsScreen.getImage(), 477, 675)));
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(new Color(0, 0, 0));
		panelContent.setBounds(477, 0, 599, 675);
		settingsPanel.add(panelContent);
		panelContent.setLayout(null);
		
		JLabel lblSettings = new JLabel("SETTINGS");
		lblSettings.setBounds(225, 106, 148, 47);
		panelContent.add(lblSettings);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
		lblSettings.setBackground(Color.BLACK);
		
		JPanel panel = new JPanel();
		panel.setBounds(163, 259, 246, 164);
		panelContent.add(panel);
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		
		JLabel lblSound = new JLabel("Sound");
		lblSound.setBounds(41, 7, 83, 27);
		panel.add(lblSound);
		lblSound.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSound.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		lblSound.setBackground(Color.BLACK);
		lblSound.setForeground(Color.WHITE);
		
		JSpinner spinnerSound = new JSpinner();
		spinnerSound.setBounds(146, 0, 100, 40);
		panel.add(spinnerSound);
		spinnerSound.setFont(new Font("SimSun", Font.PLAIN, 26));
		spinnerSound.setModel(new SpinnerListModel(new String[] {"On", "Off"}));
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setBounds(0, 131, 124, 27);
		panel.add(lblDifficulty);
		lblDifficulty.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDifficulty.setForeground(Color.WHITE);
		lblDifficulty.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		lblDifficulty.setBackground(Color.BLACK);
		
		JSpinner spinnerDifficulty = new JSpinner();
		spinnerDifficulty.setBounds(146, 124, 100, 40);
		panel.add(spinnerDifficulty);
		spinnerDifficulty.setModel(new SpinnerListModel(new String[] {"Easy", "Normal", "Hard"}));
		spinnerDifficulty.setFont(new Font("SimSun", Font.PLAIN, 26));
		
		JButton btnMainMenu = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnMainMenu.setBounds(189, 529, 220, 40);
		panelContent.add(btnMainMenu);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setText("Main Menu");
		btnMainMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setBorder(UIManager.getBorder("Button.border"));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "mainMenuPanel");
			}
		});
		spinnerDifficulty.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) { // TODO fill in difficulty changes
				if(spinnerDifficulty.getValue() == "Easy");
				else if(spinnerDifficulty.getValue() == "Normal");
				else;
			}
		});
		spinnerSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(spinnerSound.getValue() == "On") playMusic(true);
				else playMusic(false);
			}
		});
	}
	
	private void initChoose() {
		choosePanel = new JPanel();
		frame.getContentPane().add(choosePanel, "choosePanel");
		choosePanel.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		choosePanel.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(73, 10, 330, 65);
		panelTitle.add(lblTitle);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		
		ImageIcon imgChooseScreen = new ImageIcon(p1.class.getResource("/images/chooseScreen.jpg"));		
		JLabel lblChooseScreen = new JLabel("");
		lblChooseScreen.setBounds(0, 0, 477, 675);
		panelTitle.add(lblChooseScreen);
		lblChooseScreen.setIcon(new ImageIcon(getScaledImage(imgChooseScreen.getImage(), 477, 675)));
		lblChooseScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblChooseScreen.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		choosePanel.add(panelContent);
		panelContent.setLayout(null);
		
		JLabel lblChooseYourDeck = new JLabel("Choose Your Deck");
		lblChooseYourDeck.setBounds(108, 144, 382, 66);
		panelContent.add(lblChooseYourDeck);
		lblChooseYourDeck.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseYourDeck.setBackground(Color.BLACK);
		lblChooseYourDeck.setForeground(Color.WHITE);
		lblChooseYourDeck.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));
		
		JPanel deckPanel = new JPanel();
		deckPanel.setBounds(108, 250, 382, 175);
		panelContent.add(deckPanel);
		deckPanel.setBorder(null);
		deckPanel.setBackground(Color.BLACK);
		deckPanel.setLayout(new GridLayout(0, 3, 0, 0));
		

		ImageIcon imgIconDeck1 = new ImageIcon(p1.class.getResource("/images/deck1.png"));		
		ImageIcon imgIconDeck2 = new ImageIcon(p1.class.getResource("/images/deck2.png"));
		ImageIcon imgIconDeck3 = new ImageIcon(p1.class.getResource("/images/deck3.png"));
		
		JButton btnDeck1 = new JButton(new ImageIcon(getScaledImage(imgIconDeck1.getImage(),127, 175)));
		deckPanel.add(btnDeck1);
		btnDeck1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");	
				// TODO set deck 1
			}
		});

		JButton btnDeck2 = new JButton(new ImageIcon(getScaledImage(imgIconDeck2.getImage(),127, 175)));
		deckPanel.add(btnDeck2);
		btnDeck2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");
				// TODO set deck 2
			}
		});
		
		JButton btnDeck3 = new JButton(new ImageIcon(getScaledImage(imgIconDeck3.getImage(),127, 175)));
		deckPanel.add(btnDeck3);
		btnDeck3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");
				// TODO set deck 3
			}
		});
	}
	
	private void initPlay() {
		playPanel = new JPanel();
		playPanel.setLayout(null);
		frame.getContentPane().add(playPanel, "playPanel");
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		playPanel.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(73, 10, 330, 65);
		panelTitle.add(lblTitle);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		
		ImageIcon imgPlayScreen = new ImageIcon(p1.class.getResource("/images/playScreen.jpg"));		
		JLabel lblPlayScreen = new JLabel("");
		lblPlayScreen.setBounds(0, 0, 477, 675);
		panelTitle.add(lblPlayScreen);
		lblPlayScreen.setIcon(new ImageIcon(getScaledImage(imgPlayScreen.getImage(), 477, 675)));
		lblPlayScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayScreen.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		playPanel.add(panelContent);
		panelContent.setLayout(null);
		
		ImageIcon imgIconDragon = new ImageIcon(p1.class.getResource("/images/dragon.jpg")); //TODO image should be variable
		
		ImageIcon imgIconWizard = new ImageIcon(p1.class.getResource("/images/wizard.jpg")); //TODO image should be variable
		
		ImageIcon imgIconKnight = new ImageIcon(p1.class.getResource("/images/knight.jpg")); //TODO image should be variable
		
		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(UIManager.getBorder("InternalFrame.border"));
		panelStatus.setBackground(Color.BLACK);
		panelStatus.setBounds(85, 32, 428, 75);
		panelContent.add(panelStatus);
		panelStatus.setLayout(null);
		
		JLabel lblPlayerHealth = new JLabel("Player Health");
		lblPlayerHealth.setBounds(0, 14, 115, 18);
		panelStatus.add(lblPlayerHealth);
		lblPlayerHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerHealth.setForeground(Color.LIGHT_GRAY);
		lblPlayerHealth.setFont(new Font("SimSun", Font.PLAIN, 14));
		
		JProgressBar barPlayerHealth = new JProgressBar();
		barPlayerHealth.setBounds(125, 14, 111, 18);
		panelStatus.add(barPlayerHealth);
		barPlayerHealth.setValue(100);  //TODO this should be variable
		barPlayerHealth.setForeground(Color.GREEN);
		
		JLabel lblPlayerWins = new JLabel("Wins");
		lblPlayerWins.setBounds(248, 14, 46, 18);
		panelStatus.add(lblPlayerWins);
		lblPlayerWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerWins.setForeground(Color.LIGHT_GRAY);
		lblPlayerWins.setFont(new Font("SimSun", Font.PLAIN, 14));
		
		JTextPane txtpnPlayerWins = new JTextPane();
		txtpnPlayerWins.setBounds(294, 12, 23, 23);
		panelStatus.add(txtpnPlayerWins);
		txtpnPlayerWins.setText("01"); //TODO this should be variable
		txtpnPlayerWins.setMargin(new Insets(0, 3, 3, 3));
		txtpnPlayerWins.setForeground(Color.BLACK);
		txtpnPlayerWins.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		txtpnPlayerWins.setEditable(false);
		txtpnPlayerWins.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblPlayerLosses = new JLabel("Losses");
		lblPlayerLosses.setBounds(341, 14, 46, 18);
		panelStatus.add(lblPlayerLosses);
		lblPlayerLosses.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerLosses.setForeground(Color.LIGHT_GRAY);
		lblPlayerLosses.setFont(new Font("SimSun", Font.PLAIN, 14));
		
		JTextPane txtpnPlayerLosses = new JTextPane();
		txtpnPlayerLosses.setBounds(395, 12, 23, 23);
		panelStatus.add(txtpnPlayerLosses);
		txtpnPlayerLosses.setText("01"); //TODO this should be variable
		txtpnPlayerLosses.setMargin(new Insets(0, 3, 3, 3));
		txtpnPlayerLosses.setForeground(Color.BLACK);
		txtpnPlayerLosses.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		txtpnPlayerLosses.setEditable(false);
		txtpnPlayerLosses.setBackground(Color.LIGHT_GRAY);
		
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
		
		JLabel lblOpponentWins = new JLabel("Wins");
		lblOpponentWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpponentWins.setForeground(Color.LIGHT_GRAY);
		lblOpponentWins.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblOpponentWins.setBounds(248, 46, 46, 18);
		panelStatus.add(lblOpponentWins);
		
		JTextPane txtpnOpponentWins = new JTextPane();
		txtpnOpponentWins.setText("01");
		txtpnOpponentWins.setMargin(new Insets(0, 3, 3, 3));
		txtpnOpponentWins.setForeground(Color.BLACK);
		txtpnOpponentWins.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		txtpnOpponentWins.setEditable(false);
		txtpnOpponentWins.setBackground(Color.LIGHT_GRAY);
		txtpnOpponentWins.setBounds(294, 44, 23, 23);
		panelStatus.add(txtpnOpponentWins);
		
		JLabel lblOpponentLosses = new JLabel("Losses");
		lblOpponentLosses.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpponentLosses.setForeground(Color.LIGHT_GRAY);
		lblOpponentLosses.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblOpponentLosses.setBounds(341, 46, 46, 18);
		panelStatus.add(lblOpponentLosses);
		
		JTextPane txtpnOpponentLosses = new JTextPane();
		txtpnOpponentLosses.setText("01");
		txtpnOpponentLosses.setMargin(new Insets(0, 3, 3, 3));
		txtpnOpponentLosses.setForeground(Color.BLACK);
		txtpnOpponentLosses.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		txtpnOpponentLosses.setEditable(false);
		txtpnOpponentLosses.setBackground(Color.LIGHT_GRAY);
		txtpnOpponentLosses.setBounds(395, 44, 23, 23);
		panelStatus.add(txtpnOpponentLosses);
		barPlayerHealth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (barPlayerHealth.getValue() == 0)
					cardlayout.show(frame.getContentPane(), "endPanel");
					// TODO set game outcome win/loss variable
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 117, 428, 244);
		panelContent.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JTextPane txtpnGame = new JTextPane();
		txtpnGame.setLocation(97, 0);
		scrollPane.setViewportView(txtpnGame);
		txtpnGame.setText(""); // TODO this needs to be variable and append card action text
		txtpnGame.setFont(new Font("SimSun", Font.PLAIN, 7));
		txtpnGame.setEditable(false);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBounds(81, 371, 437, 200);
		panelContent.add(cardPanel);
		cardPanel.setBorder(null);
		cardPanel.setBackground(Color.BLACK);
		cardPanel.setLayout(new GridLayout(0, 3, 0, 0));
		JButton btnCard1 = new JButton(new ImageIcon(getScaledImage(imgIconDragon.getImage(), 146, 200)));
		cardPanel.add(btnCard1);
		btnCard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO change currently selected card
			}
		});
		JButton btnCard2 = new JButton(new ImageIcon(getScaledImage(imgIconWizard.getImage(), 146, 200)));
		cardPanel.add(btnCard2);
		btnCard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO change currently selected card
			}
		});
		JButton btnCard3 = new JButton(new ImageIcon(getScaledImage(imgIconKnight.getImage(), 146, 200)));
		cardPanel.add(btnCard3);
		btnCard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO change currently selected card
			}
		});
		
		JButton btnPlayCard = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(),220, 40)));
		btnPlayCard.setBounds(189, 603, 220, 40);
		panelContent.add(btnPlayCard);
		btnPlayCard.setVerticalTextPosition(SwingConstants.CENTER);
		btnPlayCard.setText("Play Card");
		btnPlayCard.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPlayCard.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnPlayCard.setBorder(UIManager.getBorder("Button.border"));
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setBounds(518, 640, 71, 25);
		panelContent.add(btnMenu);
		btnMenu.setBackground(Color.LIGHT_GRAY);
		btnMenu.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "mainMenuPanel");
			}
		});
		btnPlayCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO plays currently selected card and draws another
			}
		});
	}
	
	private void initEnd() {
		endPanel = new JPanel();
		frame.getContentPane().add(endPanel, "endPanel");
		endPanel.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(0, 0, 477, 675);
		endPanel.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setBounds(73, 10, 330, 65);
		panelTitle.add(lblTitle);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
		lblTitle.setBackground(Color.BLACK);
		
		ImageIcon imgEndScreen = new ImageIcon(p1.class.getResource("/images/endScreen.jpg"));		
		JLabel lblEndScreen = new JLabel("");
		lblEndScreen.setBounds(0, 0, 477, 675);
		panelTitle.add(lblEndScreen);
		lblEndScreen.setIcon(new ImageIcon(getScaledImage(imgEndScreen.getImage(), 477, 675)));
		lblEndScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEndScreen.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		endPanel.add(panelContent);
		panelContent.setLayout(null);
		
		JButton btnNewGame = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnNewGame.setBounds(189, 324, 220, 40);
		panelContent.add(btnNewGame);
		btnNewGame.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGame.setText("New Game");
		btnNewGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGame.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnNewGame.setBorder(UIManager.getBorder("Button.border"));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");	
			}
		});
		
		JButton btnExit = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnExit.setBounds(189, 499, 220, 40);
		panelContent.add(btnExit);
		btnExit.setVerticalTextPosition(SwingConstants.CENTER);
		btnExit.setText("Exit");
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnExit.setBorder(UIManager.getBorder("Button.border"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JTextField txtYouWin = new JTextField();
		txtYouWin.setBounds(189, 135, 220, 54);
		panelContent.add(txtYouWin);
		txtYouWin.setEditable(false);
		txtYouWin.setBorder(null);
		txtYouWin.setHorizontalAlignment(SwingConstants.CENTER);
		txtYouWin.setBackground(Color.BLACK);
		txtYouWin.setText("YOU WIN!");  //TODO this should be variable
		txtYouWin.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));
		txtYouWin.setForeground(Color.WHITE);
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
	
	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
}
