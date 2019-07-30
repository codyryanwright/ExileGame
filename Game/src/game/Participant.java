package game;

public abstract class Participant {
	protected String name; // TODO where is this used?
	protected Deck deck;
	protected int HAND_SIZE = 3;
	protected Card[] hand = new Card[HAND_SIZE];
	protected int health;
	protected int cardPosition;
	
	public Participant() {	
		this.health = 100;
		cardPosition = -1;
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
	
	public Card[] getHand() {
		return hand;
	}
	
	public void setHand(Card[] hand) {
		this.hand = hand;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void draw() {
		hand[cardPosition] = deck.getCard();
	}
	
	public void loadHand() {
		for(int i = 0; i < HAND_SIZE; i++) {
			cardPosition = i;
			draw();
		}
		cardPosition = -1;
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

//	public void pushToDiscard(Card userCard) {
//		deck.pushToDiscard(userCard);
//	}
	
	public void reset() {
		this.health = 100;
		cardPosition = -1;
		if(deck != null) deck.reset(); // reset the current deck (move discard back into deck)
		deck = null;
		for(int i = 0; i < HAND_SIZE; i++) {
			hand[i] = null;
		}
	}
}
