import java.util.Scanner;

public class Grades
{
	private Assignments[] grades;
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("How many grades are there?");
		int numberOfGrades = scan.nextInt();
		scan.nextLine();
		grades = new Assignments[numberOfGrades];
		
		for(int i = 0; i < grades.length; i++)
		{
			System.out.println("Enter topic: ");
			String t = scan.nextLine();
			System.out.println("Enter possible points: ");
			int p = scan.nextInt();
			scan.nextLine();
			System.out.println("Enter points earned: ");
			scan.nextInt();
			scan.nextLine();
			grades[i] = new Assignments(p, e, t);
		}
		
		int totalEarned = 0;
		int totalPossible = 0;
		//read in five grades from user
		for(int i = 0; i < grades.length; i++)
		{
			totalPossible += grades[i].getPossible();
			totalEarned += grades[i].getEarned();
		}
		
		//print out the average
		System.out.println("Average: " + total/assignments);
		
		//Prompts the user for a grade they have received
		System.out.println("Enter a grade you may have recieved: ");
		int score = scan.nextInt();
		scan.nextLine();
		boolean found = false;
		
		//print out which homeworks recieved that grade
		for(int i = 0; i < grades.length; i++)
		{
			//report if grades[i] had grade score
			if(grades[i] == score)
			{
				System.out.println("Recieved " + score + " on Homework " + 
									(i+1));
				found = true;
			}
		}
		if(!found)
		{
			System.out.println("You did not recieve the entered grade on any " +
								"homework.");
		}
	}
}