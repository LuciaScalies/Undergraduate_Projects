//**************************************************************************
//FeetToMilesConverter.java			Author: Holland-Minkley, Scalies
//Daily Deadlines February 8th, February 10th		
//Creates a feet to miles converter and prints the number of whole miles in
//a given number of feet. Afterwards, it prints the remaining feet. The
//initial number of feet is input by the user
//**************************************************************************

import java.util.Scanner;

public class FeetToMilesConverter
{
	public static void main(String[] args)
	{
		//Declares variables and constants and creates a Scanner object
		final int FEET_PER_MILE = 5280;
		int feet, remFeet;
		double miles;
		Scanner scan = new Scanner (System.in);
		
		//Asks the user to input a value for feet and stores the value
		System.out.print("Enter the number of feet: ");
		feet = scan.nextInt();
		
		//Calculates the conversion
		miles = (double)feet/FEET_PER_MILE;
		remFeet = feet%FEET_PER_MILE;
		
		//Prints user's value for feet and the conversions
		System.out.println(feet + " feet is " + miles + " miles.");
		System.out.println(feet + " is also " + (int)miles + " and " +
							remFeet + " feet.");
	}
}