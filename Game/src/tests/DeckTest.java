package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import game.Deck;

class DeckTest {

	@Test
	void testDeck() {
		try {
			Deck testDeck = new Deck(1);
			assertEquals(20, testDeck.getDeck().size());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
