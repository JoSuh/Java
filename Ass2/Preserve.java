
import java.util.Formatter;
import java.util.Scanner;


/**
 * 
 * File:			Preserve.java
 * Description:		This class is the struct for Preserves
 * Student Name: 	Jo Suh
 * Student Number:  040943462
 * Assignment:		2
 * Course:			CST8130_Data Structures
 * @author Jo Suh
 * @version 2.0
 * @since 1.0
 *
 */
public class Preserve extends FoodItem {

	/** The jar size. */
	private float jarSize;

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Instantiates a new Preserve.
	 */
	protected Preserve() {}

	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * @return String of the values in the inventory
	 * @see FoodItem#toString()
	 */
	@Override
	public String toString(){
		String returnString = super.toString() + " size: " + jarSize + "mL";
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
		writer.format("\n%f", jarSize);
	}
	// -------------------------------------------------------------------------------------------------------------------------
	/**
	 * Adds a Preserve item
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
				try {
					if (!scanner.hasNextLine()) {
						return false;
					}
					jarSize = Float.valueOf(scanner.nextLine());
					return true;
				} catch (NumberFormatException x) {
					return false;
				}
			}else {
				//Reading inputs from user
				String userInput;
				while (true) {
					System.out.print("Enter the size of the jar in millilitres: ");// must be a positive number
					userInput= scanner.nextLine(); // use nextLine to get the whitespaces
					if (userInput.replace(" ", "").isEmpty()) {
						System.out.println("You must enter the size of the jar");
						continue; //ask for input again
					}
					try {
						float temp= Float.valueOf(userInput);
						if (temp < 0) {
							throw new NumberFormatException();
						}else {
							jarSize = temp;
							return true;
						}
					} catch (NumberFormatException x) {
						System.out.print("Invalid entry");
						continue;
					}
				}
			}
		}

		return false;
	}


}