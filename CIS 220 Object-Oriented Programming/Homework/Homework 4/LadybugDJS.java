/*
LadybugDJS.java			Author: Holland-Minkley, Scalies

Homework 4				Sources: http://docs.oracle.com/javase/7/docs/api/
Accepts input from the user and constructs a Ladybug based on the input
*/

//imports classes 
import javax.swing.JApplet;
import java.util.Scanner;
import java.awt.*;

public class LadybugDJS extends JApplet
{	
	//declares constants
		private final int XMID = 250; //stores the midpoint of the x-axis
		private final int YMID = 200; //stores the midpoint of the y-axis
		private final int MAX = 250; //defines the max user input for the body
		private final int MIN = 50; //defines the min user input for the body
		
	//defines the paint class
	public void paint(Graphics page)
	{
	
		//creates and instantiates a Scanner Object
		Scanner input = new Scanner(System.in);
		
		//Declares variables
		int bodyWidth, bodyRadius, headRadius, spotWidth, spotRadius;
		
		/*
		  prompts the user to input the width of the Ladybug's body in pixels
		  and performs calculations necessary for constructing the ladybug
		*/
		System.out.println("Enter the desired ladybug width: ");
		bodyWidth = Math.max(Math.min(MAX, input.nextInt()), MIN);
		input.nextLine();
		bodyRadius = bodyWidth / 2;
		headRadius = bodyRadius / 2;
		spotWidth = bodyWidth / 10;
		spotRadius = spotWidth / 2;
		
		//Draws the Ladybug's body, head, wings, spots, and antennae
		page.setColor(Color.black); //colors body and head
		page.fillOval(XMID - (bodyRadius + headRadius), YMID - bodyRadius, 
			bodyWidth, bodyWidth); //draws body
		page.fillOval(XMID, YMID - headRadius, bodyRadius, bodyRadius); 
			//draws head
		page.setColor(Color.red); //colors wings
		page.fillArc(XMID - (bodyRadius + headRadius), YMID - bodyRadius, bodyWidth, bodyWidth,
			0, 135); //draws upper wing
		page.fillArc(XMID - (bodyRadius + headRadius), YMID - bodyRadius, bodyWidth, bodyWidth,
			0, -135); //draws lower wing
		page.setColor(Color.white); //colors spots
		page.fillOval(XMID - spotRadius, YMID - headRadius, spotWidth, 
			spotWidth); //top spot
		page.fillOval(XMID - spotRadius, (YMID + headRadius) - spotWidth, 
			spotWidth, spotWidth); //lower spot
		page.setColor(Color.black); //colors antennae
		page.drawArc(XMID + headRadius, YMID - bodyRadius, bodyRadius, 
			bodyRadius, 0, -90); //upper antennae
		page.drawArc(XMID + headRadius, YMID, bodyRadius, bodyRadius, 0, 90); 
			//lower antennae
	}
}