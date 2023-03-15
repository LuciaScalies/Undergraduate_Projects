//  PCircle.java       Author: Lewis/Loftus & AMH
//  Represents a circle with a particular position, size, and color, adapted
// 	to also have a number of "steps" per growth/shrink cycle and the ability
//  to make such a growth/shrink step and reset such a cycle

import java.awt.*;

public class PCircle
{
   private int diameter, x, y;	// size and location of circle
   private Color color;	// color of circle
   private int maxSteps;	// total steps circle takes to grow or shrink in a cycle
   private int currSteps;	// how many steps into the current cycle is this circle
   private boolean growing;	// is the circle currently growing
   private int numCycles; //the number of complete cycles

   public PCircle (int size, Color shade, int upperX, int upperY, int s)
   {
      diameter = size;
      color = shade;
      x = upperX;
      y = upperY;
	  maxSteps = s;
	  currSteps = 0;
	  growing = true;	// all circles start out growing
	  numCycles = 0;
   }

   public void draw (Graphics page)
   {
      page.setColor (color);
      page.fillOval (x, y, diameter, diameter);
   }
   
   public int getNumCycles()
   {
	   return numCycles;
   }

   // find out if the current grow/shrink cycle is over yet
   public boolean cycleOver() {
      return (currSteps == maxSteps);
   }
   
   // set the circle to start its next cycle, moving in the opposite direction
   public void resetCycle() {
      growing = !growing;
	  currSteps = 0;
	  numCycles++;
   }
   
   // if the circle is growing, add size to the diameter, otherwise
   // subtract size from the diameter
   public void setDiameter (int size)
   {
	  if (growing) {
		diameter += size;
	  } else {
	    diameter -= size;
      }
	  currSteps++;	// every time the diameter's size is changed, now add
					// one to the number of steps performed
   }
}