package hangman;

import java.util.ArrayList;


/**
 * The Array implementation of the GameBoard interface.
 */
public class GameBoardArray implements GameBoard, HangManConstants {
	/** The number of characters (lower/upper). */
	private static int ALPHABET_COUNT = 26*2;
	
	/** hung state */
	private int		state;
	
	private char[] guessWord;
	ArrayList<Character> previousGuess = new ArrayList<Character>();
	private int numberOfGuess;
	private int numberofGuessedChar;
	
	/**
	 * Creates a new GameBoardArray object.
	 * 
	 *  guessWord the word to guess
	 */
	public GameBoardArray(String guessWord) {
		// TODO (1)
		state    = STARTING_STATE;
		this.guessWord = guessWord.toCharArray(); 
		this.numberOfGuess = 0;
		this.numberofGuessedChar = 0;
	}
		
	public boolean isPriorGuess(char guess) {
		// TODO (2)
		boolean contains = false;
		for (char c : previousGuess) {
		    if (c == guess) {
		        contains = true;
		        break;
		    }
		}
		return contains;
	}
	
	public int numberOfGuesses() {
		// TODO (3)
		return numberOfGuess;
	}
	
	public boolean isCorrectGuess(char guess) {
		// TODO (4)
		if(!isPriorGuess(guess))
		{	
			int count = 0; 
			for (char c : this.guessWord) {
			    if (c == guess) {
			    	count ++; 
			    }
			}
			if(count == 0)
			{
				return false;
			}
			else
			{
				this.numberofGuessedChar += count;
				return true;
			}
			
		}
		return false;
	}
	
	public boolean doMove(char guess) {
		// TODO (5)
		if(isPriorGuess(guess))
		{
			return false;
		}
		else
		{
			boolean correct = false;
			if(isCorrectGuess(guess))
			{
				correct = true;
			}
			else
			{
				this.state ++;
			}
			Character cc= new Character(guess);
			previousGuess.add(cc);
			numberOfGuess ++;
			return correct;
		}
		
	}

	public boolean inWinningState() {
		// TODO (6)
		if(this.state <= 10 && this.numberofGuessedChar == this.guessWord.length)
			return true;
		return false;
	}

	public boolean inLosingState() {
		return state == NUMBER_OF_STATES;
	}
	
	public String toString() {
		String s = "";
		// TODO (7)	 
		for (char c : this.guessWord) {
		    if (isPriorGuess(c)) {
		    	s += c;
		    }
		    else
		    	s += '_';
		    s += ' ';
		}
		s = s.substring(0, s.length() - 1);
		return s;
	}

	public String previousGuessString() {
		String s = "[";
		
		// TODO (8)
		for (char c : this.previousGuess) {
		    	s += c;
		    	s += ", ";
		}
		if(s.length() > 2)
		{
			s = s.substring(0, s.length() - 2);
		}
		s += "]";
		return s;
	}
	
	public int currentHungState() {
		return state;
	}

}
