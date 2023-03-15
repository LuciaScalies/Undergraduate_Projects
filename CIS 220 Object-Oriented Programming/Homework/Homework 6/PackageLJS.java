/*
 PackageLJS.java					Author: AMH, Dominic Joseph Scalies
 Creates a PackageLJS object. PackageLJS objects track their starting 
 and destination cities, the type of item being shipped, the current 
 location of the package, how far the package has travelled, and how 
 many legs of its journey it has travelled.
 */

import java.text.DecimalFormat;
 
public class PackageLJS
{
	private String myStartCity;	//the package's starting city
	private String myEndCity;	//the package's destination
	private String myItem;	//the item being shipped
	private String myCurLocation;	//the package's current location
	private double myMiles;	//how far the package has travelled in miles
	private int myLegs;	//the number of legs the package has travelled
	private double myAvgDist; //average distance traveled in a leg
	private double myShortLeg; //the distance traveled on the shortest leg of the 
							//journey
	private DecimalFormat fmt; //formats outputs
	
	//Constructor: Initializes the package's starting city, destination, item, 
	//current location, miles travelled, and legs travelled
	public PackageLJS(String start, String end, String item)
	{
		myStartCity = start;
		myEndCity = end;
		myItem = item;
		myCurLocation = start;
		myMiles = 0.0;
		myLegs = 0;
		myAvgDist = 0;
		myShortLeg = Double.MAX_VALUE;
		fmt = new DecimalFormat("0.##");
	}
	
	/*
	  The package traverses a leg of its journey; updates the package's current 
	  location, shortest leg, miles traveled, legs traveled, and average 
	  distance traveled
	*/
	public void traverseLeg(String city, double mileage)
	{
		myCurLocation = city;
		myShortLeg = Math.min(myShortLeg, mileage);
		myMiles += mileage;
		myLegs++;
		myAvgDist = (double)myMiles/myLegs;
	}
	
	//Returns the average distance traveled in a leg
	public double getAverageDist()
	{
		return myAvgDist;
	}
	
	//Returns the name of the item in the package
	public String getItem()
	{
		return myItem;
	}
	
	//Returns the length of the package's shortest leg
	public double getShortestLeg()
	{
		return myShortLeg;
	}
	
	//Checks if the package has reached its destination and returns the result
	public boolean atDest()
	{
		return myCurLocation.equalsIgnoreCase(myEndCity);
	}
	
	//Transposes the start and end cities of a package
	public void bounce()
	{
		String temp = myStartCity;
		myStartCity = myEndCity;
		myEndCity = temp;
	}
	
	//defines the toString() method for PackageLJS and reports its current state
	public String toString()
	{
		return myItem + ": In " + myCurLocation + ", " + fmt.format(myMiles) + 
			" miles " + "traveled between " + myStartCity + " and " + myEndCity 
			+ " in " + myLegs + " legs";
	}
}