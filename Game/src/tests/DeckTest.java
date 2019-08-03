package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import game.Deck;

class DeckTest {

	@Test
	void testDeck0() {
		Deck testDeck = new Deck(0);
		assertEquals(20, testDeck.getDeck().size());
	}
	
	@Test
	void testDeck1() {
		Deck testDeck = new Deck(1);
		assertEquals(20, testDeck.getDeck().size());
	}

	@Test
	void testDeck2() {
		Deck testDeck = new Deck(2);
		assertEquals(20, testDeck.getDeck().size());
	}
}
