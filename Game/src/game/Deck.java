package game;

import java.util.Collections;
import java.util.Stack;

public class Deck {
	private Stack <Card> deck;
	private Stack <Card> discard;
	private Card playedCard;
	final private int deckSize = 20;
	
	public Deck(int deckChoice) {
		buildDeck(deckChoice);
	}
	
	private void buildDeck(int deckChoice) {
	}
	
	private void loadBluedeck() {
	}
	
	private void loadReddeck() {
	}
	
	private void loadGreendeck() {
	}
	
	public Card getCard() {
		if(deck.isEmpty())
			swapDecks();
		
		playedCard = deck.pop();
		discard.push(playedCard);

		return playedCard;
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
