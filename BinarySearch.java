/****************************************************************************
* Created by: Heejo Suh
 * Created on: Mar 2018
 * Created for: learning
 * 
 * This program generate a random list of 250 values and present them to the user. 
 * Then the list is sorted and presented to the user again. 
 * Finally you enter a number to be found. 
 * If the number is in the list, the position will be returned. 
 * If it is not in the list, the user will be informed of this.
 * 
 * 
 * 
 * Search a sorted array by repeatedly dividing the search interval in half.
 * Compare values and narrow into halves
 * Repeatedly check until the value is found or the interval is empty.
 *     
 *
 ****************************************************************************/



//have to import the following:
import java.util.Scanner;
import java.util.Random;
import java.math.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


//****************************************************************************
public class BinarySearch{

	//------------------------
	public static int binarySearch(List<Integer> inputArray , int inputNumber){
	//Checks if input is in array and return answer
		
		int startCountingPoint = 0, lastCountingPoint = (inputArray.size() -1), middleValue;
		//** to get length of listArray, you need to use '.size()'
		
		
		while (startCountingPoint <= lastCountingPoint) {
			//while range exists
			//check halves for number 
			
			int middlePoint = (int) Math.round( (lastCountingPoint+startCountingPoint)/2 );
			middleValue = inputArray.get(middlePoint);
			
			
			//if input is at middleValue
			if (inputNumber == middleValue) {
				return middlePoint; //this breaks the 'while' and the 'binarySerach'
				
			} else if (inputNumber > middleValue) {
				//if input is greater than middleValue
				//ignore left half
				//all the values less will be useless
				startCountingPoint = middlePoint + 1; //not including mid
				
			} else if (inputNumber < middleValue) {
				//if input is less than middleValue
				//ignore right half
				//all the values greater will be useless
				lastCountingPoint = middlePoint - 1; //not including mid
			}
		}//closing for while
			
		//if reach here, no values are in the list
		//so return '-1'
		return -1;
    }//closing for binarySearch
		
	
	
	//------------------------
	public static List<Integer> sortList(List<Integer> inputArray){
			//Sorts the unsorted list and assigns the result to the sorted list

			List<Integer> sortedList = new ArrayList<>();

			Integer smallestVal = 0; //default is 0
			
			for(int count = 0 ; count<inputArray.size() ; ) {
				//check all values of input array 
				smallestVal = inputArray.get(0); //set as new smallest number
				for(int eachValue: inputArray) {
					//search for the smallest value in input array and restate it as such
					//if the value is less than the smallest value, set it to new smallest value
					smallestVal = (eachValue < smallestVal)? eachValue : smallestVal;
				}
				
				sortedList.add(smallestVal); //add to sortedList
				inputArray.remove(smallestVal); //remove the small value from input array
			}
			
			return sortedList;
			
		
		}
	
	
	//------------------------
	
	public static void main(String args[]){
	//uses the binarySearch function to search
		
		Scanner scanner = new Scanner(System.in);
		String input = "", choice = "";
		int inputNumber = 0, numberOfIntegersInList = 250;
		
		//create random list
		List<Integer> randomList = new ArrayList<>(), sortedList = new ArrayList<>();
		
		
		System.out.println("List:");
		//add 250 random numbers
		for ( int count=0; count<numberOfIntegersInList; count++ ) {  
    		//add random number from 0 to 1000
	    	int randNumber= new Random().nextInt(1000);
	    	System.out.print(randNumber+" "); //show to user
	    	randomList.add(randNumber);
    	}
		
		
		while (!choice.equals("E")) {
			//not have exited program
			
			//get choice
			while (!choice.equals("I") && !choice.equals("S")){
				//see if user wants to input a value or search
				System.out.println("\n\n\n-----------------------------------------------"
						+ "\nI : insert value\nS : search for value\nE : exit");

				choice = scanner.nextLine();
				
				if (! choice.equals("I") && !choice.equals("S")){
					//error
					System.out.println("Input a valid answer! Try again!");
				}
			}//closing for get choice
			
			
			//get number
			while (input == ""){
				//see if input is a number
				System.out.println("\nInsert a number:");
				
				try {
					//check if response is an integer
					input = scanner.nextLine();
					inputNumber = Integer.parseInt(input);
				}catch (IllegalArgumentException x) { 
					System.out.println("Insert an integer!");
				}
			}//closing for get number
			
			
			
			//return
			if (choice.equals("I")){
				//insert value
				randomList.add(inputNumber);
			}
			
			//sort
			sortedList = sortList(randomList); //sorts list from least to greatest
			
			
			System.out.print("\nSorted list:\n"+sortedList+"\n");
			
			if (choice.equals("S")){
				//search value
				int answer = binarySearch(sortedList, inputNumber);
				
				if (answer == -1) {
					//not in the list
		            System.out.println("\nNumber is not in the list!");
				} else {
					//in the list

		            System.out.println("\n"+inputNumber+" is at "+answer+" index!");
				}
			}
			

			//need to reset to default
			choice = ""; 
			input = "";
			inputNumber = 0;
			
		}//closing for while not have exitted the program
		
			
    }//closing for main
}//closing for BinarySearch
