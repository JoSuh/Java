
import java.util.Scanner;
import java.util.Stack;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * 
 * File:			InventoryItem.java
 * Description:		This class deals with the the FoodItems and adds extra parameters
 * Student Name: 	Jo Suh
 * Student Number:  040943462
 * Assignment:		3
 * Course:			CST8130_Data Structures
 * @author Jo Suh
 * @version 1.0
 * @since 1.0
 *
 */

public class InventoryItem {

	/** The item quantity in stock. */
	private int itemQuantityInStock;
	/** The item. */
	private FoodItem item;
	/**  The item's expiry date. */
	private Stack<String[]> expires = new Stack<String[]>(); //sorted from oldest to newest
	//String values of {stock, date}


	/**
	 * Instantiates a new inventory item
	 */
	//------------------------------------------------------------------------------------
	public InventoryItem() {}

	/**
	 * Adds the item
	 *
	 * @param scanner the scanner from main
	 * @return true, if successful
	 */
	//------------------------------------------------------------------------------------
	public boolean addItem (Scanner scanner) {
		FoodItem newItemToAdd = null;
		//Get Type
		while (newItemToAdd==null) {
			System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			String itemType = scanner.nextLine().toLowerCase();

			switch (itemType) {
			case "f":
				newItemToAdd = new Fruit();
				break;
			case "p":
				newItemToAdd = new Preserve();
				break;
			case "v":
				newItemToAdd = new Vegetable();
				break;
			default:
				System.out.println("Invalid input");
			}
		}
		//Try to get the extra values
		if (newItemToAdd.addItem(scanner)) {

			try{
				//get quantity
				System.out.print("Enter the quantity for the item: ");// must be a positive int
				String userInput= scanner.nextLine(); // use nextLine to get the whitespaces
				if (userInput.isEmpty()) {
					throw new NumberFormatException();
				}
				int tempInt= Integer.valueOf(userInput);
				// check if positive
				if (tempInt < 0) {
					throw new NumberFormatException();
				}
				itemQuantityInStock += tempInt;
				item = newItemToAdd;
				//get expiry date and push onto stack
				String[] value = {String.valueOf(tempInt), String.valueOf(setExpiryDate(scanner)) };
				expires.push(value);
				return true;
			} catch (NumberFormatException x) {
				System.out.println("Invalid entry");
			}
		}
		return false;
	}

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Convert text to date in LocalDate format
	 *
	 * @param date the String to convert from
	 * @return the local date, or null if invalid input
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	private LocalDate convertTextToDate(String date) {
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate returnDate = LocalDate.parse(date,dateFormat);
			return returnDate;
		} catch(DateTimeParseException e) { 
			return null;
		}
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Gets a correct expiry date from user input and returns it
	 *
	 * @param scanner the scanner from main
	 * @return the local date
	 */
	// -------------------------------------------------------------------------------------------------------------------------
	public LocalDate setExpiryDate(Scanner scanner) {
		//retrieves and returns an expiry date from the user or a null value if invalid input
		String userInput = "";
		LocalDate returnDate = null;

		while (true) {
			System.out.print("Enter the expiry date of the item (yyyy-mm-dd or none): ");
			userInput = scanner.nextLine().trim();

			if ( userInput.toLowerCase().equals("none") ) {
				//no expiry date	
				return LocalDate.MAX;
			}
			//else, try to convert the string into a date
			returnDate = convertTextToDate(userInput);
			if (returnDate!=null) {
				// the format matched with no errors
				return (LocalDate) returnDate;
			}else {
				//check each character in string to match format
				int errorIndex = 0;
				for ( int i=0; i < userInput.length() ; i++){
					errorIndex = i;
					if (i>9) {
						//needs to be less than 10 characters
						break;
					}
					if ( i==4 || i==7 ) {
						if ( userInput.charAt(i) != '-') {
							i--;
							break;
						}
					}else {
						if (!Character.isDigit(userInput.charAt(i)) ){
							//check if the character is a number
							break;
						}
					}
				}
				//invalid input
				System.out.println("Could not create date from input, please use format yyyy-mm-dd");
				System.out.printf("Text '%s' could not be parsed at index %d\n", userInput, errorIndex);
			}
		}
	}

	//------------------------------------------------------------------------------------
	/**
	 * Gets the item code
	 * @return the item code
	 */
	//------------------------------------------------------------------------------------
	public int getItemCode() {
		return item.getItemCode();
	}
	//------------------------------------------------------------------------------------
	/**
	 * Input code.
	 *
	 * @param scanner the scanner from main
	 * @return true, if successful
	 */
	//------------------------------------------------------------------------------------
	public boolean inputCode(Scanner scanner) {
		if (item==null) {
			//define a temporary one for this function
			item = new FoodItem();
		}
		return item.inputCode(scanner);
	}
	//------------------------------------------------------------------------------------
	/**
	 * Prints the expiry summary.
	 */
	//------------------------------------------------------------------------------------
	public void printExpirySummary (){
		//prints newest first
		String output="";
		Stack<String[]> copyData = (Stack<String[]>) expires.clone(); 
		while ( !copyData.isEmpty() ){
			String[] each = copyData.pop();
			String expiry = (convertTextToDate(each[1])==LocalDate.MAX) ? "No Expiry" : each[1];
			output= expiry + ":    " + each[0] + "\n" + output;
			//System.out.printf("%s:     %s\n", expiry, each[0]);
		}
		System.out.println(output);
	}
	//------------------------------------------------------------------------------------
	/**
	 * Removes the expired items
	 *
	 * @param today the date
	 */
	//------------------------------------------------------------------------------------
	public void removeExpiredItems (LocalDate today) {

		while ( !expires.isEmpty() ){
			LocalDate date = convertTextToDate(expires.peek()[1]);
			if ( date.isBefore(today) ){
				//before today
				expires.pop();//remove
			}else {
				return; //no need to check anymore
			}
		}
	}
	//------------------------------------------------------------------------------------
	/**
	 * Update quantity
	 *
	 * @param scanner the scanner from main
	 * @param amount whether the operation should be positive or negative
	 * @return true, if successful
	 */
	//------------------------------------------------------------------------------------
	public boolean updateQuantity (Scanner scanner, int amount) {
		//----------------------------------------

		String userInput;
		String operation= amount>0 ? "buy" : "sell"; // set string to ask

		try {
			//----------------------------------------
			//Get number of Item update
			System.out.printf("Enter valid quantity to %s: ", operation); // must be an int
			userInput= scanner.nextLine(); // use nextLine to get the whitespaces
			if (userInput.isEmpty()) {
				throw new NumberFormatException();
			}
			int operationAmount= Integer.valueOf(userInput);
			if (operationAmount<0 ) {
				throw new NumberFormatException();
			}

			//----------------------------------------
			// finish buy/sell
			if ( itemQuantityInStock==0 || (itemQuantityInStock+operationAmount*amount)<0 ) {
				//insufficient memory
				System.out.println("Insufficient stock");
				return false;
				//-----------------------------------------
			}else {
				//enough memory
				itemQuantityInStock += operationAmount*amount;
				if (operation=="sell") {
					while (operationAmount>0) {
						//subtract amounts from stock
						String[] data = expires.pop();
						int stock = Integer.valueOf(data[0]);

						if (stock>operationAmount) {
							stock-=operationAmount;
							//push it back into the stack
							String[] val = { String.valueOf(stock), data[1] };
							expires.push(val);
							break;
						}else {
							operationAmount-=stock;
						}
					}
					//checked enough of the stack
				} else if (operation=="buy") {
					//just add item data
					String[] val = { String.valueOf(operationAmount), String.valueOf(setExpiryDate(scanner)) };
					expires.push(val); //push value to memory
				}
				sortStack();
				return true;
			}
			//----------------------------------------
		} catch (NumberFormatException x) {
			System.out.println("Invalid quantity...");
		}
		return false; // unsuccessful
	}
	//------------------------------------------------------------------------------------
	/**
	 * Sort stack from oldest item to newest
	 */
	//------------------------------------------------------------------------------------
	private void sortStack() {
		//sorts the stack from oldest expiry date to newest
		Stack<String[]> tmp = new Stack<String[]>(); 
		while(!expires.isEmpty()) {
			//for each element in stack
			String[] data = expires.pop(); //first element
			LocalDate date = convertTextToDate(data[1]);

			//rearrange tmp stack
			while( (!tmp.isEmpty()) && (convertTextToDate(tmp.peek()[1])).isBefore(date) ) { 
				// while tmp stack's top date is older than the current popped date 
				// pop top data and add back onto the org stack
				expires.push(tmp.pop()); 
			}
			// add data onto tmp stack 
			tmp.push(data); 
		}
		expires = tmp; //set as the stack
	}
	//------------------------------------------------------------------------------------
	/** Get the String values of the data
	 *	@return the String of the values
	 */
	//------------------------------------------------------------------------------------
	public String toString() {

		String returnString = item.toString();
		returnString += " qty: " + itemQuantityInStock;
		return returnString;
	}

}