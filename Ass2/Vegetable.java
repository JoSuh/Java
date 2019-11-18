
import java.util.Formatter;
import java.util.Scanner;



/**
 * 
 * File:			Vegetable.java
 * Description:		This class is the struct for Vegetables
 * Student Name: 	Jo Suh
 * Student Number:  040943462
 * Assignment:		2
 * Course:			CST8130_Data Structures
 * @author Jo Suh
 * @version 2.0
 * @since 1.0
 *
 */
public class Vegetable extends FoodItem {

	/** The farm name. */
	private String farmName;

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Instantiates a new Vegetable.
	 */
	protected Vegetable() {}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * @return String of the values in the inventory
	 * @see FoodItem#toString()
	 */
	@Override
	public String toString(){
		String returnString = super.toString() + " farm supplier: " + farmName;
		return returnString;
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Output item to the writer
	 * just the data portion of it, no description needed
	 *
	 * @param writer the writer
	 */
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("\n%s", farmName);
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds a Vegetable item
	 *
	 * @param scanner from main, sither Scanner(System.in) or Scanner(File)
	 * @return true, if successfully added Item
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		//Adds item info and returns a boolean value of whether the operation was successful or not

		if (super.addItem(scanner, fromFile)) {
			//if successfully read the basic Food Item inputs
			if (fromFile) {
				//Reading from file
				if (!scanner.hasNextLine()) {
					return false;
				}
				farmName = scanner.nextLine();
				return true;
			}else {
				//Reading inputs from user
				String userInput;
				while (true) {
					System.out.print("Enter the name of the farm supplier: "); // can be any input
					userInput= scanner.nextLine(); // use nextLine to get the whitespaces
					if (userInput.replace(" ", "").isEmpty()) {
						System.out.println("You must enter the name of the farm supplier");
						continue; //ask for input again
					}
					farmName = userInput;
					return true;
				}
			}
		}

		return false;
	}


}