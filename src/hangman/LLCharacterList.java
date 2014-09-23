package hangman;

public class LLCharacterList {

	public LLCharacterNode first;
	public LLCharacterNode last;
	public int lengthOfWord; 
	public int countOfCorrectChar;
	
	
	// Returns true if no links on list yet
	public boolean isEmpty()
	{
		return first == null;
	}
	
	public boolean isWin()
	{
		return lengthOfWord == countOfCorrectChar;
	}
	
	public LLCharacterList(String guessWord)
	{
		first = null;
		last = null;
		lengthOfWord = guessWord.length();
		countOfCorrectChar = 0;
		int index = 0;
		char charGuessWord;
		while (index < guessWord.length()) {
			charGuessWord = guessWord.charAt(index);
			insertLast(charGuessWord);
			index ++;
		}
	};
	
	// Method to insert at front of list/onto "first"
	public void insertFirst(char charGuessWord)
	{
		LLCharacterNode newLink = new LLCharacterNode(charGuessWord);

		if (isEmpty())// { // If isEmpty() is true, or list is empty
			last = newLink;
		// newLink.next = first; //???Where does 2nd "if" bracket go?
		newLink.setLink(first);
		first = newLink;
	};
	
	// Method to insert at end of list/onto "last"
	public void insertLast(char charGuessWord) {
		LLCharacterNode newLink = new LLCharacterNode(charGuessWord);

		if (isEmpty())// { // If is isEmpty() is true, or if list is empty
		{
			first = newLink;
			last = newLink;
		}
		else
		{
			last.setLink(newLink);
			last = newLink;
		}

	}
	
	public boolean isContainsChar(char f)
	{
		LLCharacterNode currentLink = first; 
		boolean correct = false;
		while(currentLink != null)
		{
			if(f == currentLink.getInfo())
			{
				correct = true;
				this.countOfCorrectChar ++;
			}
			currentLink = currentLink.getLink();
		}
		return correct;
	}
}
