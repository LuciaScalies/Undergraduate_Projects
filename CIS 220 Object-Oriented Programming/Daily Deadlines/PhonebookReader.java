/*
  PhonebookReader.java       Author: Scalies, AMH

  Each name is a single token, each phone number is a single token, and each 
  line of the file contains a name token and a phone number token in that order.
  Every name in the file is unique. Prompts the user, via the command line, for 
  the name of a person. Assumes they enter a name as a single token. Given that 
  name, reports back the phone number for that person if it appears in the 
  provided text file. Does not make any assumptions about the capitalization 
  that the user might employ when entering the name. If the name does not appear
  in the file, reports back to the user that the name is not listed.
*/
//import statements
import java.io.*;
import java.util.Scanner;

public class PhonebookReader
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		String username = ""; //stores user input
		System.out.println("Enter a name:");
		username = input.nextLine(); //name to be searched
		boolean found = false; //true if username was found
		boolean passed = false; //true if the file has passed the possible 
								//location for the name
		try
		{
			Scanner phonescan = new Scanner(new File("phonebook.txt"));
			while(phonescan.hasNext() && !found && !passed)
			{
				//checks for username and reads in file, placing it on the phone
				//number
				String name = phonescan.next();
				String number = phonescan.next();
				System.out.println("Scanning...");
				if(name.compareToIgnoreCase(username) <= 0)
				{
					if(name.equalsIgnoreCase(username)) 
					{
						System.out.println(number.toUpperCase());
						found = true; //sets found
					}
				}
				else
				{
					passed = true;
				}
			}
		} catch (IOException e) {System.out.println(e);};
		if(!found)
		{
			System.out.println(username.toUpperCase() + " was not found in " + 
								"phone book.");
		}
	}
}