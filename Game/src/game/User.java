package game;

public class User extends Participant {
	public Card playCard() {
		Card chosenCard = hand[cardPosition];
		return chosenCard;
	}
}