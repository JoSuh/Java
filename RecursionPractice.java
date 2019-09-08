/****************************************************************************
*
* Created by: Heejo Suh
* Created on: May 2018
* Created for: learning
* 
* This program performs a number of functions using recursion
****************************************************************************/

import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

//--------------------------------------------
public class RecursionPractice {
	//perform functions using recursion

    String[] buttons ={"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};  
	

	//--------------------------
    static int getInput() { 
		//gets input from user 

		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.println("Input a number!");
			try {
				//check if response is an integer
				String input = scanner.nextLine();
				int inputNumber = Integer.parseInt(input);
				
				//check if input is not less than 1
				if (inputNumber >=1) {
					return inputNumber;
				} else {
					System.out.println("Error: Invalid input!");
					return -1;
				}
			}catch (IllegalArgumentException x) { 
				System.out.println("Insert an integer!");
			}
		}
		
	}
	   //--------------------------
    static String switchToLetters (char number) {
		 //returns corresponding String of characters with input number
		 String result = "";
	      switch (number) {
	         case '2': result = "ABC";
	                  break;
	         case '3': result = "DEF";
	                  break;
	         case '4': result = "GHI";
	              break;
	         case '5': result = "JKL";
	                  break;         
	         case '6': result = "MNO";
	                  break;         
	         case '7': result = "PQRS";
	                  break;
	         case '8': result = "TUV";
	                  break;         
	         case '9': result = "WXYZ";
	                  break;         
	      }
	      return result;
	   }
	   //--------------------------
    static void listMnemonics(String inputNum, String output) {   
			// mnemonic(numbers that spell out some word)
			// generate all possible letter combinations that correspond to a given number, represented as a string of digits.
			// also finding words hat are not actually "words"
			
			String numToString = switchToLetters(inputNum.charAt(0));
			
			for (int currentNumberCharacters = 0; 
					currentNumberCharacters < numToString.length() ; 
					currentNumberCharacters++) {
				//For each alphabet the current number can be converted into
				char newLetter = numToString.charAt(currentNumberCharacters);
				if (inputNum.length() > 1) {
					//if not last number
					listMnemonics(inputNum.substring(1), output+newLetter);
				} else {
					//print mnemonic
					System.out.print( output + newLetter + "   ");
				}
			}
		}
			
		
		
		
		//--------------------------
	static int[] numberPattern(int[] inputNum, int numToInsert, int numPos) {   
			// The program asks for a single integer from the user (if value is less than 1, it stops)
			// The program then prints a pattern of integers: 
			//		a space before each integer, 
			//		a newline after each integer greater than 5
			
			
			
			// This produces a numeric pattern symmetric about n where each half before and after n is also symmetric around n-1. 
			// The newline after printing any integer larger than 5 provides a nice line length and 
			// also makes the pattern of the bigger numbers along the right edge reading down. 
			
			//Your program should work for any positive integer, but because it prints 2n-1 numbers (plus spaces) for input n, do not test it with anything bigger than 15. 

			//2n-1 numbers (plus spaces) for input n
	   		inputNum[numPos] = numToInsert; //since arrays count from 0
			
	   		
		   	 if (inputNum[numPos] > 1) {
		   		 //if number is greater than 1
		   		 int nextIndex = (int) Math.pow(2, numToInsert-1);
		   		inputNum = numberPattern(inputNum, numToInsert-1, numPos - nextIndex/2);
			   	inputNum = numberPattern(inputNum, numToInsert-1, numPos + nextIndex/2);
		   	 }
		   	
		   	 return inputNum;

	   }
	//--------------------------
	 static void hourglass(int inputNum, int currentLine, int space) {   
			// The program asks for a single integer from the user (if value is less than 1, it stops) 
			// The hourglass base case with input 1 displays two lines with one star on each line. 
			// Otherwise, it prints a pattern of asterisks as shown below for an input of 4. 
			// Note that there are four stars (and spaces) on the first and last lines.

			//		* * * * 
			//		 * * * 
			//		  * * 
			//		   * 
			//		   * 
			//		  * * 
			//		 * * * 
			//		* * * *
			
		   	if (inputNum != 0) {
		   		 //if input is not 0
				//---------------------
		   		if (currentLine!= inputNum) {
			   		if (inputNum > currentLine) {
			   			//decreasing
			   			space+= 1;
			   		} else {
			   			//increasing
			   			space-= 1;
			   		}
			   		//print space
			   		for ( int i= 0; i < space; i++ ) {  
			    		//print line
						System.out.print(" ");
			   		}
			   		//print asterisks
			   		for ( int index= 1; index <= Math.abs(inputNum-currentLine); index++ ) {  
			    		//print line
						System.out.print("* ");
			   		}
			   		//---------------------
			   		System.out.print("\n");
		   		} else {
		   			space+= 1;
		   		}
			   	if (currentLine < inputNum*2) {
		   			hourglass(inputNum, currentLine+1, space);
		   		}
		   		//---------------------
		   	}	
	   }
	
	//--------------------------
		public static void main(String[] args) {
			//try
			
			RecursionPractice recursionPractice= new RecursionPractice(); //call the same class

			
			
			//---------------------------
			System.out.println("\n---------------------\nRecursion practice: mnemonics");
			recursionPractice.listMnemonics(String.valueOf(getInput()), "");

			
			
			//---------------------------
			System.out.println("\n---------------------\nRecursion practice: number pattern");
			//2n-1 numbers (plus spaces) for input n
			int inputNum = getInput();
			int numberOfIndexs = (int) Math.pow(2, inputNum) - 1;
			int[] convertedNumbers = recursionPractice.numberPattern(new int[numberOfIndexs], inputNum, (numberOfIndexs-1)/2);
			//print numbers
	   		for ( int i= 0; i < convertedNumbers.length; i++ ) {  
	   			System.out.print(convertedNumbers[i]);
	   			if (convertedNumbers[i] > 5) {
					//print number larger than 5 -> new line
	   				System.out.print("\n");
	   			}
	   		}

	   		
	   		
			//---------------------------
			System.out.println("\n---------------------\nRecursion practice: hourglass");
			recursionPractice.hourglass(getInput(), 0, -1);
			
		}    
}
