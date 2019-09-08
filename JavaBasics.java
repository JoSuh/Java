import java.util.Scanner;	//program uses class Scanner
import java.text.DecimalFormat; //import this for formatting decimals


public class JavaBasics {

	public static void main(String[] args) {
		// main method
		
		
		//____________________________________________________________________________________
		
	// DECIMAL FORMAT DIFFERENCES
		float fNumber1 = 2.3466789f;
		float fNumber2 = 2.1f;
		float fNumber3 = 2.0f;
		
		DecimalFormat form = new DecimalFormat ("#.##"); //will ignore numbers with less decimal places
		//DecimalFormat form = new DecimalFormat ("#.00"); // will insert '0's to put numbers to assigned decimal places
		System.out.println("fnumber1= " + form.format(fNumber1));
		System.out.println("fnumber2= " + form.format(fNumber2));
		System.out.println("fnumber3= " + form.format(fNumber3));

		//____________________________________________________________________________________
		
		
	//SCANNER NEXT CHARACTER
		//Lesson-2: getting input a character using scanner
			//input is a reference variable
			//it refers an object of Scanner Class
			//Shortly you can say: input is an object of Scanner class
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a letter (a-z or A-Z)");
		String str = input.nextLine();
		char chr = str.charAt(0);
		System.out.println("You entered " + chr);

		//____________________________________________________________________________________
		/*
		Mathematical expression with precedence
		The precedence order:
			pre operator 
			( )
			/  *  % 
			+ -
			=  +=  -=  /= *= 
			post operator
		//Mathematical expression with precedence
		int x = 10;
		int y = 5;
		int a = 2;
		int b = 3;
		int z = a + b * x – x / 2 + ( y + 1 ) * ( a – 1 );
		System.out.println("z = "+z);
		int number = 5;
		number = number + 1; same number+=1;//short form
		//number = number - 3; same number -=3;//short form
		System.out.println("number = "+number);
		 */
		//____________________________________________________________________________________

	
	//Lesson-5: pre/post increment or decremet operator prec
		int x = 10;
		//int y = 5;
		System.out.println(1+2+ ++x);
		System.out.println(x); //x's value gets changed because of '++x'

	//Lesson-6: 
		int z = 10;
		int y = 5;
		// '+1' to x before operation, '+1' to y after operation.
		System.out.println(++z + y++); //11 + 5 = 16
		System.out.println("z = " + z); //11
		System.out.println("y = " + y); //6
	
	//Lesson-7: 
		int i = 10, j = 5;
		int k = i++ + ++j;
		System.out.println("i = " + i); //11
		System.out.println("j = " + j); //6
		System.out.println("k = " + k); // 10 + (5+1) = 16
	
	//Lesson-8: 
		int a = 10;
		int b = a++ + ++a;
				// the 'a' here is already altered by the previous 'a++'.
		System.out.println("a = " + a); //(11 + 1)
		System.out.println("b = " + b); // 10 + (11+1) = 22

	//Lesson-9: 
		int c = 10;
		int d = ++c + c++;
		System.out.println("c = " + c); //(11 + 1)
		System.out.println("d = " + d); // (10+1) + 11 = 22
		

	//Lesson-10: 
		int s = 2;
		System.out.println(++s + s++); //(3 + 3)

		//____________________________________________________________________________________
		
		
		
		
	
	} // end of main
	
}// end of class
