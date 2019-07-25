package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import game.Deck;

class DeckTest {

	@Test
	void testDeck() {
		Deck testDeck = new Deck(1);
		assertEquals(20, testDeck.getDeck().size());
	}

}
