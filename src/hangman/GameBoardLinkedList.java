package hangman;

public class GameBoardLinkedList implements GameBoard, HangManConstants {

	/** hung state */
	private int state;

	private int numGuesses;

	private LLCharacterList storedGuess;
//	private LLCharacterList guessProgress;	
	private LLCharacterList previousGuess;
	

	// Constructs a new linked list with no links yet

	// ************************************CREATE LINKED LIST TO STORE GUESS
	// WORD*******************************//

	public GameBoardLinkedList(String guessWord) {
		storedGuess = new LLCharacterList(guessWord);
		previousGuess = new LLCharacterList("");
		numGuesses = 0;
	}

	// Returns true if the guess is a prior guess.
	public boolean isPriorGuess(char guess) {
		return previousGuess.isContainsChar(guess);
	}

	// Returns the number of correct and incorrect guesses made.
	public int numberOfGuesses() {
		return numGuesses;
	}

	public boolean isCorrectGuess(char guess) {
		
		if(!isPriorGuess(guess))
		{
			if(storedGuess.isContainsChar(guess))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}

	public boolean doMove(char guess) {
		
		if(!isPriorGuess(guess))
		{
			numGuesses ++;
			if(isCorrectGuess(guess))
			{
				previousGuess.insertLast(guess);
				return true;
			}
			else
			{
				previousGuess.insertLast(guess);
				state ++;
				return false;
			}
		}
		return false;
	}

	public boolean inWinningState() {
		
		if(this.state <= 10 && storedGuess.isWin())
		{
			return true;
		}
		
		return false;

	}

	public boolean inLosingState() {
		return state == NUMBER_OF_STATES;
	}

	public int currentHungState() {
		return state;
	}

	// Returns string representation of board

	public String toString() {
		String boardString = "";
		
		LLCharacterNode currentLink = this.storedGuess.first; 
		while(currentLink != null)
		{
			if(this.previousGuess.isContainsChar(currentLink.getInfo()))
			{
				boardString += currentLink.getInfo();
			}
			else
			{
				boardString += "_";
			}
			boardString += " ";
			currentLink = currentLink.getLink();
		}
		boardString = boardString.substring(0, boardString.length() - 1); //remove the last empty space
		return boardString;
	}

	// Returns string representation of prior guesses
	public String previousGuessString() {
		String guessesString = "[";

		LLCharacterNode currentLink = this.previousGuess.first; 
		while(currentLink != null)
		{
			guessesString += currentLink.getInfo();
			guessesString += ", ";
			currentLink = currentLink.getLink();
		}
		
		if(guessesString.length() > 2)
		{
			guessesString = guessesString.substring(0, guessesString.length() - 2); 
		}
		guessesString += "]";
		return guessesString;
	}

}
