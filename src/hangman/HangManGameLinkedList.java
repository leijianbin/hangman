package hangman;

/**
 * A class to play the hangman game.
 */
public class HangManGameLinkedList {

	/** The hangman representation. */
	private HangMan   hangman;
	
	/** The game board state. */
	private GameBoard board;
	
	/** The prompt to the user. */
	private Prompt    prompt;
	
	/** The word to guess. */
	private String	  word;
	
	/**
	 * Creates a new HangManGame object.
	 */
	public HangManGameLinkedList(HangMan hangman, GameBoard board, String word) {
		this.hangman = hangman;
		this.board   = board;
		this.prompt  = new ConsolePrompt();
		this.word    = word;
	}
	
	/**
	 * Plays the hangman game.
	 */
	public void play() {
		boolean endOfGame = false;
		char guess;
		prompt.display(board.toString());		
		do {
			guess = prompt.nextLetter();
			if (guess == 0) {
				endOfGame = true;
			}
			
			if (board.isPriorGuess(guess)) {
				prompt.display("You guessed " + guess + " already!");				
				prompt.display("guess: " + board);
			}
			
			if (!board.isPriorGuess(guess)) {
				boolean success = board.doMove(guess);
				
				if (!success) {
					prompt.display("Bad guess!");
				}
				else {
					prompt.display("Good guess!");
				}
			}
			
			prompt.display("");
			hangman.display(board.currentHungState());
			prompt.display(board.previousGuessString());
			
			if (board.inWinningState()) {
				prompt.display("You won!");
				prompt.display("The word was " + word + "!");
				prompt.display("Number of guesses: " + board.numberOfGuesses());
				endOfGame = true;
			}
			else if (board.inLosingState()) {
				prompt.display("The word was " + word + "!");
				prompt.display("Number of guesses: " + board.numberOfGuesses());
				prompt.display("You lose!");
				endOfGame = true;
			}	
			else {
				prompt.display("");
				prompt.display(board.toString());
			}

		} while (!endOfGame);
	}
	
	public static void main(String[] args) {
		HangMan     hangman = new HangManConsole(System.out);
		Words		words	= new Words("grade2-words.txt");
		String      word    = words.pick();
		GameBoard   board   = new GameBoardLinkedList(word);
		HangManGameLinkedList game    = new HangManGameLinkedList(hangman, board, word);
		game.play();
	}

}
