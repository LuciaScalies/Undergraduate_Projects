/*
 PopulateDJS.java					Author: AMH, Dominic Joseph Scalies
 Creates and displays the PopulateDJS GUI environment
 */
/*
  Average with 0 predators: 29.571 across 7 runs {21, 24, 22, 42, 28, 28, 42}
  Average with 1 predator: 29.8 across 5 runs {28, 22, 33, 29, 37}
  Average with 2 predators: 38 across 5 runs {31, 33, 46, 48, 32}
  Average with 3 predators: {44, 56}
  My thoughts as to why the average gets progressively higher as more predators
  are introduced: Predators have three significant factors that increase the 
  duration of the program. The first is their collision modifier. Since their 
  collisions have a multiplier, they have a much greater chance of preserving or
  increasing the current energy available in the environment. Second, is their
  initial starting energy. Since the predators begin with more energy, the 
  systems they enter require more steps to expend that energy. Third, is the 
  predator's unique movement. Since predators can potentially move in more than 
  one direction per step, the odds of a collision are much higher, and since 
  collisions are the only way to increase the system's overall energy, the more 
  frequently they occur, the more likely it is that the overall energy will 
  increase. When multiple predators are in a system, these effects are 
  magnified.
*/

import javax.swing.JFrame;
 
public class PopulateDJS
{
	//runs the GUI
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Populate"); //creates the JFrame
		//exits program when closed
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		//creates the PopulatePanelDJS via its constructor
		PopulatePanelDJS panel = new PopulatePanelDJS();
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}