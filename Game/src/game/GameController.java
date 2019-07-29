package game;

import java.util.Random;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GameController {
	static final int DECK_TOTAL = 3;
	private Deck[] collection = new Deck[DECK_TOTAL];
	private int deckChoice;
	private UI view;
	public Participant user;
	public Participant opponent;	
	
	public GameController(UI view) {
		deckChoice = -1;
		this.view = view;
		user = new User();
		opponent = new AutoOpponent();
		
		try {
			buildCollection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		// TODO add action listeners to view
		view.addNewGameListener(new NewGameListener());
		view.addDeck0Listener(new Deck0Listener());
		view.addDeck1Listener(new Deck1Listener());
		view.addDeck2Listener(new Deck2Listener());
		view.addCard0Listener(new Card0Listener());
		view.addCard1Listener(new Card1Listener());
		view.addCard2Listener(new Card2Listener());
		view.addPlayCardListener(new PlayCardListener());
		view.addContinueListener(new ContinueListener());
		view.addDifficultyListener(new DifficultyListener());
	}

	class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.show("choosePanel");
			// TODO reset game variables
		}
	}
	
	// TODO a way to combine similar button listeners?
	class Deck0Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = 0;
			user.setDeck(collection[deckChoice]);
			user.loadHand();
			for(int i = 0; i < 3; i++)
				view.setImageIcon(i, user.hand[i].getImgIconCard());
			//sets the opponents deck randomly
			int choice = new Random().nextInt(2);
			opponent.setDeck(collection[choice]);
			view.show("playPanel");
		}
	}
	
	class Deck1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = 1;
			user.setDeck(collection[deckChoice]);
			user.loadHand();
			for(int i = 0; i < 3; i++)
				view.setImageIcon(i, user.hand[i].getImgIconCard());
			//sets the opponents deck randomly
			int choice = new Random().nextInt(2);
			opponent.setDeck(collection[choice]);
			view.show("playPanel");
		}
	}

	class Deck2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = 2;
			user.setDeck(collection[deckChoice]);
			user.loadHand();
			for(int i = 0; i < 3; i++)
				view.setImageIcon(i, user.hand[i].getImgIconCard());
			//sets the opponents deck randomly
			int choice = new Random().nextInt(2);
			opponent.setDeck(collection[choice]);
			view.show("playPanel");
		}
	}
	
	class Card0Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user.setCardPosition(0);
		}
	}
	
	class Card1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user.setCardPosition(1);
		}
	}
	
	class Card2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			user.setCardPosition(2);
		}
	}
	
	class PlayCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (user.getCardPosition() == -1)
				// card not chosen do nothing TODO add message to select?
				return;
			else
			{
				//gets a card from the user and the opponent
				Card userCard = ((User) user).playCard(); //TODO null pointer exception bug
				Card opCard = ((AutoOpponent) opponent).playCard(((AutoOpponent) opponent).choice());
				
				compareCards(userCard, opCard);
				
				//check for winner
				if(user.getHealth() == 0)
				{
					view.setEndMessage("YOU LOSE!");
					view.show("endPanel");
				}
				else if (opponent.getHealth() == 0)
				{
					view.setEndMessage("YOU WIN!");
					view.show("endPanel");
				}
				else
				{
					user.pushToDiscard(userCard);
					opponent.pushToDiscard(opCard);
					user.setCardPosition(-1); // resets card position
					
					// TODO set card art for new card
					// 		update score / health
					//		check if game over
				}

			}
		}
	}
	
	class ContinueListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(deckChoice == -1)
				view.show("choosePanel");
			else
				view.show("playPanel");
		}
	}
	
	class DifficultyListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			((AutoOpponent) opponent).setDifficulty((int) view.getDifficulty());			
		}
	}
	
	public void setDeckChoice(int deckChoice) {
		this.deckChoice = deckChoice;
	}
	
	public void buildCollection() throws IOException {
		for(int i = 0; i < DECK_TOTAL; i++)
			collection[i] = new Deck(i+1);
	}
	
	public int getDeckChoice() {
		return deckChoice;
	}
	
	public Deck getDeck() {
		return collection[deckChoice];
	}
	
	public void compareCards(Card userCard, Card opponentCard) {
		float playerPower = userCard.getPower(), opponentPower = opponentCard.getPower();
		// In the case that both types match
		// In the case that both archTypes, return
		if (userCard.getType().equals(opponentCard.getType()));
		//do nothing

		else if ((userCard.getType().equals("red") && opponentCard.getType().equals("green"))
				|| (userCard.getType().equals("green") && opponentCard.getType().equals("blue"))
				|| (userCard.getType().equals("blue") && opponentCard.getType().equals("red")))
			playerPower += userCard.getPower();

		else
			opponentPower += opponentCard.getPower();


		if (userCard.getArchetype().equals(opponentCard.getArchetype()));
		//do nothing

		else if ((userCard.getArchetype().equals("dragon") && opponentCard.getArchetype().equals("wizard"))
				|| (userCard.getArchetype().equals("wizard") && opponentCard.getArchetype().equals("knight"))
				|| (userCard.getArchetype().equals("knight") && opponentCard.getArchetype().equals("dragon")))
			playerPower += 1.5 * userCard.getPower();

		else
			opponentPower += 1.5 * userCard.getPower();

		if (opponentPower > playerPower)
			user.decreaseHealth();
		else if (opponentPower < playerPower)
			opponent.decreaseHealth();
		else
			return;
	}
}
