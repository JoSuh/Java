
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class stores an ArrayList of Integers in sorted order using Collections.sort() to keep the ArrayList sorted
 * Student Name: Jo Suh
 *
 */
public class BinarySearch {

	private static ArrayList<Integer> arrayList= new ArrayList<Integer>();

	//===========================================================
	public BinarySearch() {}
	//===========================================================
	public boolean canBeInt(String input, int ...range) {
		// Asks a question and gets a number input from user
		// if not the correct format, returns null
		
		try {
			//check the string for whitespaces
			if (input.isEmpty()) {
				throw new InputMismatchException(); //invalid number
			}
			int userInt = Integer.valueOf(input);

			//check if the number is in the correct range
			if ( range.length> 0 && (userInt < range[0] || userInt > range[1])) {
				throw new InputMismatchException(); //invalid number
			}
			return true;
		} catch (InputMismatchException x) {
			System.out.println("Please enter a valid integer");
		} catch (NumberFormatException x) {
			System.out.println("Please enter an integer");
		}
		return false;
	}
	//===========================================================
	public boolean addItem(Scanner scanner) {

		System.out.println("Please specify an Integer to add:");
		String userInput =  scanner.nextLine(); //use nextLine to get the spaces
		if (!canBeInt(userInput)) {
			//invalid input
			return false;
		}
		
		arrayList.add(Integer.valueOf(userInput)); //add in value
		Collections.sort(arrayList); // sort array
		
		return true;
	}
	//===========================================================
	public int searchItem(Scanner scanner) {
		//searches for item in the array list and returns the index
		// if item not found, returns -1

		System.out.println("Please specify an Integer to search:");
		String userInput =  scanner.nextLine(); //use nextLine to get the spaces
		if (!canBeInt(userInput) || arrayList.size()==0 ) {
			//invalid input
			return -1;
		}
		int itemToCheck = Integer.valueOf(userInput);
		// implements binary search
		return binarySearch(itemToCheck, 0, arrayList.size()-1);
	}
	//===========================================================
	public static int binarySearch(int inputNumber, int start, int end){
		//Checks if input is in array and return answer
		
		if (end >=start) {
			int midIndex = start + ((int) Math.round( (end-start)/2 ));
			int midValue = arrayList.get(midIndex);
	
			if (midValue == inputNumber) {
				//equal
				return midIndex; 
			}
			if (midValue < inputNumber) {
				//if input is greater than middleValue
				//ignore left half
				//all the values less will be useless
				return binarySearch(inputNumber, midIndex+1, end); 
	
			} else {
				// if (inputNumber < midValue) {
				//if input is less than middleValue
				//ignore right half
				//all the values greater will be useless
				return binarySearch(inputNumber, start, midIndex-1); 
			}
		}
		return -1;
		

   }//closing for binarySearch
			
	//===========================================================
	public String toString() {
		String indexes="index:\t";
		String list= "values:\t";
		for (int i=0 ; i<arrayList.size() ; i++) {
			indexes += i + "\t" ;
			list += arrayList.get(i) + "\t";
		}
		return indexes + "\n" + list + "\n";
	}

	//===========================================================
	public void displayMainMenu() {
	// Outputs the main menu to the console (standard output)
			/*
			Please Enter:
			1. Add Item
			2. Search for Item
			3. Display List
			4. Exit
			*/
		
		System.out.print("Please select one of the following:" + "\n"
							+ "1. Add Item" + "\n"
							+ "2. Search for Item" + "\n"
							+ "3. Display List" + "\n"
							+ "4. Exit"
							);
		
	}
	//===========================================================
	public static void main(String[] args) {
		// Runs the main program
		
		Scanner scanner = new Scanner(System.in);
		
		BinarySearch sample = new BinarySearch();
		int userOption=0;
		int menuMin = 1, menuMax = 4;
		
		//display the main menu console while the user has not exitted
		program: while(true) {
			
			//show options
			sample.displayMainMenu();

			//get user input
			System.out.println("");
			String userInput =  scanner.nextLine(); //use nextLine to get the spaces
			if (!sample.canBeInt(userInput, menuMin, menuMax)) {
				//invalid input: restart the menu
				continue;
			}
			userOption = Integer.valueOf(userInput);
			
			
			// if acceptable input, perform the corresponding actions
			switch(userOption) {
			
			case 1:
				// Add Item
				if (!sample.addItem(scanner)) {
					System.out.println("Failed to add item");
				}
				break;
				
				
			case 2:
				// Search for Item
				int index = sample.searchItem(scanner);
				System.out.print("Index of Integer is: ");
				if (index==-1) {
					System.out.println("Not Found");
				}else {
					System.out.println(index);
				}
				break;
				
				
			case 3:
				// Display List
				System.out.println(sample.toString());
				break;
				
				
			case 4:
				// Exit
				System.out.println("Exiting...");
				break program;
				
			}
			
		}
		scanner.close();
	}

}

