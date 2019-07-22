package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GameController {
	static final int DECK_TOTAL = 3;
	private Deck[] collection = new Deck[DECK_TOTAL];
	private int deckChoice;
	private UI view;
	private Participant user, opponent;	
	
	public GameController(UI view) throws IOException {
		deckChoice = 0;
		this.view = view;
		user = new User();
		
		opponent = new AutoOpponent();
		
		buildCollection();
	
		// TODO add action listeners to view
		view.addNewGameListener(new NewGameListener());
		view.addDeck1Listener(new Deck1Listener());
		view.addDeck2Listener(new Deck2Listener());
		view.addDeck3Listener(new Deck3Listener());
		view.addCard1Listener(new Card1Listener());
		view.addCard2Listener(new Card2Listener());
		view.addCard3Listener(new Card3Listener());
		view.addPlayCardListener(new PlayCardListener());
		view.addContinueListener(new ContinueListener());
	}

	class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.show("choosePanel");
		}
	}
	
	// TODO a way to combine similar button listeners?
	class Deck1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = 1;
			user.setDeck(collection[deckChoice]);
			view.show("playPanel");
		}
	}

	class Deck2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = 2;
			user.setDeck(collection[deckChoice]);
			view.show("playPanel");
		}
	}
	
	class Deck3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = 3;
			user.setDeck(collection[deckChoice]);
			view.show("playPanel");
		}
	}
	
	class Card1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user.setCardPosition(1);
		}
	}
	
	class Card2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user.setCardPosition(2);
		}
	}
	
	class Card3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user.setCardPosition(3);
		}
	}
	
	class PlayCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			compareCards(user.playCard(), opponent.playCard());
			// TODO set card art for new card
			// 		update score / health
			//		check if game over
		}
	}
	
	class ContinueListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(deckChoice == 0)
				view.show("choosePanel");
			else
				view.show("playPanel");
		}
	}
	
	public void setDeckChoice(int deckChoice) {
		this.deckChoice = deckChoice;
	}
	
	public void buildCollection() throws IOException {
		for(int i = 0; i < DECK_TOTAL; i++)
			collection[i] = new Deck(i+1);
	}
	
	public int getDeckChoice() {
		return deckChoice;
	}
	
	public Deck getDeck() {
		return collection[deckChoice];
	}
	
//	TODO DONT THINK WE NEED THIS HERE, PARTICIPANT OWNS THE DECK
//	// pops a card from chosen deck
//	public Card draw(int cardPosition) {
//		return null;
//	}
	
//	TODO DONT NEED THIS, CONTROLLER CAN CALL participant.setDeck()
//	public Deck giveDeck(int deckChoice) {
//		return null;
//	}
	
	void compareCards(Card userCard, Card opponentCard){
		// TODO game logic
	}
}