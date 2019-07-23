package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GameController {
	static final int DECK_TOTAL = 3;
	private Deck[] collection = new Deck[DECK_TOTAL];
	private int deckChoice;
	private UI view;
	public Participant user;
	public Participant opponent;	
	
	public GameController(UI view) throws IOException {
		deckChoice = -1;
		this.view = view;
		user = new User();
		
		opponent = new AutoOpponent();
		
		buildCollection();
	
		// TODO add action listeners to view
		view.addNewGameListener(new NewGameListener());
		view.addDeck0Listener(new Deck0Listener());
		view.addDeck1Listener(new Deck1Listener());
		view.addDeck2Listener(new Deck2Listener());
		view.addCard0Listener(new Card0Listener());
		view.addCard1Listener(new Card1Listener());
		view.addCard2Listener(new Card2Listener());
		view.addPlayCardListener(new PlayCardListener());
		view.addContinueListener(new ContinueListener());
	}

	class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.show("choosePanel");
		}
	}
	
	// TODO a way to combine similar button listeners?
	class Deck0Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = 0;
			user.setDeck(collection[deckChoice]);
			view.show("playPanel");
		}
	}
	
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
	
	class Card0Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user.setCardPosition(0);
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
	
	class PlayCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (user.getCardPosition() == -1)
				// card not chosen do nothing
				return;
			else
			{
				//gets a card from the user and the opponent
				Card userCard = ((User) user).playCard();
				Card opCard = ((AutoOpponent) opponent).playCard(((AutoOpponent) opponent).choice());
				
				compareCards(userCard, opCard);
				user.pushToDiscard(userCard);
				opponent.pushToDiscard(opCard);
				user.setCardPosition(-1); // resets card position
				
				// TODO set card art for new card
				// 		update score / health
				//		check if game over
			}
		}
	}
	
	class ContinueListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(deckChoice == -1)
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
	
	public void compareCards(Card userCard, Card opponentCard){
		// TODO game logic
	}
}