package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.border.MatteBorder;

public class p1 {

	private JFrame frame;
	private JTextField txtExileConquest;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setMaximumSize(new Dimension(800, 500));
		frame.setMinimumSize(new Dimension(800, 500));
	    frame.setPreferredSize(new Dimension(400, 300));
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(350, 265, 175, 200);
		panel.add(layeredPane);
		
		JTextPane txtpnCardHolder_1 = new JTextPane();
		txtpnCardHolder_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.BLUE));
		txtpnCardHolder_1.setBounds(0, 0, 175, 200);
		layeredPane.add(txtpnCardHolder_1);
		txtpnCardHolder_1.setEditable(false);
		txtpnCardHolder_1.setBackground(Color.LIGHT_GRAY);
		txtpnCardHolder_1.setText("Card Holder 1");
		txtpnCardHolder_1.setFont(new Font("SimSun", Font.PLAIN, 12));
		
		JTextPane txtpnCardHolder_0 = new JTextPane();
		// if card.color == "red" then Color.RED
		txtpnCardHolder_0.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.RED));
		txtpnCardHolder_0.setBounds(0, 0, 175, 200);
		layeredPane.add(txtpnCardHolder_0);
		txtpnCardHolder_0.setBackground(Color.LIGHT_GRAY);
		txtpnCardHolder_0.setForeground(Color.BLACK);
		txtpnCardHolder_0.setText("Card Holder 0");
		txtpnCardHolder_0.setFont(new Font("SimSun", Font.PLAIN, 12));
		txtpnCardHolder_0.setEditable(false);
		
		JTextPane txtpnCardHolder_2 = new JTextPane();
		txtpnCardHolder_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) Color.GREEN));
		txtpnCardHolder_2.setBounds(0, 0, 175, 200);
		layeredPane.add(txtpnCardHolder_2);
		txtpnCardHolder_2.setText("Card Holder 2");
		txtpnCardHolder_2.setForeground(Color.BLACK);
		txtpnCardHolder_2.setFont(new Font("SimSun", Font.PLAIN, 12));
		txtpnCardHolder_2.setEditable(false);
		txtpnCardHolder_2.setBackground(Color.LIGHT_GRAY);
		
		txtExileConquest = new JTextField();
		txtExileConquest.setBorder(null);
		txtExileConquest.setBounds(453, 10, 253, 43);
		panel.add(txtExileConquest);
		txtExileConquest.setBackground(Color.BLACK);
		txtExileConquest.setEditable(false);
		txtExileConquest.setForeground(Color.WHITE);
		txtExileConquest.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
		txtExileConquest.setText("Exile & Conquest");
		txtExileConquest.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("SimSun", Font.PLAIN, 7));
		textPane.setText("           ,   ,\r\n         ,-`{-`/\r\n      ,-~ , \\ {-~~-,\r\n    ,~  ,   ,`,-~~-,`,\r\n  ,`   ,   { {      } }                                             }/\r\n ;     ,--/`\\ \\    / /                                     }/      /,/\r\n;  ,-./      \\ \\  { {  (                                  /,;    ,/ ,/\r\n; /   `       } } `, `-`-.___                            / `,  ,/  `,/\r\n \\|         ,`,`    `~.___,---}                         / ,`,,/  ,`,;\r\n  `        { {                                     __  /  ,`/   ,`,;\r\n        /   \\ \\                                 _,`, `{  `,{   `,`;`\r\n       {     } }       /~\\         .-:::-.     (--,   ;\\ `,}  `,`;\r\n       \\\\._./ /      /` , \\      ,:::::::::,     `~;   \\},/  `,`;     ,-=-\r\n        `-..-`      /. `  .\\_   ;:::::::::::;  __,{     `/  `,`;     {\r\n                   / , ~ . ^ `~`\\:::::::::::<<~>-,,`,    `-,  ``,_    }\r\n                /~~ . `  . ~  , .`~~\\:::::::;    _-~  ;__,        `,-`\r\n       /`\\    /~,  . ~ , '  `  ,  .` \\::::;`   <<<~```   ``-,,__   ;\r\n      /` .`\\ /` .  ^  ,  ~  ,  . ` . ~\\~                       \\\\, `,__\r\n     / ` , ,`\\.  ` ~  ,  ^ ,  `  ~ . . ``~~~`,                   `-`--, \\\r\n    / , ~ . ~ \\ , ` .  ^  `  , . ^   .   , ` .`-,___,---,__            ``\r\n  /` ` . ~ . ` `\\ `  ~  ,  .  ,  `  ,  . ~  ^  ,  .  ~  , .`~---,___\r\n/` . `  ,  . ~ , \\  `  ~  ,  .  ^  ,  ~  .  `  ,  ~  .  ^  ,  ~  .  `-,");
		textPane.setBounds(350, 63, 400, 193);
		panel.add(textPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 800, 500);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/GameSkin800x500.png")));
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 800, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// play Game music
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
 			sequencer.open();
 			InputStream is = new BufferedInputStream(ClassLoader.getSystemResourceAsStream("music/bjorn__lynne-_proud_warriors.mid"));
			sequencer.setSequence(is);
			sequencer.start();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
