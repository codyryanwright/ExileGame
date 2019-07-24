package game;

import java.util.ArrayList;

public abstract class Participant {
	protected String name;
	protected Deck deck;
	protected ArrayList <Card> hand;
	protected int health;
	protected int cardPosition; // seems out of place
	
	public Participant() {	
		this.health = 100;
		// Controller builds Participant given player choices from UI
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public ArrayList <Card> getHand() {
		return hand;
	}
	public void setHand(ArrayList <Card> hand) {
		this.hand = hand;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void draw() {
		hand.add(cardPosition, deck.getCard());
	}
	public void decreaseHealth() {
		this.health -= 20;
	}
	public int getCardPosition() {
		return cardPosition;
	}
	public void setCardPosition(int cardPosition) {
		this.cardPosition = cardPosition;
	}

	public void pushToDiscard(Card userCard) {
		deck.pushToDiscard(userCard);
	}
}
