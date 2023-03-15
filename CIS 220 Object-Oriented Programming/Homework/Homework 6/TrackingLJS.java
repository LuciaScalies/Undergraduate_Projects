/*
 TrackingLJS.java					Author: AMH, Lucia Josephine Scalies
 Driver class for the PackageLJS object
 */

import java.text.DecimalFormat;
 
 public class TrackingLJS
 {
	public static void main(String[] args)
	{
		//Creates a DecimalFormat object
		DecimalFormat fmt = new DecimalFormat("0.##");
		
		//Creates a PackageLJS object representing a book being shipped from 
		//San Diego to Dayton and prints its status
		PackageLJS book = new PackageLJS("San Diego", "Dayton", "Book");
		System.out.println(book.toString());
		
		//package travels to Las Vegas ans prints its new state
		book.traverseLeg("Las Vegas", 332);
		System.out.println(book.toString());
		
		//package travels to Denver, Omaha, and Columbus and prints its state
		book.traverseLeg("Denver", 748);
		book.traverseLeg("Omaha", 537);
		book.traverseLeg("Columbus", 786);
		System.out.println(book.toString());
		
		//Prints the average distance traveled by the book
		System.out.println(book.getItem() + " average distance per leg: " + 
			fmt.format(book.getAverageDist()));
		
		//Prints the length of the journey's shortest leg
		System.out.println(book.getItem() + " shortest leg: " + 
			fmt.format(book.getShortestLeg()));
			
		//Prints whether the package has arrived at its destination
		System.out.println(book.getItem() + " is at destination: " + 
			book.atDest());
		System.out.println();
		
		//Sends the book to Dayton and prints its status
		book.traverseLeg("Dayton", 71.1);
		System.out.println(book.toString());
		
		//Prints the average distance traveled by the book
		System.out.println(book.getItem() + " average distance per leg: " + 
			fmt.format(book.getAverageDist()));
		
		//Prints the length of the journey's shortest leg
		System.out.println(book.getItem() + " shortest leg: " + 
			fmt.format(book.getShortestLeg()));
			
		//Prints whether the package has arrived at its destination
		System.out.println(book.getItem() + " is at destination: " + 
			book.atDest());
		System.out.println();
		
		//Creates a PackageLJS object representing a a phone being shipped from
		//Atlanta to Boise
		PackageLJS phone = new PackageLJS("Atlanta", "Boise", "Phone");
		
		//Ships the phone to St. Louis, Kansas City, Denver, and Santa Fe and 
		//prints its status
		phone.traverseLeg("St. Louis", 565);
		phone.traverseLeg("Kansas City", 248);
		phone.traverseLeg("Denver", 605);
		phone.traverseLeg("Santa Fe", 392);
		System.out.println(phone.toString());
		
		//Prints the average distance traveled by the phone
		System.out.println(phone.getItem() + " average distance per leg: " + 
			fmt.format(phone.getAverageDist()));
		
		//Prints the length of the journey's shortest leg
		System.out.println(phone.getItem() + " shortest leg: " + 
			fmt.format(phone.getShortestLeg()));
		
		//Prints whether the package has arrived at its destination
		System.out.println(phone.getItem() + " is at destination: " + 
			phone.atDest());
		System.out.println();
		
		//Bounces the phone and prints its status
		phone.bounce();
		System.out.println(phone.toString());
		
		//Sends the phone to Dallas and Austin and prints its status
		phone.traverseLeg("Dallas", 641);
		phone.traverseLeg("Austin", 195);
		System.out.println(phone.toString());
		
		//Bounces the phone and prints its status
		phone.bounce();
		System.out.println(phone.toString());
		
		//Sends the phone to Oklahoma City, bounces the phone, sends the phone 
		//to Memphis, and prints the phone's status
		phone.traverseLeg("Oklahoma City", 389);
		phone.bounce();
		phone.traverseLeg("Memphis", 467);
		System.out.println(phone.toString());
		
		//Prints the average distance traveled by the phone
		System.out.println(phone.getItem() + " average distance per leg: " + 
			fmt.format(phone.getAverageDist()));
		
		//Prints the length of the journey's shortest leg
		System.out.println(phone.getItem() + " shortest leg: " + 
			fmt.format(phone.getShortestLeg()));
		
		//Prints whether the package has arrived at its destination
		System.out.println(phone.getItem() + " is at destination: " + 
			phone.atDest());
		System.out.println();
		
		//Sends the phone to Atlanta and prints its status
		phone.traverseLeg("Atlanta", 383);
		System.out.println(phone.toString());
		
		//Prints the average distance traveled by the phone
		System.out.println(phone.getItem() + " average distance per leg: " + 
			fmt.format(phone.getAverageDist()));
		
		//Prints the length of the journey's shortest leg
		System.out.println(phone.getItem() + " shortest leg: " + 
			fmt.format(phone.getShortestLeg()));
		
		//Prints whether the package has arrived at its destination
		System.out.println(phone.getItem() + " is at destination: " + 
			phone.atDest());
		System.out.println();
	}
 }