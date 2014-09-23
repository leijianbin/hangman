package hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A ConsolePrompt is used to interact with the user from a console. 
 */
public class ConsolePrompt implements Prompt {

	public char nextLetter() {
		InputStreamReader s = new InputStreamReader(System.in);
		BufferedReader    r = new BufferedReader(s);		
		
		try {
			System.out.print("pick a letter: ");
			String response = r.readLine();
			if (response != null) {
				char[] a = response.toCharArray();
				if (a.length > 0)					
					return response.toCharArray()[0];
				else
					return 0;
			}
			else {
				return 0;
			}
		}
		catch (IOException e) {
			return 0;
		}
	}
	
	public void display(String s) {
		System.out.println(s);
	}

}
