package game;

public class User extends Participant {
	public Card playCard() {
		return hand[cardPosition];
	}
}