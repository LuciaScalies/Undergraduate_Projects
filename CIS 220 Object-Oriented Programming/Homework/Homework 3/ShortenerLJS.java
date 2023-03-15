/*
ShortenerDJS.java			Author: Holland-Minkley, Scalies

Homework 3
Accepts a URL from the user and shortens the URL submitted
*/

import java.util.Scanner;
import java.util.Random;
import java.text.NumberFormat;

public class ShortenerLJS
{
	public static void main(String[] args)
	{
		/*
		Instantiates a Scanner, a Random object, and a PercentInstance 
		NumberFormat object
		*/
		Scanner getURL = new Scanner(System.in);
		Random num = new Random();
		NumberFormat fmt = NumberFormat.getPercentInstance();
		
		//Declares variables related to the URL and the shortening process
		String address, baseURL, subdomain, twoChar, cleanTwoChar, shortURL;
		int firstDot, startSecondHalf, subStart, slashReplace, target;
		int code1, code2;
		String slashReplacement = "";
		String numVal = "";
		double percent;
		
		//Prompts the user for the URL and stores the URL. 
		System.out.println("Enter the URL you would like to shorten: ");
		address = getURL.nextLine();
		getURL.close();
		/*
		Prints a message to the user to inform them that the program is running.
		Obtains the baseURL by removing the http:// or https:// and prints the 
		result
		*/
		System.out.println("Processing...");
		//baseURL = address.substring(7);
		baseURL = address.substring(address.indexOf("/") + 2);
		System.out.println("\tGetting base url: " + baseURL);
		
		//Determines, stores, and prints the subdomain
		firstDot = baseURL.indexOf('.');
		subdomain = baseURL.substring(0, firstDot);
		System.out.println("\tGetting subdomain: " + subdomain);
		
		/*
		Finds the second half of the baseURL and stores the starting index.
		Begins the process for constructing the two character substring by 
		generating a random number to serve as the index of the start of the new
		substring
		*/
		startSecondHalf = baseURL.length() / 2;
		target = startSecondHalf + (baseURL.length() % 2);
		subStart = num.nextInt(startSecondHalf - 1) + target;
		twoChar = baseURL.substring(subStart, (subStart+2));
		System.out.println("\tGetting two character substring: " + twoChar);
		
		/*
		Replaces any incidence of a '/' character in the two character
		substring with a random number between 3 and 7
		*/
		slashReplace = num.nextInt(5) + 3;
		slashReplacement = slashReplacement + slashReplace;
		cleanTwoChar = twoChar.replace("/", slashReplacement);
		System.out.println("\tGetting cleansed two character substring: " + 
			cleanTwoChar);
		
		/*
		Converts the characters in the clean two-character sequence into Unicode
		and then prints the smaller of the two values
		*/
		code1 = (twoChar.toUpperCase()).charAt(0);
		code2 = (twoChar.toUpperCase()).charAt(1);
		numVal = numVal + (Math.min(code1, code2));
		System.out.println("\tGetting numerical value: " + numVal);
		
		/*
		Completes the construction of the shortened URL by combining a prefix, 
		the subdomain, the numerical value, and the cleansed two character
		sequence followed by a "/" and prints the new shortened URL. Also 
		calculates and prints by what percentage the original URL's length was 
		reduced.
		*/
		shortURL = "http://short.wj/" + subdomain + numVal + cleanTwoChar + "/";
		percent = 1 - (((double)shortURL.length())/address.length());
		System.out.println();
		System.out.println("Shortened url: " + shortURL);
		System.out.println("Percent shortened by: " + 
			fmt.format(percent));
	}
}