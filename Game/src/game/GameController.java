package game;

import java.util.Random;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
	private int deckChoice;
	private UI view;
	private Participant user, opponent;
	private float playerPower = 0, opponentPower = 0;
	private boolean hasMulliganed = false;

	public GameController(UI view) {
		deckChoice = -1;
		this.view = view;
		user = new User();
		opponent = new AutoOpponent();

		view.addNewGameListener(new NewGameListener());
		view.addDeckListener(new DeckListener());
		view.addCardListener(new CardListener());
		view.addMulliganListener(new MullListener());
		view.addPlayCardListener(new PlayCardListener());
		view.addContinueListener(new ContinueListener());
		view.addDifficultyListener(new DifficultyListener());
	}

	class NewGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deckChoice = -1;
			hasMulliganed = false;
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
			if (e.getSource() == view.getBtnDeck0()) // Blue
				deckChoice = 0;
			else if (e.getSource() == view.getBtnDeck1()) // Red
				deckChoice = 1;
			else if (e.getSource() == view.getBtnDeck2()) // Green
				deckChoice = 0;

			user.setDeck(new Deck(deckChoice));
			user.loadHand();
			updateHand();
			view.setChosenBorder(user.cardPosition);

			// sets the opponents deck randomly
			int choice = new Random().nextInt(2);
			opponent.setDeck(new Deck(choice));
			opponent.loadHand();
			view.show("playPanel");
		}
	}

	class CardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == view.getBtnCard0())
				user.setCardPosition(0);
			else if (e.getSource() == view.getBtnCard1())
				user.setCardPosition(1);
			else if (e.getSource() == view.getBtnCard2())
				user.setCardPosition(2);
			view.setChosenBorder(user.getCardPosition());
		}
	}

	class MullListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(!hasMulliganed) {
				user.mulligan();
				
				if(checkWinner() == false) {
					user.setCardPosition(-1); // resets card position
					view.setChosenBorder(user.cardPosition);
					view.setHealth(user.getHealth(), opponent.getHealth());
					updateHand();
				}
				hasMulliganed = true;
			}
			
			else {
				view.appendText("You have already used your mulligan");
				view.refreshText();
			}


		}
	}

	/**
	 * Sets the game play in motion by calling Participants' playCard() and passing
	 * to combatCards()
	 */
	class PlayCardListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (user.getCardPosition() == -1) {
				view.appendText("Please choose a Card!");
				view.refreshText();
			} else {
				// gets a card from the user and the opponent
				Card userCard = ((User) user).playCard();
				Card opCard = ((AutoOpponent) opponent).playCard();
				combatCards(userCard, opCard);
				
				if(checkWinner() == false) {
					view.setHealth(user.getHealth(), opponent.getHealth());
					user.draw();
					opponent.draw();
					updateHand();
					user.setCardPosition(-1); // resets card position
					view.setChosenBorder(user.cardPosition);
				}
			}
		}
	}

	class ContinueListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (deckChoice == -1)
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
			else
				((AutoOpponent) opponent).setDifficulty(1);
		}
	}

	public void updateHand() {
		for (int i = 0; i < 3; i++)
			view.setImageIcon(i, user.hand[i].getImgIconCard());			
	}

	public void setDeckChoice(int deckChoice) {
		this.deckChoice = deckChoice;
	}

	public int getDeckChoice() {
		return deckChoice;
	}

	/**
	 * Handles the output for a given card match-up both pre- and post-call to compareCards
	 * 
	 * @param userCard     the card that has been chosen by the user
	 * @param opponentCard the card that has been chosen by the opponent
	 */
	public void combatCards(Card userCard, Card opponentCard) {
		playerPower = 0;
		opponentPower = 0;
		
		// Print cards played
		view.appendText("You played " + userCard.getName() + "\nYour Opponent played " + opponentCard.getName());
		view.refreshText();

		int battleResult = compareCards(userCard, opponentCard);

		// Print match results
		view.appendText("Match Results: \nYour " + userCard.getName() + " power: " + playerPower + "\nYour Opponent's "
				+ opponentCard.getName() + " power: " + opponentPower);

		// Print outcome
		if (battleResult == 0) {
			user.decreaseHealth();
			view.appendText("\nYou've lost this round!\n");
			view.refreshText();
		} else if (battleResult == 1) {
			opponent.decreaseHealth();
			view.appendText("\nYou've won this round!\n");
			view.refreshText();
		} else {
			view.appendText("\nIt's a draw!\n");
			view.refreshText();
		}
	}

	/**
	 * Makes the comparison of the card matchups and modifies the card power
	 * accordingly to determine a winner.
	 * 
	 * @param userCard     the card that has been chosen by the user
	 * @param opponentCard the card that has been chosen by the opponent
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

	public Boolean checkWinner() {
		Boolean win = false;
		
		// check for winner
		if (user.getHealth() <= 0) {
			view.setEndMessage("YOU LOSE!");
			view.show("endPanel");
			win = true;
		} else if (opponent.getHealth() <= 0) {
			view.setEndMessage("YOU WIN!");
			view.show("endPanel");
			win = true;
		}
		
		return win;
	}
}
