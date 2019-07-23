package tests;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;

import game.Card;

public class CardTest {

	@Test
	public void testCard() {
		Card c1 = new Card("red", "dragon", "rare", 4.0f);
		try {
			assertEquals("red", c1.getType());
			assertEquals("dragon", c1.getArchetype());
			assertEquals("rare", c1.getRarity());
			assertEquals(4.0f, (float) c1.getPower());
		} catch(ArithmeticException e)
		{
			e.printStackTrace();
		}
	}

}
