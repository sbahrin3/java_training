package my.game;

import java.util.Random;

public class NumberGuess {
	int secretNumber;
	int level;
	int limit;
	
	public NumberGuess() {
		level = 1;
	}

	public int getSecretNumber() {
		return secretNumber;
	}

	public void setSecretNumber(int secretNumber) {
		this.secretNumber = secretNumber;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void generateSecretNumber() {
		Random rand = new Random();
		limit = level*10;
		secretNumber = rand.nextInt(limit);
	}
	
	public int evaluateGuessNumber(int guessNumber) {
		if ( guessNumber > secretNumber ) return 1;
		if ( guessNumber < secretNumber ) return -1;
		else return 0;
	}
	

}
