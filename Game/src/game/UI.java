package game;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.SwingConstants;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeListener;

import game.GameController.MullListener;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class UI {
	static final private ImageIcon imgIconButton = new ImageIcon(UI.class.getResource("/images/buttonPaper.png"));
	static final private File file = new File("gameRecord.txt");

	protected JFrame frame;
	protected CardLayout cardlayout;
	private JPanel mainMenuPanel, rulesPanel, settingsPanel, choosePanel, playPanel, endPanel;
	private JButton btnNewGameStart, btnNewGameEnd, btnContinue, btnDeck0, btnDeck1, btnDeck2, btnCard0, btnCard1,
			btnCard2, btnMulligan, btnPlayCard;
	private JSpinner spinnerDifficulty;
	private Sequencer sequencer;
	private InputStream is;
	private JProgressBar barPlayerHealth, barOpponentHealth;
	private String strEndMsg;
	private JTextArea txtpnGame = new JTextArea();
	private JTextField txtEndMsg = new JTextField();

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();

		try {
			// Create file for game record
			if (!file.exists())
				file.createNewFile();

			// Play music
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			is = new BufferedInputStream(
					ClassLoader.getSystemResourceAsStream("music/bjorn__lynne-_proud_warriors.mid"));
			sequencer.setSequence(is);
			sequencer.start(); // default setting
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void show(String panel) {
		cardlayout.show(frame.getContentPane(), panel);
	}

	public void addNewGameListener(ActionListener newGameListener) {
		btnNewGameStart.addActionListener(newGameListener);
		btnNewGameEnd.addActionListener(newGameListener);
	}

	public void addDeckListener(ActionListener deckListener) {
		btnDeck0.addActionListener(deckListener);
		btnDeck1.addActionListener(deckListener);
		btnDeck2.addActionListener(deckListener);
	}

	public void addCardListener(ActionListener cardListener) {
		btnCard0.addActionListener(cardListener);
		btnCard1.addActionListener(cardListener);
		btnCard2.addActionListener(cardListener);
	}
	
	public void addMulliganListener(MullListener mullListener) {
		btnMulligan.addActionListener(mullListener);	
	}

	public void addPlayCardListener(ActionListener playCardListener) {
		btnPlayCard.addActionListener(playCardListener);
	}

	public void addContinueListener(ActionListener ContinueListener) {
		btnContinue.addActionListener(ContinueListener);
	}

	public void addDifficultyListener(ChangeListener listener) {
		spinnerDifficulty.addChangeListener(listener);
	}

	/**
	 * Initialize the Main Menu screen.
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
		lblMainMenuScreen.setIcon(getScaledImage(imgMainMenuScreen, 477, 675));
		panelTitle.add(lblMainMenuScreen);

		JPanel panelContent = new JPanel();
		panelContent.setBorder(null);
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		mainMenuPanel.add(panelContent);

		btnNewGameStart = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnNewGameStart.setBounds(189, 103, 220, 40);
		btnNewGameStart.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGameStart.setText("New Game");
		btnNewGameStart.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGameStart.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnNewGameStart.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnNewGameStart);

		JButton btnRules = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnRules.setBounds(189, 246, 220, 40);
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
		panelContent.add(btnRules);

		JButton btnSettings = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnSettings.setBounds(189, 389, 220, 40);
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
		panelContent.add(btnSettings);


		btnContinue = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnContinue.setBounds(189, 532, 220, 40);
		btnContinue.setVerticalTextPosition(SwingConstants.CENTER);
		btnContinue.setText("Continue Game");
		btnContinue.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContinue.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnContinue.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnContinue);
	}

	/**
	 * Initialize the Rules screen.
	 */
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
		lblRulesScreen.setIcon(getScaledImage(imgRulesScreen, 477, 675));
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

		JButton btnMainMenu = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnMainMenu.setBounds(189, 593, 220, 40);
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
		panelContent.add(btnMainMenu);

		JTextPane txtpnRules = new JTextPane();
		txtpnRules.setEditable(false);
		txtpnRules.setBackground(Color.BLACK);
		txtpnRules.setForeground(Color.WHITE);
		txtpnRules.setBounds(144, 0, 330, 335);
		txtpnRules.setCaretPosition(0);
		txtpnRules.setFont(new Font("SimSun", Font.PLAIN, 14));
		txtpnRules.setText("- Each player starts the game with a hand containing three cards and a full 100 point health bar\r\n\n"
						+ "- Each turn the players choose a card to play out of his or her hand\r\n\n"
						+ "- After each player has played his chosen card, the two cards will battle\r\n\n"
						+ "- The battle either ends in a win, loss, or draw\r\n\n"
						+ "- If the battle ends in a draw, neither player's health will incur damage\r\n\n"
						+ "- Otherwise, the player that lost the battle will lose 10 health points\r\n\n"
						+ "- The game continues until one of the player's health runs out\r\n\n"
						+ "- A card will have x2 power if it has a type advantage over the other card:  "
						+ "red beats green, green beats blue, blue beats red\r\n\n"
						+ "- A card will have x1.5 power if it has an archetype advantage over the other card:  "
						+ "knight beats dragon, dragon beats wizard, wizard beats knight\r\n\n"
						+ "- A player has the option to use one \"mulligan\" which will replace the players hand inflict the same damage as a loss");
		rulesPanel.add(txtpnRules);

		JScrollPane scrollPane = new JScrollPane(txtpnRules);
		scrollPane.setBounds(16, 131, 567, 420);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panelContent.add(scrollPane);
	}

	/**
	 * Initialize the Settings screen.
	 */
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
		lblSettingsScreen.setIcon(getScaledImage(imgSettingsScreen, 477, 675));
		panelTitle.add(lblSettingsScreen);

		JPanel panelContent = new JPanel();
		panelContent.setBackground(new Color(0, 0, 0));
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		settingsPanel.add(panelContent);

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
		spinnerSound.setModel(new SpinnerListModel(new String[] { "On", "Off" }));
		spinnerSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (spinnerSound.getValue() == "On")
					sequencer.start();
				else
					sequencer.stop();
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
		spinnerDifficulty.setModel(new SpinnerListModel(new String[] {"Easy", "Hard"}));
		spinnerDifficulty.setFont(new Font("SimSun", Font.PLAIN, 26));
		panel.add(spinnerDifficulty);

		JButton btnMainMenu = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnMainMenu.setBounds(189, 529, 220, 40);
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
		panelContent.add(btnMainMenu);
	}

	/**
	 * Initialize the Choose screen.
	 */
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
		lblChooseScreen.setIcon(getScaledImage(imgChooseScreen, 477, 675));
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

		ImageIcon imgIconDeck0 = new ImageIcon(UI.class.getResource("/images/deck0.png"));
		ImageIcon imgIconDeck1 = new ImageIcon(UI.class.getResource("/images/deck1.png"));
		ImageIcon imgIconDeck2 = new ImageIcon(UI.class.getResource("/images/deck2.png"));

		btnDeck0 = new JButton(getScaledImage(imgIconDeck0, 127, 175));
		deckPanel.add(getBtnDeck0());

		btnDeck1 = new JButton(getScaledImage(imgIconDeck1, 127, 175));
		deckPanel.add(btnDeck1);

		btnDeck2 = new JButton(getScaledImage(imgIconDeck2, 127, 175));
		deckPanel.add(btnDeck2);
	}

	/**
	 * Initialize the Play screen.
	 */
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
		lblPlayScreen.setIcon(getScaledImage(imgPlayScreen, 477, 675));
		lblPlayScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayScreen.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.add(lblPlayScreen);

		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		playPanel.add(panelContent);

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

		barPlayerHealth = new JProgressBar();
		barPlayerHealth.setBounds(125, 14, 111, 18);
		barPlayerHealth.setValue(100);
		barPlayerHealth.setForeground(Color.GREEN);
		panelStatus.add(barPlayerHealth);

		JLabel lblOpponentHealth = new JLabel("Opponent Health");
		lblOpponentHealth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpponentHealth.setForeground(Color.LIGHT_GRAY);
		lblOpponentHealth.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblOpponentHealth.setBounds(0, 45, 115, 18);
		panelStatus.add(lblOpponentHealth);

		barOpponentHealth = new JProgressBar();
		barOpponentHealth.setValue(100);
		barOpponentHealth.setForeground(Color.GREEN);
		barOpponentHealth.setBounds(125, 46, 111, 18);
		panelStatus.add(barOpponentHealth);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.setBounds(287, 17, 100, 40);
		btnMainMenu.setBackground(Color.LIGHT_GRAY);
		btnMainMenu.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "mainMenuPanel");
			}
		});
		panelStatus.add(btnMainMenu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 117, 428, 244);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelContent.add(scrollPane);

		getTxtpnGame().setLocation(97, 0);
		getTxtpnGame().setFont(new Font("SimSun", Font.PLAIN, 9));
		resetText();
		getTxtpnGame().setEditable(false);
		scrollPane.setViewportView(getTxtpnGame());

		JPanel cardPanel = new JPanel();
		cardPanel.setBounds(81, 371, 437, 200);
		cardPanel.setBorder(null);
		cardPanel.setBackground(Color.BLACK);
		cardPanel.setLayout(new GridLayout(0, 3, 0, 0));
		panelContent.add(cardPanel);

		btnCard0 = new JButton();
		btnCard0.setBorder(BorderFactory.createLineBorder(Color.red, 5));
		btnCard0.setBorderPainted(false);
		cardPanel.add(btnCard0);

		btnCard1 = new JButton();
		btnCard1.setBorder(BorderFactory.createLineBorder(Color.red, 5));
		btnCard1.setBorderPainted(false);
		cardPanel.add(btnCard1);

		btnCard2 = new JButton();
		btnCard2.setBorder(BorderFactory.createLineBorder(Color.red, 5));
		btnCard2.setBorderPainted(false);
		cardPanel.add(btnCard2);

		btnMulligan = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnMulligan.setBounds(47 , 603, 220, 40);
		btnMulligan.setVerticalTextPosition(SwingConstants.CENTER);
		btnMulligan.setText("Mulligan");
		btnMulligan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMulligan.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMulligan.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnMulligan);
		
		btnPlayCard = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnPlayCard.setBounds(330, 603, 220, 40);
		btnPlayCard.setVerticalTextPosition(SwingConstants.CENTER);
		btnPlayCard.setText("Play Card");
		btnPlayCard.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPlayCard.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnPlayCard.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnPlayCard);
	}

	/**
	 * Initialize the End screen.
	 */
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
		lblEndScreen.setIcon(getScaledImage(imgEndScreen, 477, 675));
		lblEndScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEndScreen.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.add(lblEndScreen);

		JPanel panelContent = new JPanel();
		panelContent.setBackground(Color.BLACK);
		panelContent.setBounds(477, 0, 599, 675);
		panelContent.setLayout(null);
		endPanel.add(panelContent);

		btnNewGameEnd = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnNewGameEnd.setBounds(189, 324, 220, 40);
		btnNewGameEnd.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGameEnd.setText("New Game");
		btnNewGameEnd.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGameEnd.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnNewGameEnd.setBorder(UIManager.getBorder("Button.border"));
		panelContent.add(btnNewGameEnd);

		JButton btnExit = new JButton(getScaledImage(imgIconButton, 220, 40));
		btnExit.setBounds(189, 499, 220, 40);
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
		panelContent.add(btnExit);

		txtEndMsg.setBounds(189, 135, 220, 54);
		txtEndMsg.setEditable(false);
		txtEndMsg.setBorder(null);
		txtEndMsg.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndMsg.setBackground(Color.BLACK);
		txtEndMsg.setText(strEndMsg);
		txtEndMsg.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));
		txtEndMsg.setForeground(Color.WHITE);
		panelContent.add(txtEndMsg);
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
	}

	/**
	 * Returns an ImageIcon scaled to specified parameters
	 * 
	 * @param srcImgIcon an ImageIcon created from image in images package
	 * @param w          the desired width
	 * @param h          the desired height
	 * @return the scaled ImageIcon
	 */
	private ImageIcon getScaledImage(ImageIcon srcImgIcon, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImgIcon.getImage(), 0, 0, w, h, null);
		g2.dispose();
		return new ImageIcon(resizedImg);
	}

	/**
	 * Sets the icon for the Card buttons when a card is drawn from the deck.
	 *
	 * @param cardPosition the specified button artwork that needs updated
	 * @param imgIcon      the specified artwork that should be shown
	 */
	public void setImageIcon(int cardPosition, ImageIcon imgIcon) {
		switch (cardPosition) {
		case 0:
			btnCard0.setIcon(getScaledImage(imgIcon, 146, 200));
		case 1:
			btnCard1.setIcon(getScaledImage(imgIcon, 146, 200));
		case 2:
			btnCard2.setIcon(getScaledImage(imgIcon, 146, 200));
		}
	}

	/**
	 * Set the border around a card when a card is selected.
	 * 
	 * @param cardPosition  specifies which card should have a border
	 */
	public void setChosenBorder(int cardPosition) {

		btnCard0.setBorderPainted(false);
		btnCard1.setBorderPainted(false);
		btnCard2.setBorderPainted(false);
		
		switch (cardPosition) {
		case 0:
			btnCard0.setBorderPainted(true);
			break;
		case 1:
			btnCard1.setBorderPainted(true);
			break;
		case 2:
			btnCard2.setBorderPainted(true);
			break;
		}
	}

	public void setHealth(int userHealth, int opHealth) {
		barPlayerHealth.setValue(userHealth);
		barOpponentHealth.setValue(opHealth);
	}

	/**
	 * Appends text to the text area document
	 * 
	 * @param strTxt the text that will be appended to the document
	 */
	public void appendText(String strTxt) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
			out.write(strTxt + "\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void refreshText() {
		getTxtpnGame().setText("");
		getTxtpnGame().setFont(new Font("SimSun", Font.PLAIN, 13));
		try {
			// opening file and appending all file contents to message area
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				getTxtpnGame().append(scanner.nextLine() + "\n");
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void resetText() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		getTxtpnGame().setText("          ,   ,\r\n" + "         ,-`{-`/\r\n" + "      ,-~ , \\ {-~~-,\r\n"
				+ "    ,~  ,   ,`,-~~-,`,\r\n"
				+ "  ,`   ,   { {      } }                                             }/\r\n"
				+ " ;     ,--/`\\ \\    / /                                     }/      /,/\r\n"
				+ ";  ,-./      \\ \\  { {  (                                  /,;    ,/ ,/\r\n"
				+ "; /   `       } } `, `-`-.___                            / `,  ,/  `,/\r\n"
				+ " \\|         ,`,`    `~.___,---}                         / ,`,,/  ,`,;\r\n"
				+ "  `        { {                                     __  /  ,`/   ,`,;\r\n"
				+ "        /   \\ \\                                 _,`, `{  `,{   `,`;`\r\n"
				+ "       {     } }       /~\\         .-:::-.     (--,   ;\\ `,}  `,`;\r\n"
				+ "       \\\\._./ /      /` , \\      ,:::::::::,     `~;   \\},/  `,`;     ,-=-\r\n"
				+ "        `-..-`      /. `  .\\_   ;:::::::::::;  __,{     `/  `,`;     {\r\n"
				+ "                   / , ~ . ^ `~`\\:::::::::::<<~>-,,`,    `-,  ``,_    }\r\n"
				+ "                /~~ . `  . ~  , .`~~\\:::::::;    _-~  ;__,        `,-`\r\n"
				+ "       /`\\    /~,  . ~ , '  `  ,  .` \\::::;`   <<<~```   ``-,,__   ;\r\n"
				+ "      /` .`\\ /` .  ^  ,  ~  ,  . ` . ~\\~                       \\\\, `,__\r\n"
				+ "     / ` , ,`\\.  ` ~  ,  ^ ,  `  ~ . . ``~~~`,                   `-`--, \\\r\n"
				+ "    / , ~ . ~ \\ , ` .  ^  `  , . ^   .   , ` .`-,___,---,__            ``\r\n"
				+ "  /` ` . ~ . ` `\\ `  ~  ,  .  ,  `  ,  . ~  ^  ,  .  ~  , .`~---,___\r\n"
				+ "/` . `  ,  . ~ , \\  `  ~  ,  .  ^  ,  ~  .  `  ,  ~  .  ^  ,  ~  .  `-,\n");
	}

	public JButton getBtnDeck0() {
		return btnDeck0;
	}

	public JButton getBtnDeck1() {
		return btnDeck1;
	}

	public JButton getBtnDeck2() {
		return btnDeck2;
	}

	public JTextArea getTxtpnGame() {
		return txtpnGame;
	}

	public void setTxtpnGame(JTextArea txtpnGame) {
		this.txtpnGame = txtpnGame;
	}

	public JButton getBtnCard0() {
		return btnCard0;
	}

	public JButton getBtnCard1() {
		return btnCard1;
	}

	public JButton getBtnCard2() {
		return btnCard2;
	}

	public int getUserHealth() {
		return barPlayerHealth.getValue();
	}

	public int getOpHealth() {
		return barOpponentHealth.getValue();
	}

	public Object getDifficulty() {
		return spinnerDifficulty.getValue();
	}

	public void setEndMessage(String msg) {
		txtEndMsg.setText(msg);
	}
}