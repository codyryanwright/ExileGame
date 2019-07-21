package game;

import java.io.IOException;

public class GameController {
	final int deckTotal = 3;
	private Deck[] collection = new Deck[deckTotal];
	private int deckChoice;
	
	public GameController() throws IOException {
		buildCollection();
	}
	
	public void setDeckChoice(int deckChoice) {
		this.deckChoice = deckChoice;
	}
	
	public void buildCollection() throws IOException {
		for(int i = 0; i < deckTotal; i++)
			collection[i] = new Deck(i+1);
	}
	
	public int getDeckChoice() {
		return deckChoice;
	}
	
	public Deck getDeck() {
		return collection[deckChoice];
	}
	
	// pops a card from chosen deck
	public Card draw(int cardPosition) {
		return null;
	}
	
	public Deck giveDeck(int deckChoice) {
		return null;
	}
	
	void compareCards(Card userCard, Card opponentCard){
		// TODO game logic
	}
}