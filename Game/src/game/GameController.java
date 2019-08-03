package game;

import java.util.Random;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
	static final int DECK_TOTAL = 3;
	private Deck[] collection = new Deck[DECK_TOTAL];
	private int deckChoice;
	private UI view;
	private Participant user, opponent;	
	private float playerPower = 0, opponentPower = 0;
	
	public GameController(UI view) {
		deckChoice = -1;
		this.view = view;
		user = new User();
		opponent = new AutoOpponent();
		buildCollection();
	
		view.addNewGameListener(new NewGameListener());
		view.addDeckListener(new DeckListener());
		view.addCardListener(new CardListener());
		view.addPlayCardListener(new PlayCardListener());
		view.addContinueListener(new ContinueListener());
		view.addDifficultyListener(new DifficultyListener());
	}

	class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = -1;
			user.reset();
			opponent.reset();
			view.setHealth(100, 100);
			view.resetText();
			view.show("choosePanel");
			view.getTxtpnGame().setFont(new Font("SimSun", Font.PLAIN, 9));
		}
	}
	
	class DeckListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == view.getBtnDeck0()) 
				deckChoice = 0;
			else if(e.getSource() == view.getBtnDeck1())
				deckChoice = 1;
			else if(e.getSource() == view.getBtnDeck2())
				deckChoice = 0;
			
			user.setDeck(collection[deckChoice]);
			user.loadHand();
			updateHand();
			
			//sets the opponents deck randomly
			int choice = new Random().nextInt(2);
			opponent.setDeck(collection[choice]);
			opponent.loadHand();
			view.show("playPanel");
		}
	}
	
	class CardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == view.getBtnCard0())
				user.setCardPosition(0);
			else if(e.getSource() == view.getBtnCard1())
				user.setCardPosition(1);
			else if(e.getSource() == view.getBtnCard2())
				user.setCardPosition(2);
		}
	}
	
	/**
	 * Sets the game play in motion by calling Participants' playCard() and passing to combatCards()
	 */
	class PlayCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (user.getCardPosition() == -1) {
				view.appendText("Please choose a Card!");
				view.refreshText();
			}
			else
			{
				//gets a card from the user and the opponent
				Card userCard = ((User) user).playCard();
				Card opCard = ((AutoOpponent) opponent).playCard();
				combatCards(userCard, opCard);
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
			if (view.getDifficulty() == "Hard")
				((AutoOpponent) opponent).setDifficulty(0);
			else ((AutoOpponent) opponent).setDifficulty(1);
		}
	}
	
	public void updateHand() {
		for(int i = 0; i < 3; i++)
			view.setImageIcon(i, user.hand[i].getImgIconCard());
	}
	
	public void buildCollection() {
		try {
			for(int i = 0; i < DECK_TOTAL; i++)
				collection[i] = new Deck(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setDeckChoice(int deckChoice) {
		this.deckChoice = deckChoice;
	}
	
	public int getDeckChoice() {
		return deckChoice;
	}
	
	public Deck getDeck() {
		return collection[deckChoice];
	}
	
	//TODO disconnecting compare and logic
	/**
	 * Handles the output for a given card match-up both pre- and post-call to compareCards, then resets game for next play.
	 * 
	 * @param userCard  the card that has been chosen by the user
	 * @param opponentCard  the card that has been chosen by the opponent
	 */
	public void combatCards(Card userCard, Card opponentCard) {
		playerPower = 0;
		opponentPower = 0;
		// Print cards played
		view.appendText("You played "+userCard.getName() +
				"\nYour Opponent played "+opponentCard.getName());
		view.refreshText();
		
		int battleResult = compareCards(userCard, opponentCard);

		// Print match results
		view.appendText("Match Results: \nYour "+userCard.getName()+ 
				" power: "+playerPower+"\nYour Opponent's "+opponentCard.getName()+
				" power: "+opponentPower);
		
		// Print outcome
		if (battleResult == 0) {
			user.decreaseHealth();
			view.appendText("\nYou've lost this round!\n");
			view.refreshText();
		}
		else if (battleResult == 1) {
			opponent.decreaseHealth();
			view.appendText("\nYou've won this round!\n");
			view.refreshText();
		}
		else {
			view.appendText("\nIt's a draw!\n");
			view.refreshText();
		}
		
		// Check health status
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
		else // Update hand for play
		{
			view.setHealth(user.getHealth(), opponent.getHealth());
			user.draw();
			opponent.draw();
			updateHand();
			user.setCardPosition(-1); // resets card position
		}
	}
	
	/**
	 * Makes the comparison of the card matchups and modifies the card power accordingly to determine a winner.
	 * 
	 * @param userCard  the card that has been chosen by the user
	 * @param opponentCard  the card that has been chosen by the opponent
	 * @return returns a switch case for win/lose/draw for the round
	 */
	public int compareCards(Card userCard, Card opponentCard) {
		playerPower = userCard.getPower();
		opponentPower = opponentCard.getPower();

		// Make type comparison and modify participants power
		if (userCard.getType().equals(opponentCard.getType())); // do nothing
		else if ((userCard.getType().equals("red") && opponentCard.getType().equals("green"))
				|| (userCard.getType().equals("green") && opponentCard.getType().equals("blue"))
				|| (userCard.getType().equals("blue") && opponentCard.getType().equals("red")))
			playerPower += userCard.getPower();
		else
			opponentPower += opponentCard.getPower();

		// Make archetype comparison and modify participants power
		if (userCard.getArchetype().equals(opponentCard.getArchetype())); // do nothing
		else if ((userCard.getArchetype().equals("dragon") && opponentCard.getArchetype().equals("wizard"))
				|| (userCard.getArchetype().equals("wizard") && opponentCard.getArchetype().equals("knight"))
				|| (userCard.getArchetype().equals("knight") && opponentCard.getArchetype().equals("dragon")))
			playerPower += 1.5 * userCard.getPower();
		else
			opponentPower += 1.5 * opponentCard.getPower();
		
		if (opponentPower > playerPower) 
			return 0;
		else if (opponentPower < playerPower) 
			return 1;
		else 
			return 2;
	}
}
