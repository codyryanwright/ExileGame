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
		
		Card c1 = new Card("red", "dragon", "rare", 4.0f, "");
		Card c2 = new Card("blue", "dragon", "rare", 4.0f, "");
		Card c3 = new Card("blue", "knight", "rare", 4.0f, "");
		//Same type, different archetype
		gc.compareCards(c2, c3);
		assertEquals(gc.user.getHealth(), 80f);
		assertEquals(gc.opponent.getHealth(), 100f);
		
		//Different type, same archetype
		gc.compareCards(c2, c1);
		assertEquals(gc.user.getHealth(), 80f);
		assertEquals(gc.opponent.getHealth(), 80f);
		
		//Same type, same archetype
		gc.compareCards(c1, c1);
		assertEquals(gc.user.getHealth(), 80f);
		assertEquals(gc.opponent.getHealth(), 80f);
	}
		
		
}


