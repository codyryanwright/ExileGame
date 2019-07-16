#include <iostream>
#include <cstdlib>
using namespace std;

class Card {
	public:         ///DEFINITELY NEED TO MAKE PRIVATE JUST LAZY
		string color;
		string name;
		int power;
		string archetype;
		int rarity;
		//pictureType art;
		//borderType border;
	
		Card(string Ccolor, string Cname, string Carchetype, int Crarity, int Cpower) {
			color = Ccolor; name = Cname; archetype = Carchetype; rarity = Crarity; power = Cpower;
		}
		int getRarity() {
			return rarity;
		}
};

class Deck{
	private:
		string name;
		Card *card; // pointer to a hash table of cards
		
	public:
		Card getCard(); // returns top card of the deck, either that or we can just make a stack
	
};

class Participant {
	private:
		Deck deck;
		Card hand[3]; 
		int health;
	
	public:
		Card draw(); // returns the top card of the deck
		void drawToHand(int choice); // puts a card that was drawn into the hand
		void decreaseHealth();
};

class Opponent : public Participant { 
	private:
		Deck deck;
		Card hand[3];
		int health;
	
	public:
		Card draw(); 
		void drawToHand(int choice);
		void decreaseHealth();
};

class Player : public Participant {
	private:
		int health;
		Card hand[3];
		Deck deck; 
		
	public:
		Card draw(); 
		void drawToHand(int choice); 
		void decreaseHealth();
		 
};

void displayHand(Player player) {
	//shows the three card hand to the player
}

void Participant::decreaseHealth() {
	health--;
}

Card Participant::draw() {
	Card card = deck.top(); // get the card from the top of the deck, assuming that it is a stack
	deck.pop();
}

void Player::drawToHand(int choice) {
	hand[choice] = draw();
}

int getPlayerChoice(Player player) {
	int choice;
	cout << prompt;
	while(true)
	{
		if(cin >> choice)
		{
			if(choice <= 3 and number >= 1)break;
		}
		cin.clear();
		cin.ignore(1024, '\n');
		cout << "please enter valid number, between 1 and 3: ";
	}
	return choice;
}

void displayRoundWinner(Player player, Opponent opponent) {
	Card opponentCard = opponent.draw(); // get the card of the opponent
	int choice = getPlayerChoice(player);
	Card chosenCard; // stores the card chosen by the user
	switch(choice) {
		case 1: chosenCard = player.hand[0]; break;
		case 2: chosenCard = player.hand[1]; break;
		case 3: chosenCard = player.hand[2];	
	}
	
	//show the two chosen cards on the battlefield
	displayCard(opponentCard);
	displayCard(chosenCard);
	sleep(1000); // NOT ACTUAL c++ CODE, ITS FOR WHEN WE TRANSLATE IT INTO JAVA, IT IS MEANT TO GIVE THE USER TIME TO SEE THE CARDS
	
	// stores the power of the card of the player and the opponent
	float playerPower = chosenCard.power;
	float opponentPower = opponentCard.power; 
	
	
	//get the type matchup	
	if(chosenCard.color == opponentCard.color)
		//do nothing because they are the same color
	
	//if player 1 has a type matchup
	else if((chosenCard.color == "R" and opponentCard.color == "G") or (chosenCard.color == "B" and opponentCard.color == "R") or (chosenCard.color == "G" and opponentCard.color == "B"))
		playerPower *= 2;
	
	//if the opponent has a type matchup
	else 
		opponentPower *= 2;
	
	
	
	//get the archetype matchup
	if(chosenCard.archetype == opponentCard.archetype)
		//do nothing because they are the same archtype
	
	//if player 1 has a archetype matchup
	else if((chosenCard.archetype == "K" and opponentCard.archetype == "D") or (chosenCard.archetype == "W" and opponentCard.archetype == "K") or (chosenCard.archetype == "D" and opponentCard.archetype == "W"))
		playerPower *= 1.5;
		
	//if the opponent has a archetype matchup
	else 
		opponentPower *= 1.5;
		
		
	
	//get the result of the battle and decrements losing player's health
	if(opponentPower > playerPower)
		player.decreaseHealth();
	else if (opponentPower < playerPower)
		opponent.decreaseHealth();
	else 
		//do nothing, its a draw
	
	player.drawToHand(choice); // draws a card to replace the card that was played
}

//just some bullshit code to display a card, the one we will actually use will be different
void displayCard(Card card) {
	string line = "--------------------------------";
	cout << line << endl;
	cout << "|     " << card.name << "     ( " << card.color << " |" << endl;
	cout << line << endl;
	for(int i = 0; i < 10; i++)
		cout << "|                              |" << endl;
	switch(card.getRarity()) {
		case 1: cout << "|                             L|" << endl; break;
		case 2: cout << "|                             E|" << endl; break;
		case 3: cout << "|                             R|" << endl; break;
		case 4: cout << "|                             C|" << endl;
	}
	cout << line << endl;
	for(int i = 0; i < 10; i++)
		cout << "|                              |" << endl;
	cout << "|                          ____|" << endl;
	cout << "|                         | " << card.power << " |" << endl;
	cout << line << endl;
}


void playTurn(Card card1, Card card2, Card card3) {
	displayHand(player);
	getPlayerChoice();
	showOpponentCard();
	displayRoundWinner();
}


int main() 
{
	/*
	Card card("R", "JON FUCKING SNOW", "Knight", 1, 15);
	displayCard(card);
	*/
	
	while(true){
		playTurn();
		if(player.health == 0 or opponent.health == 0)
			break;
	}
	
	if(opponent.health == 0)
		displayWin();
	else
		displayLose();
	
}
