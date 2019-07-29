package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Stack;

public class Deck {
	private Stack <Card> deck;
	private Stack <Card> discard;
	private Card playedCard;
	final private int deckSize = 20;
	
	public Deck(int deckChoice) {
		try {
			deck = new Stack<Card>();
			discard = new Stack<Card>();
			loadDeck(deckChoice);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		shuffle();
	}
	
	private void loadDeck(int deckChoice) throws IOException {
		BufferedReader bufferedReader = null;
		
		switch (deckChoice) {
		case 1: bufferedReader = new BufferedReader(new FileReader("./src/game/buildRedDeck.txt"));
			break;
		case 2: bufferedReader = new BufferedReader(new FileReader("./src/game/buildGreenDeck.txt"));
			break;
		case 3: bufferedReader = new BufferedReader(new FileReader("./src/game/buildBlueDeck.txt"));
			break;
		}
		
		int lineCount = 1;
		
		try {
			String line = bufferedReader.readLine();
			
			while (lineCount <= deckSize) {
		        String[] split = line.split("\\s+");
		        Card cardToAdd = new Card(split[0], split[1], 
		        		split[2], Float.valueOf(split[3]), split[4]);
		        
//		        For testing
//		        Card cardToAdd = new Card(split[0], split[1], 
//		        		split[2], Float.valueOf(split[3]));
//		        
		        
		        deck.push(cardToAdd);
		        
				line = bufferedReader.readLine();
				lineCount++;
			}
		} finally {
			bufferedReader.close();
		}
	}
	
	public void setDeck(Stack<Card> deck) {
		this.deck = deck;
	}
	
	public  Stack<Card> getDeck() {
		return deck;
	}
	
	public Card getCard() {
		if(deck.isEmpty())
			swapDecks();
		
		playedCard = deck.pop();
//		discard.push(playedCard);

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

	public void pushToDiscard(Card userCard) {
		discard.push(userCard);
		
	}
}
