/*
 PredatorDJS.java					Author: AMH, Dominic Joseph Scalies
 Creates a PredatorDJS object. PredatorDJS objects are special cases of 
 AnimalDJS objects, and are able to be drawn in a graphics environment as 
 circles, along with managing associated data about their circular 
 representation, manage their energy stored, determine their positions, and move 
 throughout their environment without exiting it
 */

import java.awt.*;
import java.util.Random;
 
public class PredatorDJS extends AnimalDJS
{
	//Constructor: Sets up PredatorDJS object using AnimalDJS's constructor and
	//altering instance data to reflect PredatorDJS properties
	public PredatorDJS(int upperX, int upperY, int maxX, int maxY, int pause)
	{
		super(upperX, upperY, maxX, maxY, pause);
		color = Color.yellow;
		energy = 1.5;
	}
	
	/*
	  Moves the PredatorDJS object. The predator might move both horizontally 
	  and vertically, only horizontally or vertically, or not move at all. 
	  There is a one in four chance the predator will move left; if it does not 
	  move left, then with one in four chance it should move right. Independent 
	  of those choices, with one in four chance the predator should decide 
	  whether to move up; if it does not move up, then with one in four chance 
	  it should move down. Predators lose 0.05 energy per step, losing no energy
	  if they do not move at all and losing 0.1 energy if they move both 
	  horizontally and vertically; predators are constrained to remain inside 
	  their established environment in the same way animals in general are.
	*/
	public void move()
	{
		if(energy > 0)
		{
			Random generator = new Random();
			int radius = (int)((double)diameter/2);
			//checks if the Predator should move to the left
			if((generator.nextInt(4) == 0) && (x - radius >= 0) && (energy > 0))
			{
				x -= radius; //moves the Predator left
				energy -= .05; //extracts movement's energy cost
			}
			else
			{
				//checks if the predator should move to the right
				if((generator.nextInt(4) == 0) && 
					(x + diameter + radius <= winX) && (energy > 0))
				{
					x += radius; //moves the Predator right
					energy -= .05; //extracts the movement's energy cost
				}
			}
			//checks if the Predator should move up
			if((generator.nextInt(4) == 0) && (y - radius >= limit) && 
				(energy > 0))
			{
				y -= radius; //moves the Predator up
				energy -= .05; //extracts the movement's energy cost
			}
			else
			{
				//checks if the predator should move down
				if((generator.nextInt(4) == 0) && 
					(y + diameter + radius <= winY) && (energy > 0))
				{
					y += radius; //moves the Predator down
					energy -= .05; //extracts the movement's energy cost
				}
			}
		}
		energy = Math.max(energy, 0);
		stepsTaken++;
	}
}