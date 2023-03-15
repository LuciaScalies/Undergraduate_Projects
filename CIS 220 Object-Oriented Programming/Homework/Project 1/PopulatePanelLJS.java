/*
 PopulatePanelLJS.java					Author: AMH, Lucia Josephineine Scalies
 Sets up the GUI environment, as well as the AnimalLJS and PredatorLJS objects
 used in PopulateLJS.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
 
public class PopulatePanelLJS extends JPanel
{
	private AnimalLJS first; //the first AnimalLJS object
	private AnimalLJS second;  //the second AnimalLJS object
	private AnimalLJS third; //the third AnimalLJS object
	private JButton step; //the Step button
	private int count; //counts the steps in the program
	private int timesShown; //how often the final Dialogue box has appeared
	
	//Constructor: Sets up PopulatePanelLJS with the necessary components
	public PopulatePanelLJS()
	{
		count = 0;
		timesShown = 0;
		//create step button
		step = new JButton("Step");
		step.addActionListener (new StepListener());
		
		//Creates a Random number generator
		Random gen = new Random();
		
		//creates the AnimalLJS objects in random locations
		if(gen.nextInt(3) != 2) //determines if Animal 1 is a Predator
		{
			first = new AnimalLJS(gen.nextInt(161), gen.nextInt(110)+51, 200, 
									200);
		}
		else
		{
			first = new PredatorLJS(gen.nextInt(161), gen.nextInt(110)+51, 200, 
									200);
		}
		
		if(gen.nextInt(3) != 2) //determines if Animal 2 is a Predator
		{
			second = new AnimalLJS(gen.nextInt(161), gen.nextInt(110)+51, 200, 
									200);
		}
		else
		{
			second = new PredatorLJS(gen.nextInt(161), gen.nextInt(110)+51, 200, 
									200);
		}
		if(gen.nextInt(3) != 2) //determines if Animal 1 is a Predator
		{
			third = new AnimalLJS(gen.nextInt(161), gen.nextInt(110)+51, 200, 
									200);
		}
		else
		{
			third = new PredatorLJS(gen.nextInt(161), gen.nextInt(110)+51, 200, 
									200);
		}
		
		//adds components to the window
		add(step);
				
		setPreferredSize(new Dimension(200, 200)); //sets preferred window size
		setBackground(Color.black); //sets background color to black
	}
	
	//Creates the Animals by asking them to draw themselves
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		first.draw(page);
		second.draw(page);
		third.draw(page);
	}
	
	private class StepListener implements ActionListener
	{
		//Moves the Animal objects
		public void actionPerformed (ActionEvent event)
		{
			count++;
			first.move(51);
			second.move(51);
			third.move(51);
			Random number = new Random();
			double transfer = 0.0;
			boolean isFirstAlive, isSecondAlive, isThirdAlive;
			//sets states of existence for Animals
			if(first.getEnergy() > 0)
			{
				isFirstAlive = true;
			}
			else
			{
				isFirstAlive = false;
				first.setEnergy(0);
			}
			
			if(second.getEnergy() > 0)
			{
				isSecondAlive = true;
			}
			else
			{
				isSecondAlive = false;
				second.setEnergy(0);
			}
			
			if(third.getEnergy() > 0)
			{
				isThirdAlive = true;
			}
			else
			{
				isThirdAlive = false;
				third.setEnergy(0);
			}
			
			//checks if first collides with second
			if(first.collides(second) && isFirstAlive && isSecondAlive)
			{
				//generates the collsion's energy
				transfer = (number.nextInt(8) + 1)/100.0;
				//determines if either of the Animal objects involved is a 
				//Predator
				if(first instanceof PredatorLJS || second instanceof 
					PredatorLJS)
				{
					transfer *= 1.5; //multiplies the energy by 1.5
				}
				
				//first gains energy, second loses energy
				if(number.nextInt(2) == 0)
				{
					first.flee(second, transfer, 51);
					second.flee(first, (transfer * -1), 51);
				}
				else //first loses energy, second gains energy
				{
					first.flee(second,(transfer * -1), 51);
					second.flee(first, transfer, 51);
				}
			}
			
			//checks if first collides with third
			if(first.collides(third) && isFirstAlive && isThirdAlive)
			{
				//generates the collsion's energy
				transfer = (number.nextInt(8) + 1)/100.0;
				//determines if either of the Animal objects involved is a 
				//Predator
				if(first instanceof PredatorLJS || third instanceof PredatorLJS)
				{
					transfer *= 1.5; //multiplies the energy by 1.5
				}
				
				//first gains energy, third loses energy
				if(number.nextInt(2) == 0)
				{
					first.flee(third, transfer, 51);
					third.flee(first, (transfer * -1), 51);
				}
				else //first loses energy, third gains energy
				{
					first.flee(third,(transfer * -1), 51);
					third.flee(first, transfer, 51);
				}
			}
			
			//checks if second collides with third
			if(second.collides(third) && isSecondAlive && isThirdAlive)
			{
				//generates the collsion's energy
				transfer = (number.nextInt(8) + 1)/100.0;
				//determines if either of the Animal objects involved is a 
				//Predator
				if(second instanceof PredatorLJS || third instanceof 
					PredatorLJS)
				{
					transfer *= 1.5; //multiplies the energy by 1.5
				}
				
				//second gains energy, third loses energy
				if(number.nextInt(2) == 0)
				{
					second.flee(third, transfer, 51);
					third.flee(second, (transfer * -1), 51);
				}
				else //second loses energy, third gains energy
				{
					second.flee(third,(transfer * -1), 51);
					third.flee(second, transfer, 51);
				}
			}
			repaint();
			if(first.getEnergy() < .05 && second.getEnergy() < .05 && 
				third.getEnergy() < .05 && timesShown == 0)
			{
				JOptionPane complete = new JOptionPane();
				complete.showMessageDialog(null, ("Number of steps: " + count));
				timesShown++;
			}
		}
	}
}