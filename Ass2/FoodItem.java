
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;



/**
 * 
 * File:			FoodItem.java
 * Description:		This class is the basic struct (superclass) for all FoodItems,
 * 						which handles various item information.
 * Student Name: 	Jo Suh
 * Student Number:  040943462
 * Assignment:		2
 * Course:			CST8130_Data Structures
 * @author Jo Suh
 * @version 2.0
 * @since 1.0
 *
 */
public class FoodItem implements Comparable<FoodItem>{


	/**  The item's code. */
	private int itemCode;

	/**  The item's name. */
	private String itemName;

	/**  The item's price. */
	private float itemPrice;

	/**  The item's quantity in stock. */
	private int itemQuantityInStock;

	/**  The item's cost. */
	private float itemCost;

	/** The cents format. */
	private DecimalFormat centsFormat = new DecimalFormat("#0.00");

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Instantiates a new food item.
	 */
	protected FoodItem () {
		centsFormat.setRoundingMode(RoundingMode.HALF_UP); // rounds numbers up if place greater than 5
	}

	// -------------------------------------------------------------------------------------------------------------------------

	/**
	 * Returns a String value of the values in the inventory
	 * 
	 * @return String of the values in the inventory
	 */
	public String toString(){
		// Displays the all data members in the class
		// Item: <code> <name> <quantity> price: $<price> cost: $<cost>

		String returnString = "Item: " + itemCode +
				" " + itemName +
				" " + itemQuantityInStock +
				" price: $" + centsFormat.format(itemPrice) +
				" cost: $" + centsFormat.format(itemCost) ;

		return returnString;

	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Output item to the writer
	 * just the data portion of it, no description needed
	 *
	 * @param writer the writer
	 */
	public void outputItem(java.util.Formatter writer) {
		writer.format("\n%d", itemCode);
		writer.format("\n%s", itemName);
		writer.format("\n%d", itemQuantityInStock);
		writer.format("\n%.2f", itemPrice);
		writer.format("\n%.2f", itemCost);
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Gets the item code
	 *
	 * @return the item code
	 */
	public int getItemCode() {
		return itemCode;
	}

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Update item's quantity.
	 *
	 * @param amount the amount to add (can be positive or negative)
	 * @return true, if able to successfully update the quantity, false if not
	 */
	protected boolean updateItem (int amount){
		// Updates the quantity field by amount (note amount could be positive or negative);
		// Method returns true if successful, otherwise returns false
		// itemQuantityInStock field can never be less than 0

		if ( (itemQuantityInStock + amount) < 0 ) {
			return false;
		} else {
			itemQuantityInStock += amount;
			return true;
		}
	}

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Compares 2 FoodItem objects together based on their item code,
	 * and returns a corresponding int result
	 * @param item FoodItem object to compare to
	 * @return int value of the compared result
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FoodItem item){
		// Method returns 0 if the itemCode of
		// the object being acted on and the item object parameter are the same value
		return Integer.compare( this.itemCode, item.itemCode );
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds the item.
	 *
	 * @param scanner from main, sither Scanner(System.in) or Scanner(File)
	 * @param fromFile a boolean value specifying whether the data is being inputted from a file or not
	 * @return true, if successful
	 */
	public boolean addItem(Scanner scanner, boolean fromFile){
		// Reads from the Scanner object passed in
		// and fills the data member fields of the class with valid data;
		// Method returns true if program successfully reads in all fields,
		// otherwise returns false

		inputCode(scanner, fromFile);

		if (fromFile) {
			//adding values from the text file
			try {
				itemName = scanner.nextLine();
				itemQuantityInStock = Integer.valueOf(scanner.nextLine());
				itemPrice = Float.valueOf(scanner.nextLine());
				itemCost = Float.valueOf(scanner.nextLine());
				return true;
			} catch (InputMismatchException x) {
				System.out.println("ERROR: invalid file");
				return false;
			} catch (NumberFormatException x) {
				System.out.println("ERROR: invalid file data");
				return false;
			}

		}else {
			String userInput;
			int tempInt;
			float tempFloat;
			int currentStep = 1; // start with name

			//check
			while (currentStep!= 5) {
				try {
					switch (currentStep) {

					case 1:

						System.out.print("Enter the name for the item: ");// can be any input except empty string
						userInput= scanner.nextLine(); // use nextLine to get the whitespaces
						if (userInput.replace(" ", "").isEmpty()) {
							throw new InputMismatchException();
						}
						// else
						itemName = userInput;
						currentStep+=1;
						break;

					case 2:

						System.out.print("Enter the quantity for the item: ");// must be a positive int
						userInput= scanner.nextLine(); // use nextLine to get the whitespaces
						if (userInput.isEmpty()) {
							throw new InputMismatchException();
						}
						tempInt= Integer.valueOf(userInput);
						// check if positive
						if (tempInt < 0) {
							throw new InputMismatchException();
						}
						itemQuantityInStock = tempInt;
						currentStep+=1;
						break;

					case 3:

						System.out.print("Enter the cost of the item: ");// must be a positive number
						userInput= scanner.nextLine(); // use nextLine to get the whitespaces
						if (userInput.isEmpty()) {
							throw new InputMismatchException();
						}

						tempFloat= Float.valueOf( centsFormat.format( Float.valueOf(userInput) ) );
						// check if positive
						if (tempFloat < 0) {
							throw new InputMismatchException();
						}
						itemCost = tempFloat;
						currentStep+=1;
						break;

					case 4:
						System.out.print("Enter the sales price of the item: ");// must be a positive number
						userInput= scanner.nextLine(); // use nextLine to get the whitespaces
						if (userInput.isEmpty()) {
							throw new InputMismatchException();
						}
						tempFloat= Float.valueOf( centsFormat.format( Float.valueOf(userInput) ) );
						// check if positive
						if (tempFloat <= 0) {
							throw new InputMismatchException();
						}
						itemPrice = tempFloat;
						currentStep+=1;
						break;
					}

				} catch (NumberFormatException x) {
					System.out.println("Invalid entry");
				} catch (InputMismatchException x) {
					System.out.println("Invalid entry");
				}
			}
			return true;
		}


	}


	/**
	 * Asks and receives an item code from the user, then checks if it is the right format (int).
	 *
	 * @param scanner from main, sither Scanner(System.in) or Scanner(File)
	 * @param fromFile a boolean value specifying whether the data is being inputted from a file or not
	 * @return true, if successfully got itemCode from user
	 */
	public boolean inputCode(Scanner scanner, boolean fromFile){
		// Reads a valid itemCode from the Scanner object and returns true/false if successful

		String userInput;
		if (!fromFile) {
			System.out.print("Enter the code for the item: "); 
		}
		userInput= scanner.nextLine();

		try {
			// must be an int
			if (userInput.replace(" ", "").isEmpty()) {
				//if empty string
				throw new InputMismatchException();
			}
			itemCode= Integer.valueOf(userInput);
			return true;
		} catch (NumberFormatException x) {
			if (!fromFile) {
				System.out.println("Invalid entry");
			}
		}
		return false;

	}

}

