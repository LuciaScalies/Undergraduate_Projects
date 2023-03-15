/*
 RailroadDJS.java					Author: AMH, DJS
 Implements a railroad yard with two trains passing through it
 */

import java.text.NumberFormat;

public class RailroadDJS 
{
	public static void main (String[] args) 
	{
		NumberFormat fmt = NumberFormat.getPercentInstance();
		
		// create an "early" train with id number 413 that has three
		// passenger cars and five cargo cars
		TrainDJS earlyTrain = new TrainDJS(413, 3, 5);
		System.out.println(earlyTrain);	// print out status of early train
		System.out.println();
		
		earlyTrain.addPassenger(2);	// add two passenger cars to early train
		System.out.println(earlyTrain);	// print out status of early train
		earlyTrain.removeCargo(1);	// remove a cargo car from early train
		System.out.println(earlyTrain);	// print out status of early train
		earlyTrain.addPassenger(1);	// add one passenger car to early train
		System.out.println(earlyTrain);	// print out status of early train
		// print out the number of passengers the early train can carry
		System.out.println("Train " + earlyTrain.getID() + " can carry " +
			earlyTrain.getCapacity() + " passengers.");
		// print out the percent of the early train that is passenger cars and
		// the percent of the early train that is cargo cars
		System.out.println("Train " + earlyTrain.getID() + " is " +
			fmt.format(earlyTrain.percentPassenger()) + " passenger cars and " +
			fmt.format(earlyTrain.percentCargo()) + " cargo cars.");
		System.out.println();
		
		// create a "late" train with id number 111 that has six passenger
		// cars and one cargo car
		TrainDJS lateTrain = new TrainDJS(111, 6, 1);
		lateTrain.addCargo(3);	// add three cargo cars to late train
		lateTrain.removePassenger(2);	// remove two passenger cars from late train
		lateTrain.removeCargo(1);	// remove one cargo car from late train
		lateTrain.addPassenger(4);	// add two passenger cars to late train
		System.out.println(lateTrain);	// print out status of late train
		// print out the number of passengers the late train can carry
		System.out.println("Train " + lateTrain.getID() + " can carry " +
			lateTrain.getCapacity() + " passengers.");
		// print out the percent of the late train that is passenger cars and
		// the percent of the late train that is cargo cars
		System.out.println("Train " + lateTrain.getID() + " is " +
			fmt.format(lateTrain.percentPassenger()) + " passenger cars and " +
			fmt.format(lateTrain.percentCargo()) + " cargo cars.");
		System.out.println();
		
		// print out the current weight of the early train and the largest
		// weight the early train has had since it was created
		System.out.println("Train " + earlyTrain.getID() + " current weight " +
			earlyTrain.getWeight() + " tons, maximum weight recorded " +
			earlyTrain.getMaxWeight() + " tons");
		// print out the current weight of the late train and the largest
		// weight the late train has had since it was created
		System.out.println("Train " + lateTrain.getID() + " current weight " +
			lateTrain.getWeight() + " tons, maximum weight recorded " +
			lateTrain.getMaxWeight() + " tons");
		System.out.println();
		
		earlyTrain.addPassenger(1);	// add one passenger car to early train
		earlyTrain.addCargo(5);	// add five passenger cars to early train
		System.out.println(earlyTrain);	// print out status of early train
		earlyTrain.removeCargo(6);	// remove six cargo cars from early train
		System.out.println(earlyTrain);	// print out status of early train
		// print out the number of passengers the early train can carry
		System.out.println("Train " + earlyTrain.getID() + " can carry " +
			earlyTrain.getCapacity() + " passengers.");
		// print out the percent of the early train that is passenger cars and
		// the percent of the early train that is cargo cars
		System.out.println("Train " + earlyTrain.getID() + " is " +
			fmt.format(earlyTrain.percentPassenger()) + " passenger cars and " +
			fmt.format(earlyTrain.percentCargo()) + " cargo cars.");
		// print out the current weight of the early train and the largest
		// weight the early train has had since it was created
		System.out.println("Train " + earlyTrain.getID() + " current weight " +
			earlyTrain.getWeight() + " tons, maximum weight recorded " +
			earlyTrain.getMaxWeight() + " tons");				
	}
}