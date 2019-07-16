package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
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
import java.awt.Window;
import java.awt.image.BufferedImage;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class p1 {

	private JFrame frame;
	private CardLayout cardlayout;
	private JPanel startPanel, aboutPanel, settingsPanel, choosePanel, playPanel, endPanel;
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
	private void initStart() {
		startPanel = new JPanel();
		frame.getContentPane().add(startPanel, "startPanel");
		startPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 29));
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setBounds(55, 10, 248, 47);
		startPanel.add(lblTitle);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(null);
		panelButtons.setBackground(Color.BLACK);
		panelButtons.setBounds(428, 37, 283, 420);
		panelButtons.setLayout(null);
		startPanel.add(panelButtons);

		JButton btnNewGame = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnNewGame.setBounds(32, 52, 220, 40);
		panelButtons.add(btnNewGame);
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
		
		JButton btnAbout = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnAbout.setBounds(32, 144, 220, 40);
		panelButtons.add(btnAbout);
		btnAbout.setVerticalTextPosition(SwingConstants.CENTER);
		btnAbout.setText("About");
		btnAbout.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAbout.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnAbout.setBorder(UIManager.getBorder("Button.border"));
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "aboutPanel");
			}
		});
		
		JButton btnSettings = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnSettings.setBounds(32, 236, 220, 40);
		panelButtons.add(btnSettings);
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
		btnContinue.setBounds(32, 328, 220, 40);
		panelButtons.add(btnContinue);
		btnContinue.setVerticalTextPosition(SwingConstants.CENTER);
		btnContinue.setText("Continue Game");
		btnContinue.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContinue.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnContinue.setBorder(UIManager.getBorder("Button.border"));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");
				// TODO reset game environment
			}
		});
		
		JLabel lblStartScreen = new JLabel("");
		lblStartScreen.setIcon(new ImageIcon(p1.class.getResource("/images/startScreen800x500.jpg")));
		lblStartScreen.setBounds(0, 0, 800, 500);
		startPanel.add(lblStartScreen);
	}
	
	private void initAbout() {
		aboutPanel = new JPanel();
		frame.getContentPane().add(aboutPanel, "aboutPanel");
		aboutPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 29));
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setBounds(55, 10, 248, 47);
		aboutPanel.add(lblTitle);
		
		JLabel lblAbout = new JLabel("ABOUT");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setForeground(Color.WHITE);
		lblAbout.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
		lblAbout.setBackground(Color.BLACK);
		lblAbout.setBounds(501, 37, 148, 47);
		aboutPanel.add(lblAbout);
		
		JTextPane txtpnAbout = new JTextPane();
		txtpnAbout.setFont(new Font("SimSun", Font.PLAIN, 18));
		txtpnAbout.setText("This game was developed by Round Rock Team 1.\r\n\r\nTo play, first...\r\n<Insert Instructions>");
		txtpnAbout.setBackground(Color.BLACK);
		txtpnAbout.setForeground(Color.WHITE);
		txtpnAbout.setBounds(410, 121, 330, 259);
		aboutPanel.add(txtpnAbout);
		
		JButton btnMainMenu = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setText("Main Menu");
		btnMainMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setBorder(UIManager.getBorder("Button.border"));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "startPanel");
			}
		});
		btnMainMenu.setBounds(465, 417, 220, 40);
		aboutPanel.add(btnMainMenu);
		
		JLabel lblAboutScreen = new JLabel("");
		lblAboutScreen.setIcon(new ImageIcon(p1.class.getResource("/images/aboutScreen800x500.jpg")));
		lblAboutScreen.setBounds(0, 0, 800, 500);
		aboutPanel.add(lblAboutScreen);
	}
	
	private void initSettings() {
		settingsPanel = new JPanel();
		frame.getContentPane().add(settingsPanel, "settingsPanel");
		settingsPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 29));
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setBounds(55, 10, 248, 47);
		settingsPanel.add(lblTitle);
		
		JLabel lblSettings = new JLabel("SETTINGS");
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setFont(new Font("Viner Hand ITC", Font.BOLD, 26));
		lblSettings.setBackground(Color.BLACK);
		lblSettings.setBounds(498, 53, 148, 47);
		settingsPanel.add(lblSettings);
		
		JLabel lblSound = new JLabel("Sound");
		lblSound.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSound.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		lblSound.setBackground(Color.BLACK);
		lblSound.setForeground(Color.WHITE);
		lblSound.setBounds(478, 174, 83, 27);
		settingsPanel.add(lblSound);
		
		JSpinner spinnerSound = new JSpinner();
		spinnerSound.setFont(new Font("SimSun", Font.PLAIN, 26));
		spinnerSound.setModel(new SpinnerListModel(new String[] {"On", "Off"}));
		spinnerSound.setBounds(583, 167, 100, 40);
		spinnerSound.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(spinnerSound.getValue() == "On") playMusic(true);
				else playMusic(false);
			}
		});
		settingsPanel.add(spinnerSound);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDifficulty.setForeground(Color.WHITE);
		lblDifficulty.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		lblDifficulty.setBackground(Color.BLACK);
		lblDifficulty.setBounds(437, 298, 124, 27);
		settingsPanel.add(lblDifficulty);
		
		JSpinner spinnerDifficulty = new JSpinner();
		spinnerDifficulty.setModel(new SpinnerListModel(new String[] {"Easy", "Normal", "Hard"}));
		spinnerDifficulty.setFont(new Font("SimSun", Font.PLAIN, 26));
		spinnerDifficulty.setBounds(583, 291, 100, 40);
		spinnerDifficulty.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) { // TODO fill in difficulty changes
				if(spinnerDifficulty.getValue() == "Easy");
				else if(spinnerDifficulty.getValue() == "Normal");
				else;
			}
		});
		settingsPanel.add(spinnerDifficulty);
		
		JButton btnMainMenu = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setText("Main Menu");
		btnMainMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMainMenu.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnMainMenu.setBorder(UIManager.getBorder("Button.border"));
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "startPanel");
			}
		});
		btnMainMenu.setBounds(465, 417, 220, 40);
		settingsPanel.add(btnMainMenu);
		
		JLabel lblSettingsScreen = new JLabel("");
		lblSettingsScreen.setIcon(new ImageIcon(p1.class.getResource("/images/settingsScreen800x500.jpg")));
		lblSettingsScreen.setBounds(0, 0, 800, 500);
		settingsPanel.add(lblSettingsScreen);
	}
	
	private void initChoose() {
		choosePanel = new JPanel();
		frame.getContentPane().add(choosePanel, "choosePanel");
		choosePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 29));
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setBounds(55, 10, 248, 47);
		choosePanel.add(lblTitle);
		
		JLabel lblChooseYourDeck = new JLabel("Choose Your Deck");
		lblChooseYourDeck.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseYourDeck.setBackground(Color.BLACK);
		lblChooseYourDeck.setForeground(Color.WHITE);
		lblChooseYourDeck.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));
		lblChooseYourDeck.setBounds(380, 70, 382, 66);
		choosePanel.add(lblChooseYourDeck);
		
		JPanel deckPanel = new JPanel();
		deckPanel.setBorder(null);
		deckPanel.setBackground(Color.BLACK);
		deckPanel.setBounds(380, 147, 382, 175);
		choosePanel.add(deckPanel);
		deckPanel.setLayout(new GridLayout(0, 3, 0, 0));

		ImageIcon imgIconDeck1 = new ImageIcon(p1.class.getResource("/images/deck1.png"));		
		JButton btnDeck1 = new JButton(new ImageIcon(getScaledImage(imgIconDeck1.getImage(),127, 175)));
		btnDeck1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");	
			}
		});
		deckPanel.add(btnDeck1);
		
		ImageIcon imgIconDeck2 = new ImageIcon(p1.class.getResource("/images/deck2.png"));
		JButton btnDeck2 = new JButton(new ImageIcon(getScaledImage(imgIconDeck2.getImage(),127, 175)));
		btnDeck2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");	
			}
		});
		deckPanel.add(btnDeck2);
		
		ImageIcon imgIconDeck3 = new ImageIcon(p1.class.getResource("/images/deck3.png"));
		JButton btnDeck3 = new JButton(new ImageIcon(getScaledImage(imgIconDeck3.getImage(),127, 175)));
		btnDeck3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");	
			}
		});
		deckPanel.add(btnDeck3);
		
		JLabel lblChooseScreen = new JLabel("");
		lblChooseScreen.setIcon(new ImageIcon(p1.class.getResource("/images/chooseScreen800x500.jpg")));
		lblChooseScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblChooseScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseScreen.setBounds(0, 0, 800, 500);
		choosePanel.add(lblChooseScreen);
	}
	
	private void initPlay() {
		playPanel = new JPanel();
		playPanel.setLayout(null);
		frame.getContentPane().add(playPanel, "playPanel");
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBorder(null);
		cardPanel.setBackground(Color.BLACK);
		cardPanel.setBounds(379, 259, 382, 175);
		playPanel.add(cardPanel);
		cardPanel.setLayout(new GridLayout(0, 3, 0, 0));
			
		ImageIcon imgIconDragon = new ImageIcon(p1.class.getResource("/images/dragon.jpg")); //TODO image should be variable
		JButton btnCard1 = new JButton(new ImageIcon(getScaledImage(imgIconDragon.getImage(),127, 175)));
		cardPanel.add(btnCard1);
		btnCard1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO change currently selected card
			}
		});
		
		ImageIcon imgIconWizard = new ImageIcon(p1.class.getResource("/images/wizard.jpg"));
		JButton btnCard2 = new JButton(new ImageIcon(getScaledImage(imgIconWizard.getImage(),127, 175)));
		cardPanel.add(btnCard2);
		btnCard2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO change currently selected card
			}
		});
		
		ImageIcon imgIconKnight = new ImageIcon(p1.class.getResource("/images/knight.jpg"));
		JButton btnCard3 = new JButton(new ImageIcon(getScaledImage(imgIconKnight.getImage(),127, 175)));
		cardPanel.add(btnCard3);
		btnCard3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO change currently selected card
			}
		});
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 29));
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setBounds(55, 10, 248, 47);
		playPanel.add(lblTitle);
		
		JTextPane txtpnGame = new JTextPane();
		txtpnGame.setText("           ,   ,\r\n         ,-`{-`/\r\n      ,-~ , \\ {-~~-,\r\n    ,~  ,   ,`,-~~-,`,\r\n  ,`   ,   { {      } }                                             }/\r\n ;     ,--/`\\ \\    / /                                     }/      /,/\r\n;  ,-./      \\ \\  { {  (                                  /,;    ,/ ,/\r\n; /   `       } } `, `-`-.___                            / `,  ,/  `,/\r\n \\|         ,`,`    `~.___,---}                         / ,`,,/  ,`,;\r\n  `        { {                                     __  /  ,`/   ,`,;\r\n        /   \\ \\                                 _,`, `{  `,{   `,`;`\r\n       {     } }       /~\\         .-:::-.     (--,   ;\\ `,}  `,`;\r\n       \\\\._./ /      /` , \\      ,:::::::::,     `~;   \\},/  `,`;     ,-=-\r\n        `-..-`      /. `  .\\_   ;:::::::::::;  __,{     `/  `,`;     {\r\n                   / , ~ . ^ `~`\\:::::::::::<<~>-,,`,    `-,  ``,_    }\r\n                /~~ . `  . ~  , .`~~\\:::::::;    _-~  ;__,        `,-`\r\n       /`\\    /~,  . ~ , '  `  ,  .` \\::::;`   <<<~```   ``-,,__   ;\r\n      /` .`\\ /` .  ^  ,  ~  ,  . ` . ~\\~                       \\\\, `,__\r\n     / ` , ,`\\.  ` ~  ,  ^ ,  `  ~ . . ``~~~`,                   `-`--, \\\r\n    / , ~ . ~ \\ , ` .  ^  `  , . ^   .   , ` .`-,___,---,__            ``\r\n  /` ` . ~ . ` `\\ `  ~  ,  .  ,  `  ,  . ~  ^  ,  .  ~  , .`~---,___\r\n/` . `  ,  . ~ , \\  `  ~  ,  .  ^  ,  ~  .  `  ,  ~  .  ^  ,  ~  .  `-,");
		txtpnGame.setFont(new Font("SimSun", Font.PLAIN, 7));
		txtpnGame.setEditable(false);
		txtpnGame.setBounds(379, 49, 382, 193);
		playPanel.add(txtpnGame);
		
		JLabel lblHealth = new JLabel("Health");
		lblHealth.setHorizontalAlignment(SwingConstants.LEFT);
		lblHealth.setForeground(Color.LIGHT_GRAY);
		lblHealth.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblHealth.setBounds(379, 11, 46, 18);
		playPanel.add(lblHealth);
		
		JProgressBar healthBar = new JProgressBar();
		healthBar.setValue(100);  //TODO this should be variable
		healthBar.setForeground(Color.GREEN);
		healthBar.setBounds(424, 12, 111, 18);
		healthBar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (healthBar.getValue() == 0)
					cardlayout.show(frame.getContentPane(), "endPanel");
					// TODO set game outcome win/loss variable
			}
		});
		playPanel.add(healthBar);
		
		JLabel lblWins = new JLabel("Wins");
		lblWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblWins.setForeground(Color.LIGHT_GRAY);
		lblWins.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblWins.setBounds(535, 12, 46, 18);
		playPanel.add(lblWins);
		
		JTextPane txtpnWins = new JTextPane();
		txtpnWins.setText("01"); //TODO this should be variable
		txtpnWins.setMargin(new Insets(0, 3, 3, 3));
		txtpnWins.setForeground(Color.BLACK);
		txtpnWins.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		txtpnWins.setEditable(false);
		txtpnWins.setBackground(Color.LIGHT_GRAY);
		txtpnWins.setBounds(577, 10, 23, 23);
		playPanel.add(txtpnWins);
		
		JLabel lblLosses = new JLabel("Losses");
		lblLosses.setHorizontalAlignment(SwingConstants.CENTER);
		lblLosses.setForeground(Color.LIGHT_GRAY);
		lblLosses.setFont(new Font("SimSun", Font.PLAIN, 14));
		lblLosses.setBounds(608, 12, 46, 18);
		playPanel.add(lblLosses);
		
		JTextPane txtpnLosses = new JTextPane();
		txtpnLosses.setText("01"); //TODO this should be variable
		txtpnLosses.setMargin(new Insets(0, 3, 3, 3));
		txtpnLosses.setForeground(Color.BLACK);
		txtpnLosses.setFont(new Font("Yu Gothic Medium", Font.BOLD, 12));
		txtpnLosses.setEditable(false);
		txtpnLosses.setBackground(Color.LIGHT_GRAY);
		txtpnLosses.setBounds(657, 10, 23, 23);
		playPanel.add(txtpnLosses);
		
		JButton btnPlayCard = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(),220, 40)));
		playPanel.add(btnPlayCard);
		btnPlayCard.setVerticalTextPosition(SwingConstants.CENTER);
		btnPlayCard.setText("Play Card");
		btnPlayCard.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPlayCard.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnPlayCard.setBorder(UIManager.getBorder("Button.border"));
		btnPlayCard.setBounds(460, 444, 220, 40);
		btnPlayCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO plays currently selected card and draws another
			}
		});
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.setBackground(Color.LIGHT_GRAY);
		btnMenu.setFont(new Font("SimSun", Font.PLAIN, 14));
		btnMenu.setBounds(690, 9, 71, 25);
		playPanel.add(btnMenu);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "startPanel");
			}
		});
		
		JLabel lblPlayScreen = new JLabel("");
		lblPlayScreen.setIcon(new ImageIcon(p1.class.getResource("/images/playScreen800x500.png")));
		lblPlayScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayScreen.setBounds(0, 0, 800, 500);
		playPanel.add(lblPlayScreen);
	}
	
	private void initEnd() {
		endPanel = new JPanel();
		frame.getContentPane().add(endPanel, "endPanel");
		endPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Exile & Conquest");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Viner Hand ITC", Font.BOLD, 29));
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setBounds(55, 10, 248, 47);
		endPanel.add(lblTitle);
		
		JButton btnNewGame = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnNewGame.setVerticalTextPosition(SwingConstants.CENTER);
		btnNewGame.setText("Exit");
		btnNewGame.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewGame.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnNewGame.setBorder(UIManager.getBorder("Button.border"));
		btnNewGame.setBounds(458, 364, 220, 40);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(frame.getContentPane(), "playPanel");	
			}
		});
		endPanel.add(btnNewGame);
		
		JButton btnExit = new JButton(new ImageIcon(getScaledImage(imgIconButton.getImage(), 220, 40)));
		btnExit.setVerticalTextPosition(SwingConstants.CENTER);
		btnExit.setText("New Game");
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setFont(new Font("Viner Hand ITC", Font.PLAIN, 24));
		btnExit.setBorder(UIManager.getBorder("Button.border"));
		btnExit.setBounds(458, 234, 220, 40);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		endPanel.add(btnExit);
		
		JTextField txtYouWin = new JTextField();
		txtYouWin.setEditable(false);
		txtYouWin.setBorder(null);
		txtYouWin.setHorizontalAlignment(SwingConstants.CENTER);
		txtYouWin.setBackground(Color.BLACK);
		txtYouWin.setText("YOU WIN!");  //TODO this should be variable
		txtYouWin.setFont(new Font("Viner Hand ITC", Font.PLAIN, 40));
		txtYouWin.setForeground(Color.WHITE);
		txtYouWin.setBounds(458, 90, 220, 54);
		endPanel.add(txtYouWin);
		txtYouWin.setColumns(10);
		
		JLabel lblEndScreen = new JLabel("");
		lblEndScreen.setIcon(new ImageIcon(p1.class.getResource("/images/endScreen800x500.jpg")));
		lblEndScreen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEndScreen.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndScreen.setBounds(0, 0, 800, 500);
		endPanel.add(lblEndScreen);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		
		frame.setResizable(false);
		frame.setMaximumSize(new Dimension(800, 525));
		frame.setMinimumSize(new Dimension(800, 525));
	    frame.setPreferredSize(new Dimension(400, 300));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    cardlayout = new CardLayout(0, 0);
		frame.getContentPane().setLayout(cardlayout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initStart();
	    initAbout();
	    initSettings();
	    initChoose();
		initPlay();
	    initEnd();

		// Commands used to switch which Panel is viewed for debug
//		cardlayout.show(frame.getContentPane(), "startPanel");
//		cardlayout.show(frame.getContentPane(), "aboutPanel");
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
