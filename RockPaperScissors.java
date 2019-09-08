
/****************************************************************************
 *
 * Created by: Heejo Suh
 * Created on: Feb 2018
 * Created for: learning
 * 
 * This program is an interactive rock paper scissors game
 *     
 *
 ****************************************************************************/



//have to import the following:
import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;

//****************************************************************************
//****************************************************************************
public class RockPaperScissors{

    public static void main(String[] args) {
    	//play rock paper scissors
    	
    	//set up a scanner to get the response from the user
    	Scanner scanner = new Scanner(System.in);
    	
    	//set variables to a random default
    	
    	String input, result, userInput = "", compInput;
        
        
       //create a dictionary to refer to
       /* declare HashMap */
       HashMap<String, String> play = new HashMap<String, String>();

       /*Add elements to HashMap*/
       //valid output, output required to win against it.
       play.put("Rock", "Paper"); //Paper beats Rock
       play.put("Scissors", "Rock"); //Rock beats Scissors
       play.put("Paper", "Scissors"); //Scissors beats Paper
  
       System.out.print("\n\n");
    	//-----------
        //get valid input
    	while (userInput == "") {
    		//keep asking for input
    		System.out.print("Rock, Scissors, Paper?\nUser: ");
		
    		//Get input as a string value-----
    		input = scanner.nextLine();
    		
    		//check if available input---------
    		if (play.containsKey(input)){
    			userInput = input; 
    			//-------------------------------
    		    //get computer input
    		    // Get a random entry from the HashMap.
    			//Random rand = new Random();
    		     Object[] availableInputs = play.keySet().toArray();
    		     Object randomKey = availableInputs[new Random().nextInt(availableInputs.length)];
    		     
    		     //compInput= randomKey.toString();
    		     compInput= randomKey.toString();
    		     
    		     System.out.println("Computer: " + compInput);
    		     
    		     //----------
    		     //calculate win
    		     
    		     //**************CANT use String==String 
    		     //new String("test") == new String("test") // --> false 
    		     if (userInput.equals(compInput)) {
    		    	 //tie
    		    	 result= "It's a tie!";
    		     }else if (play.get(userInput) == compInput) {
    		    	 //lose
    		    	 result= "You lost!";
    		     }else {
    		    	 //win
    		    	 result= "You won!";
    		     }
    		     
    		     System.out.println(result);
    			//break;
    		}else{
        		//Not in the dictionary
        		System.out.println("ERROR: Try again with Rock, Scissors, or Paper.\n");
        	}	
    	}//closing for while
          			
    
    }//closing for public main
}//closing for public class RockPaperScissors
