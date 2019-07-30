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
		
		if(difficulty == 1) {
			if(hand[0].getPower() >= hand[1].getPower() && hand[0].getPower() >= hand[2].getPower())
				choice = 0;
			else if(hand[1].getPower() >= hand[0].getPower() && hand[1].getPower() >= hand[2].getPower())
				choice = 1;
			else
				choice = 2;
		}
		else
			choice = new Random().nextInt(2);
		
		setCardPosition(choice);
		
		return choice;
	}
	
	// play card from hand
	public Card playCard(int cardPosition) {
		return hand[cardPosition];
	}
}