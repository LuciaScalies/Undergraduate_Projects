//**************************************************************************
//Credits.java			Author: Scalies
//Daily Deadlines February 5th, Edited February 8th
//Stores the number of credits taken and prints a sentence using that value
//Adds the number of credits to be taken in the current semester to the value
//of credits stored and prints the new total
//**************************************************************************

public class Credits
{
	//-------------------------------------------------------------------
	//Main method declared variables and prints the associated sentence.
	//-------------------------------------------------------------------
	
	public static void main(String[] args)
	{
		double creditHours = 16.0;
		
		System.out.println("I have taken " + creditHours + " credits total.");
		System.out.println("After this semester I will have taken " + 
							(creditHours + 16.0) + " credits total.");
	}
}