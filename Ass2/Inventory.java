
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;




/**
 * 
 * File:			Inventory.java
 * Description:		This class handles the inventory for Food Items;
 * 						checking, updating, and managing the values.
 * Student Name: 	Jo Suh
 * Student Number:  040943462
 * Assignment:		2
 * Course:			CST8130_Data Structures
 * @author Jo Suh
 * @version 2.0
 * @since 1.0
 *
 */
public class Inventory {

	/**  The inventory of FoodItems. */
	private ArrayList<FoodItem> inventory;

	/** The path. */
	private String PATH = "C:\\Users\\77hee\\eclipse-workspace\\8130\\src\\";

	/** The text file regex. */
	private String TEXT_FILE_REGEX =   "^([^\\s]+)(\\.(?i)txt)$"; 
	/*
	 $ = checks if a line end follows
	 [^\\s]+ = any characters except whitespace
	 \\. = dot
	 (?i) = ignore case checking for the following
	 \w = any word character = [a-zA-Z]
	 + = finds one or several
	 {x} = occurs x times only
	 */

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Instantiates a new inventory.
	 */
	protected Inventory() {
		// For now, limit the inventory array to a maximum of 20 entries
		inventory= new ArrayList<FoodItem>();
	}


	/**
	 * Gets the file name.
	 *
	 * @param scanner the scanner
	 * @param toAsk the to ask
	 * @return the file name
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	private String getFileName(Scanner scanner, String toAsk) {
		System.out.println("Name of the file to " + toAsk + ": ");
		String fileName = scanner.nextLine();

		//check valid text file format
		if ( !Pattern.matches(TEXT_FILE_REGEX, fileName) ) {
			return null;
		}

		return fileName;
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * @return String of the values in the inventory
	 */
	public String toString(){
		// returns a String of the values
		String toReturn = "";
		for ( int i=0 ; i<inventory.size() ; i ++) {
			toReturn += inventory.get(i).toString();
			toReturn += "\n";
		}
		return toReturn;
	}

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Checks if a FoodItem with the same code exists.
	 *
	 * @param item (FoodItem) to compare the code to
	 * @return int value of the item's index in inventory, if doesn't match then returns -1
	 */
	protected int alreadyExists (FoodItem item) {
		// Returns the index of a FoodItem in the inventory array
		// with the same itemCode as the FoodItem object in the parameter list
		// else returns -1

		for ( int i=0 ; i< inventory.size() ; i++ ) {
			if (inventory.get(i).compareTo(item) == 0) {
				return i;
			}
		}
		// else
		return -1;

	}

	/**
	 * Save to file.
	 *
	 * @param scanner the scanner
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	public void saveToFile(Scanner scanner) {
		//save the current inventory data into the text file
		Formatter writer= null;

		try {
			String file = getFileName(scanner, "save to");
			if (file==null) {
				throw new InputMismatchException();
			}
			File outputTextFile = new File(PATH +file );
			if (!outputTextFile.exists()) {
				outputTextFile.createNewFile();
				System.out.println("Creating new file...");
			}else {
				System.out.println("Adding to file...");
			}

			int numOfItems=0;
			String originalComponents = "";
			ArrayList<Integer> originalItemCodes= new ArrayList<Integer>();
					
			
			Scanner reader = new Scanner(outputTextFile);
			if (reader.hasNext()) {
				//if file already has components
				numOfItems= Integer.valueOf(reader.nextLine());
				//copy the values in the file to an array
				
				while(reader.hasNext()) {
					int itemCode = Integer.valueOf(reader.nextLine());
					originalItemCodes.add(itemCode);
					originalComponents+=scanner.nextLine(); //itemName
					originalComponents+=scanner.nextLine(); //itemQuantityInStock
					originalComponents+=scanner.nextLine(); //itemPrice
					originalComponents+=scanner.nextLine(); //itemCost
				}
			}
			reader.close();

			//open the output file
			writer = new Formatter( outputTextFile );
			writer.flush();//clear the file
			writer.format("%d", (int) numOfItems+inventory.size()); //write correct number of FoodItems
			if (!originalComponents.replace(" ", "").isEmpty()) {
				writer.format("\n%s", originalComponents); //add in the original data
			}

			for (FoodItem eachItem: inventory) {
				//check for duplicate item code
				for (Integer originalCode: originalItemCodes) {
					if (eachItem.getItemCode()==originalCode) {
						//duplicate item code
						System.out.println("ERROR: Duplicate item code");
						throw new InputMismatchException();
					}
				}
				//append item type
				if (eachItem instanceof Fruit) {
					writer.format("\n%s", "f");
				}else if (eachItem instanceof Vegetable) {
					writer.format("\n%s", "v");
				} else if (eachItem instanceof Preserve) {
					writer.format("\n%s", "p");
				}else {
					throw new InputMismatchException();
				}
				
				//append extra information
				eachItem.outputItem(writer);
			}
			System.out.println("Saved file successfully");

		}catch (IOException x) {
			System.out.println("ERROR: An error occured");
		}catch (NumberFormatException x) {
			System.out.println("ERROR: Invalid text file");
		}catch (InputMismatchException x) {
			System.out.println("ERROR: Invalid text file");
		}
		try {
			writer.close();
		}catch (NullPointerException x) {}
	}

	/**
	 * Read from file.
	 *
	 * @param scanner the scanner
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	public void readFromFile(Scanner scanner) {
		// Upon read, find a duplicate itemCode, abort reading in the file.
		// Valid FoodItems before that one should remain in the inventory and an appropriate error message should be displayed
		Scanner reader = null;
		try {
			String file = getFileName(scanner, "read from");
			if (file==null) {
				throw new InputMismatchException();
			}
			File inputTextFile = new File(PATH + file);
			if (!inputTextFile.exists()) {
				throw new FileNotFoundException();
			}
			System.out.println("Reading from file...");

			reader = new Scanner(inputTextFile);
			int numOfItems = Integer.valueOf(reader.nextLine());
			if (!reader.hasNextLine()) {
				//Only has a zero in the file
				System.out.println("ERROR: Empty text file");
				reader.close();
				return;
			}
			for (int i=0 ; i<numOfItems ; i++) {
				//for every item in the list
				if (!addItem(reader, true)) {
					throw new InputMismatchException();
				}
			}
			System.out.println("Succesfully read inputs from file");
			return;
		}catch (FileNotFoundException x) {
			System.out.println("File Not Found, ignoring...");
		}catch (InputMismatchException x) {
			System.out.println("Error Encountered while reading the file, aborting...");
		}
		try {
			reader.close();
		}catch (NullPointerException x) {}
	}

	/**
	 * Gets the type.
	 *
	 * @param itemType the item type
	 * @return the type
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	private FoodItem getType(String itemType) {
		// checks the Item type and returns a instance of the corresponding FoodItem
		// returns null if invalid String
		switch (itemType) {
		case "f":
			return new Fruit();
		case "p":
			return new Preserve();
		case "v":
			return new Vegetable();
		default:
			return null;
		}
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds the FoodItem to the inventory.
	 *
	 * @param scanner from main is passed on
	 * @param fromFile a boolean value specifying whether the data is being inputted from a file or not
	 * @return true if successfully added item, otherwise false
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		// Adds an item to the inventory array
		// uses polymorphism to call addItem method in the correct derived FoodItem class
		// for input of the fields of the FoodItem

		/*
		FoodItem objects are in numerical order from smallest to largest by itemCode in the most efficient way possible

		 Once the inventory structure is sorted, modify your other methods to also make them as efficient as possible.
		 (Note – do NOT use Generic Collection methods yet – we will learn to do the coding ourselves first).
		 */

		// The new item to add
		FoodItem newItemToAdd;
		newItemToAdd = null;

		if (fromFile) {
			//adding values from the text file
			newItemToAdd= getType(scanner.nextLine());
			if (newItemToAdd==null) {
				//corrupted file
				return false;
			}
		}else {
			//adding values from user
			//Get Type
			while (newItemToAdd==null) {
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
				newItemToAdd= getType( scanner.nextLine().toLowerCase() );
			}
		}

		if ( !newItemToAdd.addItem(scanner, fromFile) ) {
			return false;
		}

		//check if item code is overlapping
		// If the user tries to enter a code that already exists,
		// an error message should be displayed, entry should not continue,
		// the program should go back to the main menu and display it again
		if ( alreadyExists(newItemToAdd) != -1) {
			// Overlapping item code
			System.out.println("ERROR: Item code already exists");
			return false;
		}else {
			// no overlapping itemcode -> try to add value
			int indexToBePlaced=inventory.size();
			for(int i=inventory.size()-1 ; i>=0 ; i-- ) {
				if ( newItemToAdd.compareTo(inventory.get(i))<0 ) {
					//if the new item's itemcode is less than the one in the inventory
					indexToBePlaced=i;
				}else {
					break;
				}
			}
			inventory.add(indexToBePlaced, newItemToAdd);
			return true; // successful
		}

	}


	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Update quantity of items: buy or sell item.
	 *
	 * @param scanner the scanner from main is passed on
	 * @param buyOrSell value of whether the user is buying or not
	 * @return true if successfully modified the quantity, false if not
	 */
	protected boolean updateQuantity (Scanner scanner, boolean buyOrSell) {
		// Reads in an itemCode to update and quantity to update by
		// and updates that item by the input quantity in the inventory array.
		// The boolean parameter is used to denote whether buying operation (true) or selling operation (false) is occurring.
		// Method returns true/false on whether update was successful or not

		String userInput, operation;
		operation= buyOrSell ? "buy" : "sell"; // set string to ask

		if (inventory.size()>0) {
			//need to have data in inventory
			try {
				// Get item code to update
				// Create a temporary Food item to use its method
				FoodItem temp = new FoodItem();
				System.out.print("Enter valid item code: "); 
				if (!temp.inputCode(scanner, false)){
					//user entered a wrong item code
					return false;
				}

				int indexOfItem = alreadyExists(temp);
				if ( indexOfItem == -1 ) {
					//Item does not exist
					System.out.println("Code not found in inventory...");
					throw new InputMismatchException();
				}


				//Get number of Item update
				System.out.printf("Enter valid quantity to %s: ", operation); // must be an int
				userInput= scanner.nextLine(); // use nextLine to get the whitespaces
				if (userInput.isEmpty()) {
					throw new NumberFormatException();
				}
				int amount= Integer.valueOf(userInput);
				if (amount<0 ) {
					throw new NumberFormatException();
				}

				int buying = buyOrSell? 1 : -1; // selling: set the buying value to a negative so can subtract
				if ( inventory.get(indexOfItem).updateItem(amount*buying) ) {
					//successfully updated list
					return true;
				}else {
					System.out.println("Insufficient stock in inventory...");
					throw new InputMismatchException();
				}

			} catch (NumberFormatException x) {
				System.out.printf("Invalid quantity...");
			} catch (InputMismatchException x) {

			}
		}
		System.out.printf("Error...could not %s item\n", operation);
		return false; // unsuccessful
	}

	/**
	 * Search for item.
	 *
	 * @param scanner the scanner
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	public void searchForItem(Scanner scanner) {
		System.out.print("Enter the code for the item: ");
		String userInput = scanner.nextLine();

		try {
			int itemCode = Integer.valueOf(userInput);
			for (FoodItem each: inventory) {
				if (each.getItemCode() == itemCode) {
					System.out.println("Item: "+ each.toString());
					return;
				}
			}
		} catch (NumberFormatException x) {}
		System.out.println("Code not found in inventory...");

	}
}