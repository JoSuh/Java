 /****************************************************************************
 *
 * Created by: Heejo Suh
 * Created on: Mar 2018
 * Created for: learning
 * 
 * This program uses the stack 'Triangle',
 * to calculate values of input triangle
 *
 ****************************************************************************/


import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


//****************************************************************************
public class TriangleCalculator {
	//This class tests the stack
	
	//-----------------------
	public static void main(String[] args) throws Exception {
		//instantiate triangle given the input and calculate values
		
		Scanner scanner = new Scanner(System.in);
		String input = "", choice = "";
		int angleCount = 0;

		//create list
       	HashMap<String, Double> inputValues = new HashMap<String, Double>();
		List<String> inputTypes = new ArrayList<>();

		System.out.println("\nSA: side A, 	SB: side B, 	SC: side C\nAA: angle A, 	AB: angle B, 	AC: angle C\n----------------------------------------------------");
		//--------------------------------------------------------------------------
		//Get correct inputs -------------------------------------------------------
		while (inputTypes.size() < 3) {
			//not have exited program
			
			//----------------------------------------
			while (choice=="") {
				//get choice
				System.out.println("\nWhat value would you like to put in?");
				choice = scanner.nextLine().toUpperCase();
				
				if (inputValues.get(choice)==null) {
					//check if overlapping input
					//inputValues.get(choice);
					//if (String.valueOf(choice.charAt(0))=="A") {
					if (String.valueOf(choice.charAt(0))=="A") {
						//check for angle count
						if (angleCount == 2) {
							//if past maximum
							System.out.println("ERROR: You can't put in more than two angles!");
							choice="";
						} else {
							//add angle count
							angleCount= angleCount+1;
						}
					} else if (!choice.equals("SA") && !choice.equals("SB") && !choice.equals("SC") && !choice.equals("AA") && !choice.equals("AB") && !choice.equals("AC")) {
						//if invalid input
						System.out.println("ERROR: Invalid input!");
						choice="";
					}
				}else { 
					System.out.println("ERROR: Overlapping input!");
					choice="";
				}
			}
			
			
			//----------------------------------------
			while (choice!="") {
				//get input
				System.out.println("Insert the value: ");
				
				try {
					//check if response is a number
					input = scanner.nextLine();
					double inputNumber = Double.valueOf(input);
					if (inputNumber < 0 ) {
						System.out.println("ERROR: Insert a valid number!");
					} else {
						//insert into dictionary
						inputValues.put(choice, inputNumber);
						inputTypes.add(choice);
						choice="";
					}	
					
				}catch (IllegalArgumentException x) { 
					System.out.println("ERROR: Insert a number!");
				}
			}
		} 
		
		

		//instantiate Triangle
		Triangle customTriangle = new Triangle(inputTypes.get(0), inputValues.get(inputTypes.get(0)), inputTypes.get(1), inputValues.get(inputTypes.get(1)), inputTypes.get(2), inputValues.get(inputTypes.get(2)));
		//check validity
		if (customTriangle.IsTriangleValid() == true) {
			//if valid triangle

			//Name
			System.out.println("Triangle: " + customTriangle.GetName() + " triangle");
			//Area
			System.out.println("Area: " + customTriangle.GetArea());
			customTriangle.printTriangleHeight();
			customTriangle.printLargestInscribedCircle();
			customTriangle.printAreaOfTheCircumcircleOfTheTriangle();
			
		} else {
			//error
			System.out.println("\nInvalid triangle!");
			
		}
			
	}
}
