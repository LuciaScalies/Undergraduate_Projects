//Dominic Scalies
//Sources: Java API
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//DataPoint - object class with instance data for each column in source data
//file, a constructor, a display method, getters, and a method for computing
//distance metric
class DataPoint {
	private String datetime;
	private int hour, season, holiday, workingday, weather, humidity, count;
	private double temp, atemp,	windspeed;
	
	//constructor for DataPoint
	public DataPoint(String d, int s, int ho, int wD, int we, double t, double aT,
			int hu, double wi, int c) {
		datetime = d;
		hour = Integer.parseInt(d.substring((d.indexOf(" ")+1), d.indexOf(":")));
		season = s;	// categorical
		holiday = ho;	// categorical
		workingday = wD;	// categorical
		weather = we;	// categorical
		temp = t;	// numerical
		atemp = aT;	// numerical
		humidity = hu;	// numerical
		windspeed = wi;	// numerical
		count = c;
	}
	
	//DataPoint getter methods
	public String getDatetime() {return datetime;}
	public int getHour() {return hour;}
	public int getSeason() {return season;}
	public int getHoliday() {return holiday;}
	public int getWorkingDay() {return workingday;}
	public int getWeather() {return weather;}
	public int getHumidity() {return humidity;}
	public int getCount() {return count;}
	public double getTemp() {return temp;}
	public double getAtemp() {return atemp;}
	public double getWindspeed() {return windspeed;}
	
	public double distance(DataPoint day2) {
		// CODE REQUIRED HERE FOR CALCULATING DISTANCE BETWEEN TWO DATA POINTS
		//strategy: define the math for individual items first as separate fields, and go
		//forward from there
		//overall equation is the square root of the sum of each individually calculated 
		//square
		//initialization of categorical values to equation defaults
		int s = 1; //season
		int ho = 1; //holiday
		int wD = 1; //workingday
		int wE = 1; //weather
		
		//categorical equivalence checks
		if(season == day2.getSeason()) s = 0;
		if(holiday == day2.getHoliday()) ho = 0;
		if(workingday == day2.getWorkingDay()) wD = 0;
		if(weather == day2.getWeather()) wE = 0;
		
		//numerical base differences before squaring
		//hour subtraction checks
		int time = hour - day2.getHour();
		if(time > 12 || time < -12)
		{
			time = 24 - Math.abs(hour - day2.getHour());
		}
		int hu = humidity - day2.getHumidity(); //humidity
		double t = temp - day2.getTemp(); //temp
		double aT = atemp - day2.getAtemp(); //atemp
		double wS = windspeed - day2.getWindspeed(); //windspeed
		
		//return result of the equation
		return Math.sqrt(Math.pow(s, 2) + Math.pow(ho, 2) + Math.pow(wD, 2) + 
				Math.pow(wE, 2) + Math.pow(hu, 2) + Math.pow(t, 2) + Math.pow(aT, 2) + 
				Math.pow(wS, 2) + Math.pow(time, 2));
	}
	
	//returns the date and time and the number of rentals 
	public String toString() {
		return datetime + ": " + count + " rentals";
	}
}

// Link - a single link object with its data being a DataPoint object
class Link {
	public DataPoint data;              // data item (key)
	public Link next;              // next link in list

	//Link constructor
	public Link(DataPoint d) {
		data = d;
		next = null;
	}
		
	//prints this Link
	public void displayLink() {
		System.out.print(data);
	}
}

// DataList - a collection of DataPoints stored in a Linked List arrangement
// Supports insertion of DataPoints into the list, a kNN list processing
// method operation that determines for a DataPoint and a value of k what the
// predicted number of bikes needed for the given DataPoint, and a runTest
// method that takes in a list of DataPoints with known bike demand and
// computes the total sum of squared error of kNN against the list of DataPoints
// using the given k.
// Note that delete and find operations are not required.
class DataList {
	private Link first;  //the first Link in the DataList
	private int numItems; //the number of items in the list

	//DataList constructor
	public DataList() {
		first = null;
		numItems = 0;
	}
	
	//getter method for numItems
	public int getNum() {return numItems;}

	// insert DataPoint in list in constant time
	public void insert(DataPoint d) {
		Link newLink = new Link(d); //creates a new Link
		newLink.next = first; 		//makes newLink's next point to the old first Link in 
									//the DataList
		first = newLink; 			//makes first point to newLink
		numItems++; 				//increments numItems to reflect the new total
   }

   // calculate kNN prediction of number of bikes needed on newDay considering
   // given k number of nearest neighbors
	public int kNN(DataPoint newDay, int k) {
		DataPoint[] days = new DataPoint[k]; //an array of k data points
		double[] distances = new double[k]; //an array of k distances
		
		for(int i = 0; i < k; i++) //initializes distances to max_double
		{
			distances[i] = Double.MAX_VALUE;
		}
		Link current = first;
		double currDist = 0;
		while(current != null) //loops through the linked list
		{
			currDist = newDay.distance(current.data); //calculates distance between newDay and 
													  //the Link
			//upon completion, all k smallest distances and their corresponding dataPoints
			//will be stored in relative sorted order in distances[] and days[] respectively
			for(int i = 0; i < k; i++) 				//loop through distances
			{
				if(currDist < distances[i])			//check if distances
				{
					for(int j = k-1; j>i; j--)
					{
						distances[j] = distances[j-1];
						days[j] = days[j-1];
					}
					distances[i] = currDist;
					days[i] = current.data;
					i = k;
				}
			}
			current = current.next;
		}
		//averages and rounds the counts of the k closest integers
		int sum = 0;
		for(int i = 0; i < k; i++)
		{
			sum += days[i].getCount();
		}
		return (int)Math.round(((double)sum)/k);
	}

	// calculate total sum of squared error across all labeled items in
	// training against kNN prediction with given k
	public long runTest(DataList training, int k) {
		Link current = first;
		long sum = 0;
		int actual = 0;
		int diff;
		while(current != null) //loops through the linked list
		{
			int predict = training.kNN(current.data, k);
			actual = current.data.getCount();
			diff = predict - actual;
			sum += (long)(Math.pow(diff, 2));
			current = current.next;
		}
		return sum;
	}
	
	//prints out list in reverse chronological order (established inherently by 
	//DataList's implementation)
	public void displayList() {
		System.out.print("List (first-->last): ");
		Link current = first;
		while(current != null) {
			current.displayLink();
			System.out.println("\t");
			current = current.next;
		}
	}
}

//driver class
public class BikeDemandkNN {
	public static void main (String[] args) throws IOException {
		//long sumOfAvgSum = 0;
		//int iterations = 100; //test 1
		//int iterations = 500; //test 2
		//int iterations = 1000;  //test 3
		//data collection loop
		//for(int i = 0; i < iterations; i++)
		//{
			Scanner fileScan = new Scanner(new File("sharingData.csv"));
			fileScan.nextLine();	// read past header line
		
			DataList training = new DataList(); //creates training list
			DataList testing = new DataList();  //creates testing list
		
			Random gen = new Random(); 			//creates RNG
		
		
			//reads in data
			while (fileScan.hasNext()){
				String rowData = fileScan.nextLine();
				Scanner dayScan = new Scanner(rowData);
				dayScan.useDelimiter(",");
			
				String datetime;
				int season, holiday, workingday, weather, humidity, count;
				double temp, atemp,	windspeed;
			
				datetime = dayScan.next();
				season = dayScan.nextInt();
				holiday = dayScan.nextInt();
				workingday = dayScan.nextInt();
				weather = dayScan.nextInt();
				temp = dayScan.nextDouble();
				atemp = dayScan.nextDouble();
				humidity = dayScan.nextInt();
				windspeed = dayScan.nextDouble();
				count = dayScan.nextInt();
			
				DataPoint tempDay = new DataPoint(datetime, season, holiday, workingday,
						weather, temp, atemp, humidity, windspeed, count);

				// for development purposes, start by only inserting in training
				// list to start to be able to check that all items are being
				// correctly inserted
				//tested insert and it works properly, inserting all 10312 items
				//training.insert(tempDay);

				// once insertion has been tested, the following code inserts
				// data items into the testing list with 1 in 100 probability
				if (gen.nextInt(100)==0) {
					testing.insert(tempDay);
				} else {
					training.insert(tempDay);
				}

			
				dayScan.close();
			}
			fileScan.close();

			//System.out.println(training.getNum() + " items inserted in training list");
			//System.out.println(testing.getNum() + " items inserted in testing list");

			// for development purposes, start by only displaying the list to confirm
			// items are inserted properly and can be iterated over by the displayList
			// method
			training.displayList();
		
			// hard coded test data for steps 3 and 4 of assignment
			DataPoint testDay = new DataPoint("1/19/2011 0:00", 1, 0, 1, 2, 9.02, 13.635, 93, 0, 0);
			System.out.println("Prediction 1: " + training.kNN(testDay, 5));
			testDay = new DataPoint("1/19/2011 10:00", 1, 0, 1, 2, 10.66, 13.635, 93, 8.9981, 0);
			System.out.println("Prediction 2: " + training.kNN(testDay, 5));
			testDay = new DataPoint("4/19/2011 12:00", 2, 0, 1, 2, 22.14, 25.76, 64, 6.0032, 0);
			System.out.println("Prediction 3: " + training.kNN(testDay, 5));
			testDay = new DataPoint("4/19/2011 22:00", 2, 0, 1, 1, 19.68, 23.485, 77,  6.0032, 0);
			System.out.println("Prediction 4: " + training.kNN(testDay, 5));
			testDay = new DataPoint("7/19/2011 4:00", 3, 0, 1, 1, 29.52, 34.09, 70, 8.9981, 0);
			System.out.println("Prediction 5: " + training.kNN(testDay, 5));
			testDay = new DataPoint("7/19/2011 13:00", 3, 0, 1, 1, 35.26, 41.665, 53, 7.0015, 0);
			System.out.println("Prediction 6: " + training.kNN(testDay, 5));

			// method call to compare held out test data against set of training
			// data using k
			// using k of 1
			// long error = testing.runTest(training, 1); // test 1, 4
			// using k of 2
			// long error = testing.runTest(training, 2); //test 1, 4
			// using k of 3
			// long error = testing.runTest(training, 3); //test 1, 4
			// using k of 4
			// long error = testing.runTest(training, 4); //test 1, 4
			// using k of 5
			// long error = testing.runTest(training, 5); //Step 5, test 1, 4, 5
			// using k of 6
			// long error = testing.runTest(training, 6); //test 1, 2, 4, 5
			// using k of 7
			// long error = testing.runTest(training, 7); //test 1, 2, 4, 5, 6
			// using k of 8
			// long error = testing.runTest(training, 8); //test 1, 2, 3, 4, 5
			// using k of 9
			 long error = testing.runTest(training, 9); //test 1, 2, 4, 5, 6
			// using k of 10
			// long error = testing.runTest(training, 10); //test 1, 2, 3, 4
			System.out.println("Total sum of squared error: " + error);
			// update to print out average sum of squared error as well
			System.out.println("Average sum of squared error: " + (error/training.getNum()));
			//sumOfAvgSum += error/training.getNum();
			//System.out.println((i+1) + "/" + iterations);
		//}
		//System.out.println("Representative average sum: " + (sumOfAvgSum/((long)iterations)));
	}
}