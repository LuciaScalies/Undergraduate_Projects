//********************************************************************
//  CirclePractice.java       Author: Sarver, Scalies
//
//  Makes a circle
//********************************************************************

import javax.swing.JApplet;
import java.awt.*;
import java.util.Scanner;

public class CirclePractice extends JApplet
{
	public void paint(Graphics page)
	{
		//Establish the center of the circle
		final int XCENTER = 200;
		final int YCENTER = 150;
		
		//Test axis
		//page.drawLine(XCENTER, 0, XCENTER, 300);
		//page.drawLine(0, YCENTER, 400, YCENTER);
		
		/*
		Prompts the user for the radius of the initial circle and the ratio of
		the smaller circles to the larger circle
		*/
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a Radius: ");
		int radius = input.nextInt();
		input.nextLine();
		System.out.print("Enter the ratio: 1:");
		int ratio = input.nextInt();
		input.nextLine();
		
		/*
			Creates the concentric circles based on user input
		*/
		page.drawOval(XCENTER-radius, YCENTER-radius, 2*radius, 2*radius);
		page.setColor(Color.red);
		page.fillOval(XCENTER-(radius/ratio), YCENTER-(radius/ratio), 
			((radius*2)/ratio), (radius*2)/ratio);
		page.setColor(Color.green);
		page.fillOval(XCENTER-(radius/(ratio*2)), YCENTER-(radius/(ratio*2)), 
			((radius*2)/(ratio*2)), ((radius*2)/(ratio*2/ratio)));
		page.setColor(Color.magenta);
		page.fillOval(XCENTER-(radius/(ratio*4)), YCENTER-(radius/(ratio*4)), 
			((radius*2)/(ratio*4)), ((radius*2)/(ratio*4)));
		
	}
}