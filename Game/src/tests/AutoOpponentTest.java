package tests;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import game.AutoOpponent;
import game.Deck;

class AutoOpponentTest {
	
	AutoOpponent ao1 = new AutoOpponent();
	Deck d1 = new Deck(1);
	
	@Test
	void choiceTest1() {
		ao1.setDifficulty(1);
		ao1.setDeck(d1);
		ao1.loadHand();
		ao1.choice();
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
	void choiceTest2() {
		ao1.setDifficulty(2);
		ao1.setDeck(d1);
		ao1.loadHand();
		ao1.choice();
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