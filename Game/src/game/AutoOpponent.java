package game;

import java.util.Random;

public class AutoOpponent extends Participant {
	protected int difficulty;

	public int choiceAI(int difficulty)
	{
		return 0;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public int choice() {
		int choice;
		
		if(difficulty == 1) 
			if(hand.get(0).getPower() >= hand.get(1).getPower()
			&& hand.get(0).getPower() >= hand.get(2).getPower())
				choice = 0;
			else if(hand.get(1).getPower() >= hand.get(0).getPower()
			&& hand.get(1).getPower() >= hand.get(2).getPower())
				choice = 1;
			else
				choice = 2;
		
		else
			choice = new Random().nextInt(2);
		return choice;
	}
	
	// play card from hand and replace from deck
	public Card playCard(int cardPosition) {

		Card chosenCard = hand.get(cardPosition);

		return chosenCard;
	}
}