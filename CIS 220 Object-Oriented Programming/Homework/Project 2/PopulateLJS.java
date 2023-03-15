/*
 PopulateLJS.java					Author: AMH, Lucia Josephine Scalies
 Creates and displays the PopulateLJS GUI environment
 */

//imports Scanner class
import java.util.Scanner;
//imports GUI setup components
 import javax.swing.JFrame;
 
public class PopulateLJS
{
	//runs the GUI
	public static void main (String[] args)
	{
		//checks input and prints specific error messages if input is invalid
		int life = 0;
		double hunters = 0.0;
		int closeness = 0;
		boolean lifeClear = false;
		boolean huntersClear = false;
		boolean closenessClear = false;
		
		if(args.length == 3)
		{
			Scanner scan1 = new Scanner(args[0]);
			Scanner scan2 = new Scanner(args[1]);
			Scanner scan3 = new Scanner(args[2]);
			
			if(scan1.hasNextInt()) //checks is args[0] is an int
			{
				life = scan1.nextInt();
				if(life > 0) //checks if life is positive
				{
					lifeClear = true; //life passes all tests; clear to pass
				}
				else //Error 2
				{
					System.out.println("PopulateLJS Error 2: Negative or Zero "
									  + "Value entered for argument 1. Run ");
					System.out.println("program with a positive first integer " 
									  + "argument.");
					System.out.println();
				}
			}
			else //Error 1
			{
				System.out.println("PopulateLJS Error 1: The first argument " +
								   "either was not entered or was not an ");
				System.out.println("integer. Run program with a positive first " 
								  + "integer argument.");
				System.out.println();
			}
			scan1.close();

			if(scan2.hasNextDouble()) //checks if args[1] contains a double
			{
				hunters = scan2.nextDouble();
				if(hunters >= 0) //checks if hunters is positive or 0
				{
					huntersClear = true; //hunters passes all tests; clear to 
										 //pass
				}
				else //Error 6
				{
					System.out.println("PopulateLJS Error 6: Negative Value " +
									   "entered for argument 2. Run program " +
									   "with ");
					System.out.println("a positive or zero rational second " +
									   "argument.");
					System.out.println();
				}
			}
			else //Error 5
			{
				System.out.println("PopulateLJS Error 5: The second argument " +
								   "either was not entered or was not a ");
				System.out.println("rational number. Run program with a " + 
								   "positive or zero rational argument.");
				System.out.println();
			}

			scan2.close();
			
			if(scan3.hasNextInt()) //checks is args[2] is an int
			{
				closeness = scan3.nextInt();
				if(closeness > 0) //checks if closeness is positive
				{
					closenessClear = true; //closeness passes all tests; clear 
										   //to pass
				}
				else //Error 8
				{
					System.out.println("PopulateLJS Error 8: Negative or Zero "
									  + "Value entered for argument 3. Run ");
					System.out.println("program with a positive integer third " 
									  + "argument.");
					System.out.println();
				}
			}
			else //Error 7
			{
				System.out.println("PopulateLJS Error 7: The third argument " +
								   "either was not entered or was not an ");
				System.out.println("integer. Run program with a positive third " 
								  + "integer argument.");
				System.out.println();
			}
			scan3.close();
		}
		else
		{
			if(args.length > 3) //Error 3
			{
				System.out.println("PopulateLJS Error 3: Excessive quantity of "
								   + "arguments supplied. Please ");
				System.out.println("enter only 3 arguments.");
				System.out.println();
			}
			else //Error 4
			{
				System.out.println("PopulateLJS Error 4: Insufficient quantity "
									+ "of arguments supplied. Please ");
				System.out.println("enter 3 arguments.");
				System.out.println();
			}
		}
		
		//checks if all conditions for successful operation exist
		if(lifeClear && huntersClear && closenessClear)
		{
			JFrame frame = new JFrame ("Populate"); //creates the JFrame
			//exits program when closed
			frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			
			//creates the PopulatePanelLJS via its constructor
			PopulatePanelLJS panel = new PopulatePanelLJS(life, hunters, 
														 closeness);
			frame.getContentPane().add(panel);
			frame.pack();
			frame.setVisible(true);
		}
		else //final error message; provides example input for user
		{
			System.out.println("Example input: java PopulateLJS 20 0.25 40");
		}
	}
}