package game;

import java.util.Random;

public class AutoOpponent extends Participant {
	private int difficulty;
	
	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * AI for making a card selection.
	 * If the difficulty is hard it chooses best available, else it chooses a random selection then sets cardPosition.
	 */
	public void makeChoice() {
		int choice;
		
		if (difficulty == 1) { // Hard
			if (hand[0].getPower() >= hand[1].getPower() && hand[0].getPower() >= hand[2].getPower())
				choice = 0;
			else if (hand[1].getPower() >= hand[0].getPower() && hand[1].getPower() >= hand[2].getPower())
				choice = 1;
			else
				choice = 2;
		}
		else // if (difficulty == 0), Easy
			choice = new Random().nextInt(2);
		
		this.cardPosition = choice;
	}
	
	// play card from hand
	public Card playCard() {
		makeChoice();
		return hand[cardPosition];
	}
}