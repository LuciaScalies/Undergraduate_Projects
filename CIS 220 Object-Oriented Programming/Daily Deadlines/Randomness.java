/*
  Create a new, empty Java program named Randomness.java and within it, complete
  the following tasks without looking at any code you have previously written or
  any documentation besides the exam reference Java API files. 
  1. Prompt the user for an integer number of values that they would like to 
     generate and store in an array.
  2. Ensure that the code does not crash if non-integer values are entered. 
     If a non-integer value or a value that would not be a valid number of items
	 to store in an array is entered, re-prompt the user as many times as 
	 necessary until a valid input is entered.
  3. Create a two-dimensional array to hold a set of integer values. Let the 
     number of values that the user requested be N; size your array to be N by 
	 4. You will use this array to store four sets of random values, with each 
	 set having N values.
  4. Fill your two-dimensional array with random values. One set should draw the
     N random values from the range {0 .. N}, one set should draw the N random 
	 values from the range {0 .. N*2}, one set should draw the N random values 
	 from the range {0 .. N/2}, and one set should draw the N random values from
	 the range {0 .. N/4}.
  5. Prove a print method that prints out each of the four sets; use this to 
     debug your code by ensuring that the correct number of values are 
	 generated, over the correct ranges.
  6. For each of the four sets, compute and print out the percent of the values 
     that are unique. That is, what percent of the items in the set are 
	 different from all of the other values in that set (they do not need to be 
	 unique from values in the other three sets). Computing this will require 
	 looking at each item in turn and scanning the rest of the set looking for 
	 any matching values. Be careful in doing so that you do not compare the 
	 value against itself, but do consider that a matching value may be either 
	 before or after it in the array.
*/

import java.util.Random;
import java.util.Scanner;

public class Randomness
{
	public static void main(String[] args)
	{
		Random gen = new Random();
		Scanner scan = new Scanner(System.in);
		int n = 0;
		boolean valid = false;
		
		System.out.println("How many values would you like to generate?");
		do
		{
			System.out.println("Enter an integer value: ");
			if(scan.hasNextInt())
			{
				n = scan.nextInt();
				scan.nextLine();
				if(n > 0)
				{
					valid = true;
				}
				else
				{
					valid = false;
					n = 0;
				}
			}
			else
			{
				valid = false;
				scan.nextLine();
			}
		}
		while(!valid);
		
		int[][] values = new int[4][n];
		for(int i = 0; i < values[0].length; i++)
		{
			values[0][i] = gen.nextInt(n+1);
		}
		for(int i = 0; i < values[1].length; i++)
		{
			values[1][i] = gen.nextInt(n*2 + 1);
		}
		for(int i = 0; i < values[2].length; i++)
		{
			values[2][i] = gen.nextInt((n/2) + 1);
		}
		for(int i = 0; i < values[3].length; i++)
		{
			values[3][i] = gen.nextInt((n/4) + 1);
		}
		
		for(int i = 0; i < values.length; i++)
		{
			for(int j = 0; j < values[i].length; j++)
			{
				System.out.print(values[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < values.length; i++)
		{
			int count
			for(int j = 0; j < values[i].length; j++)
			{
			}
		}
	}
}