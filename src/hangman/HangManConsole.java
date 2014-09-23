package hangman;

import java.io.PrintStream;

/**
 * A class to represent the hangman sideways "image". 
 */
public class HangManConsole implements HangMan {

	private PrintStream out;
	
	private String[] states = {
		// State 1:
		"|--------|\n",
		
		// State 2:
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|--------|\n",
		
		// State 3:
		"-----    \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|--------|\n",
		
		// State 4:
		"-----|   \n" +
		"|    |   \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|--------|\n",
		
		// State 5:
		"-----|   \n" +
		"|    |   \n" +
		"|    O   \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|        \n" +
		"|--------|\n",
		
		// State 6:
		"-----|   \n" +
		"|    |   \n" +
		"|    O   \n" +
		"|    |   \n" +
		"|    |   \n" +
		"|        \n" +
		"|        \n" +
		"|--------|\n",
		
		// State 7:
		"-----|   \n" +
		"|    |   \n" +
		"|    O   \n" +
		"|    |   \n" +
		"|    |   \n" +
		"|   /    \n" +
		"|        \n" +
		"|--------|\n",
		
		// State 8:
		"-----|   \n" +
		"|    |   \n" +
		"|    O   \n" +
		"|    |   \n" +
		"|    |   \n" +
		"|   / \\ \n" +
		"|        \n" +
		"|--------|\n",
		
		// State 9:
		"-----|   \n" +
		"|    |   \n" +
		"|    O   \n" +
		"|   /|   \n" +
		"|    |   \n" +
		"|   / \\ \n" +
		"|        \n" +
		"|--------|\n",
		
		// State 10:
		"-----|   \n" +
		"|    |   \n" +
		"|    O   \n" +
		"|   /|\\  \n" +
		"|    |   \n" +
		"|   / \\ \n" +
		"|        \n" +
		"|--------|\n",		
	};
	
	/**
	 * Creates a new HangMan object.
	 */
	public HangManConsole(PrintStream p) {
		out = p;
	}
	
	/**
	 * Displays the state of the Hangman.
	 */
	public void display(int state) {
		if (state == 0) {
			out.print("");
		}		
		else if (state > NUMBER_OF_STATES) {
			out.print(states[9]);
		}
		else {
			out.print(states[state-1]);
		}
		
	}
	
}
