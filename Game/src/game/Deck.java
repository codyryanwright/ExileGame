package game;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	private Stack <Card> deck;
	private Stack <Card> discard;
	private int deckSize; // Do we really need this, or is it just deck.size()?
	
	public Deck(int deckChoice) {
		// TODO build deck
	}
	
	public void setDeckSize(int deckSize) {
		this.deckSize = deckSize;
	}
	
	public int getDeckSize() {
		return deckSize;
	}
	
	public Card getCard() {
		if(deck.isEmpty())
			swapDecks();
		
		return deck.pop();
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public void swapDecks() {
		while(!discard.isEmpty())
			deck.push(discard.pop());
		
		shuffle();
	}
}
