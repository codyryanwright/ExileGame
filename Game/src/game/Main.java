package game;

import java.awt.EventQueue;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				UI view = new UI();
				view.frame.setVisible(true);
				@SuppressWarnings("unused")
				GameController controller = new GameController(view);
			}
		});
	}
}
