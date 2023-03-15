/*
EssayDJS.java			Author: Holland-Minkley, Scalies

Homework 2
Performs calculations about the user's progress on an essay. Prompts the user
for input related to the essay and uses the input throughout the program to
calculate various values related to the user's progress on the essay
*/

import java.util.Scanner;

public class EssayDJS
{
	public static void main(String[] args)
	{
		//instantiates a Scanner object
		Scanner input = new Scanner(System.in);
		
		//Prompts the user for the word requirement and the topic of their essay
		System.out.println("How many words should your essay be?");
		int wordReq = input.nextInt();
		input.nextLine();
		System.out.println("What is the topic of your essay?");
		String topic = input.nextLine();
		
		/*
		Outputs text about the word requirement and the topic of the user's
		essay
		*/
		System.out.println("You have been assigned to write a " + wordReq + 
			" word essay about " + topic + ".");
		System.out.println();
		
		/*
		Inquires about the user's progress and calculates the remaining
		number of words and the user's percentage of completion
		*/
		System.out.println("How many words have you currently written?");
		int currentWords = input.nextInt();
		input.nextLine();
		int remainingWords = wordReq - currentWords;
		double completion = ((double)currentWords / wordReq) * 100.0;
		
		/*
		Outputs the user's current progress and the number of remaining words
		and the user's percentage of completion
		*/
		System.out.println("You have written " + currentWords + " words and "
			+ "have " + remainingWords + " remaining to write.");
		System.out.println("Your essay is " + completion + "% complete.");
		System.out.println();
		
		/*
		Prompts the user for and stores information about the number of hours
		spent on the user's essay, and calculates the users rate of words per
		hour and the remaining time if the rate is maintained
		*/
		System.out.println("How many hours have you been writing your essay "
			+ "for?");
		double hours = input.nextDouble();
		input.nextLine();
		double wordsPerHour = currentWords/hours;
		double hoursLeft = remainingWords/wordsPerHour;
		
		//Outputs the result of the above calculations
		System.out.println("You are writing at a pace of " + wordsPerHour
			+ " words per hour.");
		System.out.println("At that pace you will be done in " + hoursLeft 
			+ " hours.");
	}
}