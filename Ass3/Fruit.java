
import java.util.Scanner;



/**
 * File:			Fruits.java
 * Description:		This class is the struct for Fruits
 * Student Name: 	Jo Suh
 * Student Number:  040943462
 * Assignment:		3
 * Course:			CST8130_Data Structures
 * @author Jo Suh
 * @version 3.0
 * @since 1.0
 *
 */
public class Fruit extends FoodItem {

	/** The orchard name. */
	private String orchardName;


	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Instantiates a new Fruit.
	 */
	protected Fruit() {}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * @return String of the values in the inventory
	 * @see FoodItem#toString()
	 */
	@Override
	public String toString(){
		String returnString = super.toString() + " orchard supplier: " + orchardName;
		return returnString;
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds a Fruit item
	 *
	 * @param scanner from main, either Scanner(System.in) or Scanner(File)
	 * @return true, if successfully added Item
	 */
	public boolean addItem(Scanner scanner) {
		//Adds item info and returns a boolean value of whether the operation was successful or not

		if (super.addItem(scanner)) {
			//if successfully read the basic Food Item inputs
			//Reading inputs from user
			String userInput;
			while (true) {
				System.out.print("Enter the name of the orchard supplier: "); // can be any input
				userInput= scanner.nextLine(); // use nextLine to get the whitespaces
				if (userInput.replace(" ", "").isEmpty()) {
					System.out.println("You must enter the name of the orchard supplier");
					continue; //ask for input again
				}
				orchardName = userInput;
				return true;
			}
		}

		return false;
	}


}