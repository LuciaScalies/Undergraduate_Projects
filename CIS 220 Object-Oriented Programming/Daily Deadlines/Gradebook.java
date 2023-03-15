/* 
 Gradebook.java		Author: AMH, DJS, CIS 220 01 Sp16
 Creates an Gradebook of Assignments storing the possible points, points earned,
 and topic of each Assignment
*/

import java.util.Scanner;

public class Gradebook
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("How many students are in your class?");
		int numStudents = scan.nextInt();
		scan.nextLine();
		
		System.out.println("How many assignments in your class?");
		int numGrades = scan.nextInt();
		scan.nextLine();
		
		int[][] grades = new int[numStudents][numGrades];
		
		for(int i = 0; i < grades.length; i++)
		{
			for(int j = 0; j < grades[i].length; j++)
			{
				System.out.println("Enter the grade for Student " + (i + 1) +
								   " on Homework " + (j + 1));
				grades[i][j] = scan.nextInt();
				scan.nextLine();
			}
		}
		
		for(int i = 0; i < grades.length; i++) //i is the current student
		{
			int total = 0;
			for(int j = 0; j < grades[i].length; j++)
			{
				total += grades[i][j];
			}
			System.out.println("Student " + (i+1) + " average: " + 
				total/(double)grades[0].length);
		}
	}
}