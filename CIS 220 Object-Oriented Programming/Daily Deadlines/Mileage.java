import java.util.Scanner;

public class Mileage
{
	public static void main(String[] args)
	{
		
		//11374 feet is ___ miles.
		//11374 feet is also ___ miles and ___ feet.
		//variables to store needed information
		//principle: assign types in a way that is sensible for the
		//nature of the data being stored, NOT in a way that reflects
		//how we intent to compute with that data
		//int numFeet = 11374;
		
		//get input
		Scanner scan = new Scanner(System.in);
		
		System.out.println("How many feet are you travelling?");
		int numFeet = scan.nextInt();
		//after readint in an int or a double, still have a return on input
		//read past it to be ready for more input
		scan.nextLine();
		
		System.out.println("What is your destination?");
		String destination = scan.nextLine();
		
		//do calculations
		int feetPerMile = 5280;
		double miles = numFeet/((double)feetPerMile);
		
		//report results
		System.out.println(destination + " is " + miles + " miles away.");
		System.out.println(destination + " is also " + numFeet/feetPerMile +
			" miles and " + numFeet%feetPerMile + " feet away.");
		/*
		int wholeMiles = numFeet/feetPerMile;
		int remFeet = numFeet%feetPerMile;
		System.out.println(numFeet + " feet is also " + wholeMiles +
			" miles and " + remFeet + " feet.");
		*/
	}
}