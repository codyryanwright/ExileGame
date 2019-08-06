package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import game.GameController;
import game.Card;
import game.UI;

public class GameControllerTest {
	
	UI ui = new UI();
	GameController gc = new GameController(ui);
	
	@Test
	public void compareTest() {
		
		// Empty string param for artwork not necessary in test
		Card c1 = new Card("red", "dragon", "rare", 4.0f, "");
		Card c2 = new Card("blue", "dragon", "rare", 4.0f, "");
		Card c3 = new Card("blue", "knight", "rare", 4.0f, "");
		Card c4 = new Card("green", "knight", "rare", 4.0f, "");
		Card c5 = new Card("green", "wizard", "rare", 4.0f, "");
		
		//Same type, different archetype, opponent wins
		assertEquals(gc.compareCards(c2, c4), 0);
		
		//Different type, same archetype, user wins
		assertEquals(gc.compareCards(c2, c1), 1);
		
		//Same type, same archetype, draw
		assertEquals(gc.compareCards(c1, c1), 2);
		
		//Same type, different archetype, opponent wins
		assertEquals(gc.compareCards(c2, c3), 0);
		
		//Same type, different archetype, user wins
		assertEquals(gc.compareCards(c4, c2), 1);
		
		//Different type, same archetype, opponent wins
		assertEquals(gc.compareCards(c1, c2), 0);
		
		//Same type, different archetype, user wins
		assertEquals(gc.compareCards(c3, c2), 1);
		
		//user wins
		assertEquals(gc.compareCards(c1, c5), 1);
		
		//user wins
		assertEquals(gc.compareCards(c5, c3), 1);
		
		//opponent wins
		assertEquals(gc.compareCards(c5, c1), 0);
	}
}


