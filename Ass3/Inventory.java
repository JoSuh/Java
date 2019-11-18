
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;





/**
 * 
 * File:			Inventory.java
 * Description:		This class handles the inventory for Food Items;
 * 						checking, updating, and managing the values.
 * Student Name: 	Jo Suh
 * Student Number:  040943462
 * Assignment:		3
 * Course:			CST8130_Data Structures
 * @author Jo Suh
 * @version 3.0
 * @since 1.0
 *
 */
public class Inventory {

	/**  The inventory of InventoryItems. */
	private ArrayList<InventoryItem> inventory;



	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Instantiates a new inventory.
	 */
	protected Inventory() {
		inventory= new ArrayList<InventoryItem>();
	}


	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * To string.
	 *
	 * @return String of the values in the inventory
	 */
	public String toString(){
		// returns a String of the values
		String toReturn = "";
		for (InventoryItem each :  inventory) {
			toReturn += each.toString();
			toReturn += "\n";
		}
		return toReturn;
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Checks if a InventoryItem with the same item code exists.
	 *
	 * @param item (InventoryItem) to compare the code to
	 * @return int value of the item's index in inventory, if doesn't match then returns -1
	 */
	protected int alreadyExists (InventoryItem item) {
		// Returns the index of a FoodItem in the inventory array
		// with the same itemCode as the FoodItem object in the parameter list
		// else returns -1

		for ( int i=0 ; i< inventory.size() ; i++ ) {
			if (inventory.get(i).getItemCode() == item.getItemCode() ) {
				return i;
			}
		}
		// else
		return -1;

	}

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds the InventoryItem to the inventory.
	 *
	 * @param scanner from main is passed on
	 * @return true if successfully added item, otherwise false
	 */
	public boolean addItem(Scanner scanner) {
		// Adds an item to the inventory array
		// uses polymorphism to call addItem method in the correct derived FoodItem class
		// for input of the fields of the FoodItem

		// The new item to add
		InventoryItem newInventoryItem = new InventoryItem();

		if ( !newInventoryItem.addItem(scanner) ) {
			return false;
		}
		//check if item code is overlapping
		// If the user tries to enter a code that already exists,
		// an error message should be displayed, entry should not continue,
		// the program should go back to the main menu and display it again
		if ( alreadyExists(newInventoryItem) != -1) {
			// Overlapping item code
			System.out.println("ERROR: Item code already exists");
			return false;
		}else {
			// no overlapping itemcode -> try to add value
			inventory.add(newInventoryItem);
			//sort items by item code
			Comparator<InventoryItem> compareByCode =
					(InventoryItem item1, InventoryItem item2) -> Integer.valueOf(item1.getItemCode()).compareTo(Integer.valueOf(item2.getItemCode()));
					Collections.sort(inventory, compareByCode);
					return true; // successful
		}

	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Update quantity of items: buy or sell item.
	 *
	 * @param scanner the scanner from main is passed on
	 * @param buyOrSell value of whether the user is buying or not
	 * @param today date to compare the items to
	 * @return true if successfully modified the quantity, false if not
	 */
	protected boolean updateQuantity (Scanner scanner, boolean buyOrSell, LocalDate today) {
		// Reads in an itemCode to update and quantity to update by
		// and updates that item by the input quantity in the inventory array.
		// The boolean parameter is used to denote whether buying operation (true) or selling operation (false) is occurring.
		// Method returns true/false on whether update was successful or not


		String operation= buyOrSell ? "buy" : "sell"; // set string to ask
		int amount= buyOrSell ? 1 : -1; // amounts to multiply the values by

		if (inventory.size()>0) {
			//need to have data in inventory
			//----------------------------------------
			// Get item code to update
			// Create a temporary InventoryItem to use its method
			InventoryItem temp = new InventoryItem();
			if (!temp.inputCode(scanner)){
				//user entered a wrong item code
				return false;
			}
			// check if code exists
			int indexOfItem = alreadyExists(temp);
			if ( indexOfItem == -1 ) {
				//Item does not exist
				System.out.println("Error: Code not found in inventory...");
				return false; // unsuccessful
			}
			// operate
			if (inventory.get(indexOfItem).updateQuantity(scanner, amount)) {
				//successfully updated list
				inventory.get(indexOfItem).removeExpiredItems(today);
				//must also remove the LocalDate objects that are past the expiry date
				return true;
			}
		}else {
			System.out.println("Insufficient stock in inventory...");
			throw new InputMismatchException();
		}
		System.out.printf("Error...could not %s item\n", operation);
		return false; // unsuccessful
	}

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Prints the expiry dates for an item
	 *
	 * @param scanner the scanner from main
	 */
	public void printExpiry(Scanner scanner) {

		InventoryItem item = getInventoryItem(scanner);
		if (item!= null) {
			item = inventory.get(alreadyExists(item));
			System.out.println(item.toString());
			System.out.println("Expiry Details:");
			item.printExpirySummary();
		}
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Removes the expired items
	 *
	 * @param today the today
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	public void removeExpired(LocalDate today) {
		if (today!=null) {
			for (InventoryItem each: inventory) {
				each.removeExpiredItems(today);
			}
			System.out.println("Successfully removed the expired items in inventory");
		}else {
			System.out.println("Invalid date");
		}
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Gets the inventory item from user item code input
	 *
	 * @param scanner the scanner from main
	 * @return the inventory item
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	private InventoryItem getInventoryItem(Scanner scanner) {
		//returns null if the item code is invalid or does not exist
		System.out.print("Enter the code for the item: ");
		String userInput = scanner.nextLine();

		try {
			int itemCode = Integer.valueOf(userInput);
			for (InventoryItem each: inventory) {
				if (each.getItemCode() == itemCode) {
					return each;
				}
			}
		} catch (NumberFormatException x) {}
		System.out.println("Code not found in inventory...");
		return null;
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Search for item in the inventory
	 *
	 * @param scanner the scanner from main
	 */
	public void searchForItem(Scanner scanner) {

		InventoryItem item = getInventoryItem(scanner);
		if (item != null && alreadyExists(item)==-1 ) {
			System.out.println("Item: "+ item.toString());
		}
		return;

	}
}