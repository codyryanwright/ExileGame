package tests;

import static org.junit.Assert.fail;
import org.junit.jupiter.api.Test;
import game.AutoOpponent;
import game.Deck;

class AutoOpponentTest {
	
	AutoOpponent ao1 = new AutoOpponent();
	Deck d1 = new Deck(1);
	
	@Test
	void choiceTestHard() {
		ao1.setDifficulty(1);
		ao1.setDeck(d1);
		ao1.loadHand();
		ao1.makeChoice();
		if(ao1.getCardPosition() == 0 || ao1.getCardPosition() == 1|| 
				ao1.getCardPosition() == 2)	
		{
			return;
		}
		else {
			fail();
		}
	}
	
	@Test
	void choiceTestEasy() {
		ao1.setDifficulty(0);
		ao1.setDeck(d1);
		ao1.loadHand();
		ao1.makeChoice();
		if(ao1.getCardPosition() == 0 || ao1.getCardPosition() == 1|| 
				ao1.getCardPosition() == 2)	
		{
			return;
		}
		else {
			System.out.println(ao1.getCardPosition());
			fail();
		}
	}

}
