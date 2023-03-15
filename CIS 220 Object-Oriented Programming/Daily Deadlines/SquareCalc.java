//**************************************************************************
//SquareCalc.java			Author: Scalies
//Daily Deadlines February 10th		
//Prompts the user for the length of the sides of a square and the units 
//used for measuring. Reports the perimeter of the square, the area of the 
//square, and the ratio between the two. Test your program completely.
//**************************************************************************

//imports the Scanner class
import java.util.Scanner;

public class SquareCalc
{
	public static void main(String[] args)
	{
		//Instantiates a Scanner object and declares variables
		Scanner ask = new Scanner(System.in);
		double side, perimeter, area, ratio;
		String units;
		
		/*
		Prompts the user for the necessary input and stores the length of the
		square's side and the type of units used to measure
		*/
		System.out.print("Enter the length of a square's side: ");
		side = ask.nextDouble();
		ask.nextLine();
		System.out.print("Enter the unit of measure: ");
		units = ask.nextLine();
		
		//Performs calculations
		perimeter = 4 * side;
		area = side * side;
		ratio = perimeter/area;
		
		//Prints the values stored and the results of the calculations
		System.out.println("The side of your square is " + side + " " + units
			+ " long.");
		System.out.println("The square's perimeter is: " + perimeter + " " +
			units);
		System.out.println("The square's area is: " + area + " square " + 
			units); 
		System.out.println("The ratio of the square's perimeter to the " +
			"square's area is: " + ratio);
	}
}