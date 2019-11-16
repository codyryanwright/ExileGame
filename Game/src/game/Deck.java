package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Stack;

public class Deck {
	static final int DECK_SIZE = 20;
	private Stack <Card> deck;
	private Stack <Card> discard;
	
	public Deck(int deckChoice) {
		deck = new Stack<Card>();
		discard = new Stack<Card>();
		loadDeck(deckChoice);
		shuffle();
	}
	
	/**
	 * Reads from a file to build the deck.
	 * 
	 * @param deckChoice the case that specifies which deck to build.
	 */
	private void loadDeck(int deckChoice) {
		BufferedReader bufferedReader = null;
		
		try {
			switch (deckChoice) {
				case 0: bufferedReader = new BufferedReader(new FileReader(Deck.class.getResource("buildRedDeck.txt").getPath()));
					break;
				case 1: bufferedReader = new BufferedReader(new FileReader(Deck.class.getResource("buildGreenDeck.txt").getPath()));
					break;
				case 2: bufferedReader = new BufferedReader(new FileReader(Deck.class.getResource("buildBlueDeck.txt").getPath()));
					break;
			}
			
			int lineCount = 1;
			String line = bufferedReader.readLine();
			
			while (lineCount <= DECK_SIZE) {
		        String[] split = line.split("\\s+");
		        Card cardToAdd = new Card(split[0], split[1], split[2], Float.valueOf(split[3]), split[4]);
		        deck.push(cardToAdd);
				line = bufferedReader.readLine();
				lineCount++;
			}
			
			bufferedReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDeck(Stack<Card> deck) {
		this.deck = deck;
	}
	
	public  Stack<Card> getDeck() {
		return deck;
	}
	
	public Card getCard() {
		if(deck.isEmpty()) reset();
		
		Card playedCard = deck.pop();
		discard.push(playedCard);

		return playedCard;
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	/**
	 *  Moves cards from discard pile back to deck
	 */
	public void reset() {
		while(!discard.isEmpty())
			deck.push(discard.pop());
		
		shuffle();
	}
	
	public void discard(Card card) {
		discard.push(card);
	}
	
	public int discardSize() {
		return discard.size();
	}
	
	public int deckSize() {
		return deck.size();
	}
}
