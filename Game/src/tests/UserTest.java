package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import game.User;
import game.Deck;

class UserTest {

	User p1 = new User();
	Deck d1 = new Deck(1);
	
	@Test
	void decreaseHealthTest() {
		p1.decreaseHealth();
		assertEquals(p1.getHealth(), 80);
	}

	
	@Test
	void loadHandTest() {
		p1.setDeck(d1);
		p1.loadHand();
		assertNotNull(p1.getHand());
	}

}
