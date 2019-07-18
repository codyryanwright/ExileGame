package game;

import java.util.ArrayList;

public abstract class Participant {
	private String name;
	private Deck deck;
	private ArrayList <Card> hand;
	private int health;
	private int cardPosition; // seems out of place
	
	public Participant() {
		// TODO build participant
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
	public int getCardPosition() {
		return cardPosition;
	}
	public void setCardPosition(int cardPosition) {
		this.cardPosition = cardPosition;
	}
}
