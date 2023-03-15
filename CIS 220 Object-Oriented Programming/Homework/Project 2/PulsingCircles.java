// Driver class for PulsingCircles example
// Author: AMH
import javax.swing.JFrame;
import java.util.Scanner;

public class PulsingCircles 
{
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Circles!");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		//Ask the user how many circles they want and store the information
		Scanner scan = new Scanner(System.in);
		int numCircles = 1;
		boolean intFound = false;
		
		//obtains and checks user input
		do
		{
			System.out.println("Enter the desired number of circles (1-20):");
			if(!scan.hasNextInt())
			{
				scan.nextLine();
				System.out.println("Invalid input was entered.");
			}
			else
			{
				int hold = scan.nextInt();
				scan.nextLine();
				if(hold > 0 && hold <= 20)
				{
					intFound = true;
					numCircles = hold;
				}
			}
		}while(!intFound);
		PulsingCirclesPanel panel = new PulsingCirclesPanel(numCircles);
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
   }
}