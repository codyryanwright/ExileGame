package tests;

import static org.junit.jupiter.api.Assertions.*;

import game.*;

import org.junit.jupiter.api.Test;

class UITest {

	UI view = new UI();
	@Test
	void testConstructor() {
		assertNotNull(view);
	}
	
	@Test
	void testHealth() {
		view.setHealth(50, 50);
		
		assertEquals(view.getUserHealth(), 50);
		assertEquals(view.getOpHealth(), 50);
	}
}
