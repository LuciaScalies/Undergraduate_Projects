/*
 Series.java				Author: AMH
 Implements a series that behaves like a book with multiple volumes; volumes can
 be added during the course of reading
*/

public class Series extends Book
{
	private int numVolumes; //number of volumes in a Series
	
	//Constructor: given a series name and number of pages, assumes series start
	//with one volume
	public Series(String seriesName, bookPages)
	{
		super(seriesName, bookPages); //call constructor of the super class on
									  //these arguments
		numVolumes = 1;
	}
	
	//method to add a volume with a given number of pages to the series
	public void addVolume(int bookPages)
	{
		numVolumes++;
		numPages += bookPages;
	}
}