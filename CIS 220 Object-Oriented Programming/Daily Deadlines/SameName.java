//********************************************************************
//  SameName.java       Author: Lucia Scalies
//
//  Prompts the user for their name and returns feedback based on 
//  if the user has the same name as the programmer
//********************************************************************

import java.util.Scanner;

public class SameName
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String programmer = "Lucia"; //Sets the programmers name to be checked
		
		//Prompts the user and reads their input
		System.out.println("What is your first name?");
		String username = input.nextLine();
		
		//checks the user's input against a condition
		if(programmer.equalsIgnoreCase(username))
		{
			System.out.println("You have the same name as the programmer!" + 
				"\nLife is full of little coincidences!");
		}
		else
		{
			System.out.println("You have a different name than the programmer!" 
				+ "\nCongratulations on being unique!");
		}
		
		int index = -1;
		while (!(index > 0 && index <= username.length()))
		{
			System.out.println("Enter an integer index:");
			if(input.hasNextInt())
			{
				index = input.nextInt();
			}
			input.nextLine();
		}
		input.close();
		//loop has exited, so index must be valid
		System.out.println(username.charAt(index));
	}
}