
import java.util.Scanner;

/**
 * 
 * This class uses recursion to check if an input is a palindrome: spelled the same backwards and forwards
 * Student Name: Jo Suh
 *
 */
public class Palindrome {

	/**
	 * @param args
	 */
	public boolean isPalindrome (String input) {
		// if nothing or just one character, return true
		if( input.length() <= 1 ) {
			// If there is only one character left, it will get caught here and return true
            return true; 
		}
        
		// compare the first and last characters
		if (input.charAt(0) == input.charAt(input.length()-1) ){
			// if same, use recursion to its substring until condition fails or the whole string is checked
			// Assuming correct input, this will call the function until there is one character left
			// -> will get caught in the if statement above
			return isPalindrome( input.substring(1,(input.length()-1)) );
		}
		
        //if reaches here, means the characters don't match
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);
		Palindrome trial = new Palindrome();
		System.out.println("Enter a string to check if it is a palindrome:");
		if ( trial.isPalindrome(userInput.nextLine()) ) {
			System.out.println( "Is a palindrome" );
		}else {
			System.out.println( "Not a palindrome" );
		}
		userInput.close();
		
	}
}
