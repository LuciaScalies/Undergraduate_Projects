/*
 Library.java				Author: AMH
 Implement a library program for tracking progress through various
 books being read.
*/

import java.text.NumberFormat;

public class Library
{
	public static void main(String[] args) 
	{
		NumberFormat pfmt = NumberFormat.getPercentInstance();
		
		Book javaBook = new Book("Java Software Solutions", 806);
		System.out.println(javaBook);
		System.out.println();
		
		javaBook.read(12);
		System.out.println(javaBook);
		System.out.println("Percent of " + javaBook.getTitle() + " completed: "
			+ pfmt.format(javaBook.getPercentRead()));
		System.out.println("Average pages read per day in " + javaBook.getTitle()
			+ ": " + javaBook.getPagesPerDay());
		System.out.println();

		javaBook.read(9);
		javaBook.read(16);
		System.out.println(javaBook);
		System.out.println("Percent of " + javaBook.getTitle() + " completed: "
			+ pfmt.format(javaBook.getPercentRead()));
		System.out.println("Average pages read per day in " + javaBook.getTitle()
			+ ": " + javaBook.getPagesPerDay());
		System.out.println();
		
		javaBook.restart();
		javaBook.read(9);
		javaBook.read(16);
		System.out.println(javaBook);
		System.out.println("Percent of " + javaBook.getTitle() + " completed: "
			+ pfmt.format(javaBook.getPercentRead()));
		System.out.println("Average pages read per day in " + javaBook.getTitle()
			+ ": " + javaBook.getPagesPerDay());
		System.out.println();
		
		Book compBook = new Book("Competitive Programming 3", 423, 33, 2);
		System.out.println(compBook);
		System.out.println("Percent of " + compBook.getTitle() + " completed: "
			+ pfmt.format(compBook.getPercentRead()));
		System.out.println("Average pages read per day in " + compBook.getTitle()
			+ ": " + compBook.getPagesPerDay());
			
		Series mySeries = new Series("Exciting Series", 320);
		System.out.println(mySeries);
		mySeries.read(32);
		System.out.println(mySeries);
		mySeries.addVolume(250);
		System.out.println(mySeries);
	}
}