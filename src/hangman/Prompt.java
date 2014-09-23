package hangman;

/**
 * A Prompt is used to interact with the user of the hangman game.
 */
public interface Prompt {
	/**
	 * Returns the next letter from the input. 
	 */
	public char nextLetter();
	
	/**
	 * Displays the String s to the user. 
	 */
	public void display(String s);
}
