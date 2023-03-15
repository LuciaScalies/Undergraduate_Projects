/*
 PopulatePanelDJS.java					Author: AMH, Dominic Joseph Scalies
 Sets up the GUI environment, as well as the AnimalDJS and PredatorDJS objects
 used in PopulateDJS. Using Threads, the program simulates and manages the 
 motion and interaction of AnimalDJS and PredatorDJS objects. It contains a 
 pause feature allowing the user at any time to freeze the dynamic motion in 
 place, with the following press restarting the program as if no time has passed
 */

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
 
public class PopulatePanelDJS extends JPanel
{
	private AnimalDJS[] animals; //the AnimalDJS objects in the array
	private double chance; //the chances of a PredatorDJS object being created
	private int proximity; //the distance of pixels between AnimalDJS' centers 
						   //to be considered in proximity to one another
	private Thread[] motion; // array of Threads used to control the behavior of
							 // the AnimalDJS and PredatorDJS objects
	private JButton pause; //the Pause button
	private int time; //the time between redraws of the screen in nanoseconds
	private boolean canMove; //control boolean for pause button functionality
	private final int WIN_SIZE; //size of the window
	private final int PAUSE_BOTTOM; //bottom of the PAUSE JButton
	
	//Constructor: Sets up PopulatePanelDJS with the necessary components
	public PopulatePanelDJS(int life, double hunters, int closeness)
	{
		time = 300000000;
		canMove = true;
		// creates the AnimalDJS and Thread arrays
		animals = new AnimalDJS[life];
		motion = new Thread[life];
		chance = hunters;
		proximity = closeness;
		WIN_SIZE = 400;
		PAUSE_BOTTOM = 51;
		
		//create pause button
		pause = new JButton("Pause");
		pause.addActionListener (new PauseListener());
		
		//adds components to the window
		add(pause);
		
		//sets preferred window size		
		setPreferredSize(new Dimension(WIN_SIZE, WIN_SIZE)); 
		setBackground(Color.black); //sets background color to black
		
		//Creates a Random number generator
		Random gen = new Random();
		
		//populates the array with animals
		for(int i = 0; i < animals.length; i++)
		{
			if(Math.random() <= chance) //determines if Animals are Predators
			{
				animals[i] = new PredatorDJS(gen.nextInt(361), 
									gen.nextInt(310)+PAUSE_BOTTOM, WIN_SIZE, 
									WIN_SIZE, PAUSE_BOTTOM);
			}
			else
			{
				animals[i] = new AnimalDJS(gen.nextInt(361), 
									gen.nextInt(310)+PAUSE_BOTTOM, WIN_SIZE, 
									WIN_SIZE, PAUSE_BOTTOM);
			}
		}
		
		//pairs the Threads with associated runnables
		for(int i = 0; i < motion.length; i++)
		{
			motion[i] = new Thread(new MotionMonitor(i));
			motion[i].start();
		}
	}
	
	//Creates the Animals by asking them to draw themselves
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		for(int i = 0; i < animals.length; i++)
		{
			animals[i].draw(page);
		}
	}
	
	//creates the MotionMonitor class to implement Runnable and run Threads
	private class MotionMonitor implements Runnable
	{
		private int index; //the index of the associated AnimalDJS object
		
		public MotionMonitor(int i)
		{
			index = i; //associates the Thread and the Animal
		}
		
		public void run() //runs the Thread
		{
			double starttime, endtime;
			Random num = new Random();
			boolean last = false;
			while(animals[index].getEnergy() > 0)
			{
				if(canMove) //checks if simulation is paused
				{
					animals[index].move(); //moves animal
					//the index of the closest Animal
					int closest = WIN_SIZE; //initialized to an impossible number
					//finds closest animal and stores the index
					for(int i = 0; i < animals.length; i++) 
					{
						if(i != index && animals[i].getEnergy() > 0)
						{
							double temp = animals[index].getDistance(animals[i]);
							if(Math.min(temp, closest) < closest)
							{
								closest = i;
							}
						}
					}
					
					//makes sure there is a closest living animal
					if(closest != WIN_SIZE)
					{
						//determines if the animals are in proximity
						if(animals[index].getDistance(animals[closest]) <= 
							proximity)
						{
							//checks for case of 2 predators interacting
							if(animals[index] instanceof PredatorDJS && 
								animals[closest] instanceof PredatorDJS)
							{
								animals[index].flee(animals[closest], .02);
								animals[closest].flee(animals[index], .02);
							}
							
							//checks for case of 2 non-predators
							//true if and only if both Animals are Animals but
							//not Predators
							if((animals[index] instanceof AnimalDJS && 
								!(animals[index] instanceof PredatorDJS)) && 
								(animals[closest] instanceof AnimalDJS && 
								!(animals[closest] instanceof PredatorDJS)))
							{
								animals[index].addEnergy(.02);
								animals[closest].addEnergy(.02);
							}
							//checks for case of predator and non-predator
							//true if and only if one but not both Animals are
							//Predators
							if((animals[index] instanceof PredatorDJS || 
								animals[closest] instanceof PredatorDJS) && 
							   !(animals[index] instanceof PredatorDJS && 
							   animals[closest] instanceof PredatorDJS))
							{
								if(animals[index] instanceof PredatorDJS)
								{
									animals[index].addEnergy(.02);
									animals[closest].flee(animals[index], .02);
								}
								else
								{
									animals[closest].addEnergy(.02);
									animals[index].flee(animals[closest], .02);
								}
							}
						}
					}
					else
					{
						last = true;
					}
				}
				repaint();	// call the paintComponent() method
				starttime = System.nanoTime();	// checks the current time
				// repeat checking the time until the required number of
				// nanoseconds have passed since starting the do loop
				do 
				{
					endtime = System.nanoTime(); //checks the current time
				} while (endtime - starttime < time);
				
				if(last) //reached only if the AnimalDJS is the last one alive
				{
					if(animals[index].getEnergy() == 0)
					{
						int animalCount = 0; //number of AnimalDJS objects
						int predatorCount = 0; //number of PredatorDJS 
											//objects
						int animalSteps = 0; //steps AnimalDJS objects have
											 //taken
						int maxAnimalSteps = 0; //the maximum number of steps
												//taken by an AnimalDJS object
						int predatorSteps = 0; //steps PredatorDJS objects have
											   //taken
						int maxPredatorSteps = 0; //the maximum number of steps 
												  //taken by a PredatorDJS 
												  //object
						for(int i = 0; i < animals.length; i++)
						{
							if(animals[i] instanceof PredatorDJS)
							{
								predatorCount++;
								predatorSteps += animals[i].getStepsTaken();
								maxPredatorSteps = Math.max(maxPredatorSteps, 
													animals[i].getStepsTaken());
							}
							else
							{
								animalCount++;
								animalSteps += animals[i].getStepsTaken();
								maxAnimalSteps = Math.max(maxAnimalSteps, 
												 animals[i].getStepsTaken());
							}
						}
						
						if(animalCount != 0) //prevents divide by zero error
						{
							System.out.println("Average non-predator steps:"
								+ " " + ((double)animalSteps/animalCount));
						}
						else
						{
							System.out.println("Average non-predator steps:"
										+ " N/A; no non-predators found");
						}
						
						System.out.println("Maximum non-predator steps: " + 
										   maxAnimalSteps);
						
						if(predatorCount != 0) //prevents divide by zero error
						{
							System.out.println("Average predator steps:"
								+ " " + ((double)predatorSteps/predatorCount));
						}
						else
						{
							System.out.println("Average predator steps:"
										+ " N/A; no non-predators found");
						}
						System.out.println("Maximum predator steps: " + 
										   maxPredatorSteps);
					}
				}
			}
		}
	}
	
	//creates the PauseListener class to implement ActionListener on the pause
	//button
	private class PauseListener implements ActionListener 
	{
		public void actionPerformed (ActionEvent event) 
		{
			canMove = !canMove;
		}
	}
}