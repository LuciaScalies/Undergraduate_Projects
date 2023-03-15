/*
  TripReader.java       Author: Scalies, O'Malley, AMH
  
  Reports the total number of miles traveled across the entire trip, and the 
  average speed maintained across all of the days. Prints out the order in which
  the cities were visited.
*/

import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

public class TripReader
{
	public static void main(String[] args)
	{
		int count = 0; //number of items scanned for averages
		int milage = 0; //number of miles traveled
		double velocity = 0.0; //total of speed values used for calculating the
							   //average
		try
		{
			Scanner reader = new Scanner(new File("trip.txt"));
			reader.nextLine();

			while(reader.hasNext())
			{
				reader.next();
				reader.next();
				milage += Integer.parseInt(reader.next());
				velocity += Double.parseDouble(reader.next());
				count++;
			}
		}catch (IOException e) {System.out.println(e);};

		System.out.println("Average milage: " + milage);
		System.out.println("Average speed: " + (velocity/count));

	}
}