/*
 AnimalDJS.java					Author: AMH, Dominic Joseph Scalies
 Creates an AnimalDJS object. AnimalDJS objects are able to be drawn in a 
 graphics environment as circles, along with managing associated data about 
 their circular representation, manage their energy stored, determine their
 positions, and move throughout their environment without exiting it
 */

import java.awt.*;
import java.util.Random;
import java.text.DecimalFormat;

public class AnimalDJS
{
	protected int diameter, x, y; //circle size instance data
	protected Color color; //the circle's color
	protected double energy; //the AnimalDJS's current energy
	protected int winX, winY, limit; //The current environmental boundaries; the
									 //right edge of the screen, the bottom of 
									 //the screen, and the bottom of the button
	protected int stepsTaken; //the steps the AnimalDJS object has taken
	
	//Constructor initializes instance data and creates an AnimalDJS object
	public AnimalDJS (int upperX, int upperY, int maxX, int maxY, int pause)
	{
		x = upperX;
		y = upperY;
		winX = maxX;
		winY = maxY;
		limit = pause;
		diameter = 40;
		color = Color.green;
		energy = 1.0;
		stepsTaken = 0;
    }
	
	//Draws this AnimalDJS in the specified graphics context.
	public void draw (Graphics page)
	{
		page.setColor (color);
		page.fillOval (x, y, diameter, diameter);
		page.setColor (Color.black);
		DecimalFormat fmt = new DecimalFormat("0.00"); //creates DecimalFormat
		page.setFont(new Font("Arial", Font.BOLD, 10));
		page.drawString (fmt.format(energy), (x+10), (y+25));
	}
	
	/*
	  Moves the AnimalDJS object up, down, left, or right randomly, a unit equal
	  to the animal's radius referred to as a step. The method does nothing if 
	  the Animal has no energy or if the selected direction would take it 
	  outside the boundary of its environment.
	*/
	public void move()
	{
		//checks if the Animal has energy to move
		if(energy > 0)
		{
			//Generates and stores a random int from 0-3 inclusive 0 = up, 
			//1 = down, 2 = right, 3 = left
			Random generator = new Random();
			int direction = generator.nextInt(4);
			int radius = (int)((double)diameter/2);
			if(direction > 1) //executes if the Animal moves on the x-axis
			{
				//executes if a right step will not violate the boundaries
				if(direction == 2 && (x + diameter + radius <= winX))
				{
					energy -= 0.05; //subtracts energy
					x += radius; //steps right
				}
				else
				{
					//executes if a left step will not violate the boundaries
					if(direction == 3 && (x - radius >= 0))
					{
						energy -= 0.05; //subtracts energy
						x -= radius; //steps left
					}
				}
			}
			else //executes if the Animal moves on the y-axis
			{
				//executes if an up step will not violate the boundaries
				if(direction == 0 && (y - radius >= limit))
				{
					energy -= 0.05; //subtracts energy
					y -= radius; //steps up
				}
				else
				{
					//executes if a down step will not violate the boundaries
					if(direction == 1 && (y + diameter + radius <= winY))
					{
						energy -= 0.05; //subtracts energy
						y += radius; //steps down
					}
				}
			}
			energy = Math.max(energy, 0);
		}
		stepsTaken++;
	}
	
	/*
	  Takes an Animal as an argument and returns a boolean value if the two 
	  animals currently are colliding, defined as the overlapping of any of 
	  their pixels. Returns true if the Animals are colliding, otherwise returns
	  false
	*/
	public boolean collides(AnimalDJS fauna)
	{
		//determines the radius of each circle
		double radius = (double)diameter/2;
		double faunaRadius = (double)fauna.getDiameter()/2;
		//determine x half of the distance formula
		double halfA = Math.pow(((x + radius) - (fauna.getX() + (faunaRadius))),
								2.0);
		//determine y half of the distance formula
		double halfB = Math.pow(((y + radius) - (fauna.getY() + (faunaRadius))),
								2.0);
		//combine the two halves and complete the distance formula
		double distance = Math.sqrt(halfA + halfB);
		if(distance < (radius + faunaRadius) && energy > 0 && fauna.getEnergy() 
			> 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	  Takes an Animal as an argument and returns a double value representing the
	  distance between the two animals
	*/
	public double getDistance(AnimalDJS fauna)
	{
		//determines the radius of each circle
		double radius = (double)diameter/2;
		double faunaRadius = (double)fauna.getDiameter()/2;
		//determine x half of the distance formula
		double halfA = Math.pow(((x + radius) - (fauna.getX() + (faunaRadius))),
								2.0);
		//determine y half of the distance formula
		double halfB = Math.pow(((y + radius) - (fauna.getY() + (faunaRadius))),
								2.0);
		//combine the two halves and complete the distance formula
		double distance = Math.sqrt(halfA + halfB);
		return distance;
	}
	 
	/*
	  Takes and Animal object and an power value as arguments and moves the
	  Animal object a radius-distance away from the other animal in both the x
	  and y directions, so long as this fleeing does not violate the boundary
	  of the Animal's current environment. The Animal's energy is either 
	  increased or decreased by the power argument
	*/
	public void flee(AnimalDJS fauna, double power)
	{
		int radius = (int)((double)diameter/2);
		if(energy > 0)
		{
			if(x - fauna.getX() < 0) //checks if this is moving left
			{
				//calculates the leftward move and tests if it violates the
				//boundary
				if(x - radius >= 0)
				{
					x -= radius; //moves this left
				}
				else //this may move right
				{
					//calculates the rightward move and tests if it violates 
					//the boundary
					if(x + diameter + radius <= winX)
					{
						x += radius; //moves this right
					}
				}
			}
		}
		 
		if(energy > 0)
		{
			if(y - fauna.getY() < 0) //determine if fauna is above or below this
			{
				//calculates the upward move and tests if it violates the boundary
				if(y - radius >= limit)
				{
					y -= radius; //moves this up
				}
			}
			else
			{
				//calculates the downward move and tests if it violates the 
				//boundary
				if(y + diameter + radius <= winY)
				{
					y += radius; //moves this down
				}
			}
		}
		energy -= power;
		energy = Math.max(energy, 0);
	}
	
	//Diameter mutator.
	public void setDiameter (int size)
	{
		diameter = size;
	}
	
	//Color mutator.
	public void setColor (Color shade)
	{
		color = shade;
	}
	
	//button boundary mutator
	public void setLimit(int button)
	{
		limit = button;
	}
	
	//X mutator.
	public void setX (int upperX)
	{
		x = upperX;
	}
	
	//Y mutator.
	public void setY (int upperY)
	{
		y = upperY;
	}
	
	//Energy mutator
	public void addEnergy(double power)
	{
		energy += power;
	}
	
	//Diameter accessor.
	public int getDiameter ()
	{
		return diameter;
	}
	
	//Color accessor.
	public Color getColor ()
	{
		return color;
	}
	
	//button boundary accessor
	public int getLimit()
	{
		return limit;
	}
	
	//X accessor.
	public int getX ()
	{
		return x;
	}
	
	//Y accessor.
	public int getY ()
	{
		return y;
	}
	
	//Energy accessor.
	public double getEnergy()
	{
		return energy;
	}
	
	//window X size accessor
	public int getWinX()
	{
		return winX;
	}
	
	//window Y size accessor
	public int getWinY()
	{
		return winY;
	}
	
	//stepsTaken accessor
	public int getStepsTaken()
	{
		return stepsTaken;
	}
}