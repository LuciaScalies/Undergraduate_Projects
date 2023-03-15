/*
 Book.java				Author: AMH
 Describes a book object that a reader may make progress reading
 across a series of days.
*/

public class Book
{
	protected String title;	// title of the book
	protected int numPages;	// total number of pages in book
	protected int currPage;	// page currently reading, assume books start
							// numbering pages at 1
	protected int days;		// number of days spent reading
	protected int totalRead;  //total number of pages read through all passes 
							//through the book
	
	// Constructor for a book just being started
	public Book(String bookTitle, int bookPages) 
	{
		title = bookTitle;
		numPages = bookPages;
		currPage = 1;
		totalRead = 0;
		days = 0;
	}
	
	// Constructor for a book object in progress
	public Book(String bookTitle, int bookPages, int current, int daysReading) 
	{
		title = bookTitle;
		numPages = bookPages;
		currPage = current;
		days = daysReading;
	}
	
	// update book to indicate pages have been read, assume indicated
	// pages were read on a single day
	public void read(int pages) 
	{
		currPage += pages;
		totalRead += pages;
		days++;
	}
	
	// return title of the book
	public String getTitle() 
	{
		return title;
	}
	
	// return number of page currently reading
	public int getCurrPage() 
	{
		return currPage;
	}
	
	//return number of pages read across all passes through the book
	public int getTotalRead()
	{
		return totalRead;
	}
	
	// return number of pages remaining in book
	public int getRemaining() 
	{
		return numPages-currPage;
	}
	
	// return the percentage of the book that has been read, reported as a
	// value between 0.0 and 1.0
	public double getPercentRead() 
	{
		return currPage/(double)numPages;
	}
	
	// return the number of pages read per day on average
	public double getPagesPerDay() 
	{
		return totalRead/(double)days;
	}
	
	//allows the reader to restart a book without losing their pacing info
	public void restart()
	{
		currPage = 1;
	}
	
	// print status of book summarizing progress through book
	public String toString() 
	{
		return "On page " + currPage + " of " + numPages + " of " + title;
	}
}