package game;

public class AutoOpponent extends Participant {
	private int difficulty;

	public int choiceAI(int difficulty)
	{
		return 0;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
}