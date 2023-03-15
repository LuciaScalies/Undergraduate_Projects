/*
 TrainLJS.java					Author: AMH, LJS
 Creates a TrainLJS object and defines its methods 
 */

public class TrainLJS
{
	private int myID; //Train ID Number
	private int myPassengerCars; //Number of Passenger Cars
	private int myCargoCars; //Number of Cargo Cars
	private int myMaxWeight; //Heaviest the train has ever been in tons
	private final int ENGINE; //Stores the weight of an engine
	private final int PASSENGER_CAR; //Stores the weight of a passenger car
	private final int CARGO_CAR; //Stores the weight of a cargo car
	private final int CAR_CAPACITY; //Stores the max number of 
		//passengers a car can carry
		
	//initializes instance data and constants
	public TrainLJS(int id, int passengerCars, int cargoCars)
	{
		myID = id;
		myPassengerCars = passengerCars;
		myCargoCars = cargoCars;
		ENGINE =  125;
		PASSENGER_CAR = 45;
		CARGO_CAR = 160;
		CAR_CAPACITY = 80;
		myMaxWeight = ENGINE + (myPassengerCars * PASSENGER_CAR) + (myCargoCars * 
			CARGO_CAR);
	}
	
	//adds int cars cargo cars to the train
	public void addCargo(int cars)
	{
		myCargoCars += cars;
		myMaxWeight = Math.max(ENGINE + (myPassengerCars * PASSENGER_CAR) + (myCargoCars * 
			CARGO_CAR), myMaxWeight);
	}
	
	//adds int cars passenger cars to the train
	public void addPassenger(int cars)
	{
		myPassengerCars += cars;
		myMaxWeight = Math.max(ENGINE + (myPassengerCars * PASSENGER_CAR) + (myCargoCars * 
			CARGO_CAR), myMaxWeight);
	}
	
	//calculates the train's passenger capacity and returns the result
	public int getCapacity()
	{
		return myPassengerCars * CAR_CAPACITY;
	}
	
	//returns the train's ID number to be accessed
	public int getID()
	{
		return myID;
	}
	
	//returns the current weight of the train
	public int getWeight()
	{
		return ENGINE + (myPassengerCars * PASSENGER_CAR) + (myCargoCars * 
			CARGO_CAR);
	}
	
	//returns the maximum recorded weight of the train
	public int getMaxWeight()
	{
		return myMaxWeight;
	}
	
	/*
	calculates and returns the percentage of the train's cars that are cargo 
	cars
	*/
	public double percentCargo()
	{
		int total = 1 + myPassengerCars + myCargoCars;
		return (double)myCargoCars/total;
	}
	
	/*
	calculates and returns the percentage of the train's cars that are passener 
	cars
	*/
	public double percentPassenger()
	{
		int total = 1 + myPassengerCars + myCargoCars;
		return (double)myPassengerCars/total;
	}
	
	//removes int cars cargo cars from the train
	public void removeCargo(int cars)
	{
		myCargoCars -= cars;
	}

	//removes int cars passenger cars from the train
	public void removePassenger(int cars)
	{
		myPassengerCars -= cars;
	}
	
	//defines the toString() method
	public String toString()
	{
		return "Train " + myID + ": " + myPassengerCars + " passenger cars, " + 
			myCargoCars + " cargo cars\n\tTotal train weight: " + (ENGINE + 
			(myPassengerCars * PASSENGER_CAR) + (myCargoCars * CARGO_CAR)) + 
			" tons";
	}
}