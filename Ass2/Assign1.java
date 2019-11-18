
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * 
 * File:			Assign1.java
 * Description:		This class will run with a basic menu that allows the user to
 * 						add an item to inventory,
 * 						display to the screen the inventory,
 * 						buy an item (i.e. add to the quantity of that item),
 * 						and sell an item (i.e. subtract from the quantity of that item)
 * Student Name: 	Jo Suh
 * Student Number:  040943462
 * Assignment:		2
 * Course:			CST8130_Data Structures
 * @author Jo Suh
 * @version 2.0
 * @since 1.0
 *
 */
public class Assign1 {


	/**
	 * Displays the menu.
	 */
	private void displayMenu(){
		//Displays the menu options
		System.out.println("Please select one of the following:\n"
				+ "1: Add Item to Inventory" + "\n"
				+ "2: Display Current Inventory" + "\n"
				+ "3: Buy Item(s)" + "\n"
				+ "4: Sell Item(s)" + "\n"
				+ "5: Search for Item" + "\n"
				+ "6: Save Inventory to File" + "\n"
				+ "7: Read Inventory from File" + "\n"
				+ "8: To Exit");
	}

	/**
	 * The main method.
	 *
	 * @param args Basic arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Assign1 program = new Assign1();
		int userOption=0;
		int[] optionRange = {1, 8};


		Inventory inventory = new Inventory(); 

		while( userOption != optionRange[1]) {
			program.displayMenu();

			//get user input
			try {
				//check the string for whitespaces
				String userInput = scanner.nextLine();//use nextLine to get the spaces
				if (userInput.isEmpty()) {
					throw new InputMismatchException();
				}
				userOption= Integer.valueOf(userInput);

			} catch (InputMismatchException x) {
				System.out.println("Please enter a valid integer");
				continue;
			} catch (NumberFormatException x) {
				System.out.println("Please enter an integer");
				continue;
			}


			// if acceptable input, perform the corresponding actions
			switch(userOption) {

			//--------------------------------------------------------------------
			case 1:
				// Add Item to Inventory
				if (inventory.addItem(scanner, false)) {
					//successful
					System.out.println("Successfully added item to inventory");
				}
				break;
				//--------------------------------------------------------------------
			case 2:
				// Display Current Inventory
				System.out.println("Inventory: \n" + inventory.toString());
				break;
				//--------------------------------------------------------------------
			case 3:
				// Buy Item(s)
				if (inventory.updateQuantity(scanner, true)) {
					//successful
					System.out.println("Successfully bought item(s)");
				}
				break;
				//--------------------------------------------------------------------
			case 4:
				// Sell Item(s)
				if (inventory.updateQuantity(scanner, false)) {
					//successful
					System.out.println("Successfully sold item(s)");
				}
				break;
				//--------------------------------------------------------------------
			case 5:
				// Search for Item
				// view a FoodItem given the itemCode
				inventory.searchForItem(scanner);
				break;
				//--------------------------------------------------------------------
			case 6:
				// Save Inventory to File
				inventory.saveToFile(scanner);
				break;
				//--------------------------------------------------------------------				
			case 7:
				// Read Inventory from File
				inventory.readFromFile(scanner);
				break;
				//--------------------------------------------------------------------	
			case 8: // the program terminates
				// To Exit
				System.out.println("Exiting...");
				break;
				//--------------------------------------------------------------------
			default:
				// invalid number range
				System.out.println("Incorrect value entered");
			}
			//--------------------------------------------------------------------
		}
		scanner.close(); // close Scanner
	}


}