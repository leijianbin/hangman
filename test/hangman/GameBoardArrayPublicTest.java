package hangman;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameBoardArrayPublicTest {

	private GameBoardArray board1;
	private GameBoardArray board2;
	
	@Before
	public void before() {
		board1 = new GameBoardArray("Zigzag");
		board2 = new GameBoardArray("Volume");
	}
	
	@Test (timeout = 1000)
	public void testIsCorrectGuess() {
		boolean result;
		result = board1.isCorrectGuess('c');
		assertFalse("'c' is not in Zigzag", result);
		result = board1.isCorrectGuess('z');
		assertTrue("'z' is in Zigzag", result);
		result = board1.isCorrectGuess('g');
		assertTrue("'g' is in Zigzag twice", result);
	}
	
	@Test (timeout = 1000)
	public void testIsPriorGuess() {
		boolean result;
		result = board1.isCorrectGuess('g');
		assertTrue("'g' is in Zigzag twice", result);
		result = board1.doMove('g');
		assertTrue("'g' is in Zigzag; move was played", result);
		result = board1.isPriorGuess('g');
		assertTrue("'g' is a prior guess", result);
	}
	
	@Test (timeout = 1000)
	public void testDoMove() {
		boolean result;
		
		result = board1.doMove('i');
		assertTrue("'i' is in the word so doMove should return true", result);
		result = board1.doMove('i');
		assertFalse("We already guessed 'i' so doMove should return false", result);
		result = board1.doMove('p');
		assertFalse("'p' is not in the word so doMove should return false", result);
		result = board1.doMove('g');
		assertTrue("'g' is in the word twice so doMove should return true", result);
		result = board1.doMove('g');
		assertFalse("We already guessed 'g' (in the word twice) so doMove should return false", result);
	}

	@Test (timeout = 1000)
	public void testInWinningState() {
		boolean result = false;
		
		char[] letters = {
			'Z', 'i', 'g', 'z', 'a'	
		};
		
		for (int i = 0; i < letters.length-1; i++) {
			result = board1.doMove(letters[i]);
			assertTrue("'" + letters[i] + "' is in the word", result);
			result = board1.inWinningState();
			assertFalse("[" + i + "] The board is not in the winning state", result);
		}
		
		result = board1.doMove(letters[letters.length-1]);
		assertTrue("'" + letters[letters.length-1] + "' is in the word", result);
		result = board1.inWinningState();
		assertTrue("The board is in the winning state", result);
	}

	@Test (timeout = 1000)
	public void testInLosingState() {
		boolean result = false;
		
		char[] letters = {
			'v', 'd', 'c', 'x', 'q', 
			'r', 'l', 'm', 'n', 'p'	
		};
		
		for (int i = 0; i < letters.length-1; i++) {
			result = board1.doMove(letters[i]);
			assertFalse("'" + letters[i] + "' is not in the word", result);
			result = board1.inLosingState();
			assertFalse("[" + i + "] The board is not in the losing state", result);
		}
		
		result = board1.doMove(letters[letters.length-1]);
		assertFalse("'" + letters[letters.length-1] + "' is not in the word", result);
		result = board1.inLosingState();
		assertTrue("The board is in the losing state", result);
	}

	@Test (timeout = 1000)
	public void testCurrentHungState() {
		board1.doMove('Z');
		assertTrue("Is in state 0", board1.currentHungState() == 0);
		board1.doMove('i');		
		assertTrue("Is in state 0", board1.currentHungState() == 0);
		board1.doMove('g');
		assertTrue("Is in state 0", board1.currentHungState() == 0);
		board1.doMove('z');		
		assertTrue("Is in state 0", board1.currentHungState() == 0);
		board1.doMove('a');
		assertTrue("Is in state 0", board1.currentHungState() == 0);
		
		assertTrue("Is in state 0", board2.currentHungState() == 0);
		board2.doMove('Z');
		assertTrue("Is in state 1", board2.currentHungState() == 1);
		board2.doMove('i');		
		assertTrue("Is in state 2", board2.currentHungState() == 2);
		board2.doMove('g');
		assertTrue("Is in state 3", board2.currentHungState() == 3);
		board2.doMove('z');		
		assertTrue("Is in state 4", board2.currentHungState() == 4);
		board2.doMove('a');
		assertTrue("Is in state 5", board2.currentHungState() == 5);
		
		board2.doMove('V');
		assertTrue("Is in state 5", board2.currentHungState() == 5);
		board2.doMove('x');
		assertTrue("Is in state 6", board2.currentHungState() == 6);
		board2.doMove('x');
		assertTrue("Is in state 6", board2.currentHungState() == 6);
		board2.doMove('x');
		assertTrue("Is in state 6", board2.currentHungState() == 6);
		board2.doMove('x');
		assertTrue("Is in state 6", board2.currentHungState() == 6);		
		board2.doMove('x');
		assertTrue("Is in state 6", board2.currentHungState() == 6);
		
		// Test after state 6 -> should still be in state 6:
		board2.doMove('x');
		assertTrue("Should still be in state 6", board2.currentHungState() == 6);
	}

	@Test (timeout = 1000)
	public void testToString() {
		board1.doMove('Z');
		assertTrue("State should be \"Z _ _ _ _ _\"", board1.toString().equals("Z _ _ _ _ _"));
		board1.doMove('i');
		assertTrue("State should be \"Z i _ _ _ _\"", board1.toString().equals("Z i _ _ _ _"));		
		board1.doMove('g');
		assertTrue("State should be \"Z i g _ _ g\"", board1.toString().equals("Z i g _ _ g"));
		board1.doMove('z');
		assertTrue("State should be \"Z i g z _ g\"", board1.toString().equals("Z i g z _ g"));
		board1.doMove('a');
		assertTrue("State should be \"Z i g z a g\"", board1.toString().equals("Z i g z a g"));
				
		board2.doMove('Z');
		assertTrue("State should be \"_ _ _ _ _ _\"", board2.toString().equals("_ _ _ _ _ _"));
		board2.doMove('l');
		assertTrue("State should be \"_ _ l _ _ _\"", board2.toString().equals("_ _ l _ _ _"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		board2.doMove('e');
		assertTrue("State should be \"_ _ l _ _ e\"", board2.toString().equals("_ _ l _ _ e"));
		// Should always remain as the same string even after the number of guesses exceeded!
	}
	
	@Test (timeout = 1000)
	public void testPreviousGuessString() {		
		char[] letters = {
			'v', 'd', 'c', 'x', 'q', 
			'r', 'l', 'm', 'n', 'p'	
		};
		
		for (int i = 0; i < letters.length-1; i++) {
			board1.doMove(letters[i]);
		}
	
		assertTrue("Previous guess string should be \"[v, d, c, x, q, r, l, m, n]\"",
				   board1.previousGuessString().equals("[v, d, c, x, q, r, l, m, n]"));
	}
	
}
