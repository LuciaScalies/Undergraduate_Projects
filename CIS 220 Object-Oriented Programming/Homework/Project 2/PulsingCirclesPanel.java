// Panel class for Pulsing Circles example
// Author: AMH

// import statements for graphics and GUI components
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// import statement for threads
import java.lang.Runnable;
//import statement for user prompt
import java.util.Random;

public class PulsingCirclesPanel extends JPanel {
	private final int WIN_WIDTH = 1280, WIN_HEIGHT = 1024;	// size of window
	private PCircle[] circles;	// array of PCircle objects
	private Thread[] timers;	// array of Threads used to control the 
								// behavior of the Circle objects respectively
	private int time;			// how much time should pass between redraws
								// of the screen, in nanoseconds
	
	public PulsingCirclesPanel(int numCircles) {			
		time = 200000000;
				
		// set the window size and color
		setPreferredSize (new Dimension(WIN_WIDTH, WIN_HEIGHT));
		setBackground (Color.blue);

		// initialize the arrays
		circles = new PCircle[numCircles];
		timers = new Thread[numCircles];
		
		//create Random number generator to position circles
		Random gen = new Random();
		
		//control time steps
		int steps = 5;
		
		// create the Circle objects
		for(int i = 0; i < circles.length; i++)
		{
			int maxSize = steps * 40;
			circles[i] = new PCircle(40, Color.red, 
									gen.nextInt(WIN_WIDTH - maxSize), 
									gen.nextInt(WIN_HEIGHT - maxSize), steps);
			steps++;
		}
				
		// create the thread objects to attach to the Circle objects
		for(int i = 0; i < timers.length; i++)
		{
			timers[i] = new Thread(new TimerThing(i));
			timers[i].start();
		}	
   }

	
   // draw the screen whenever a refresh is called for
   public void paintComponent(Graphics page) {
		super.paintComponent(page);

		// draw the circles
		for(int i = 0; i < circles.length; i++)
		{
			circles[i].draw(page);
		}
	}
	
	// a private inner class describing the behavior of the threads
	private class TimerThing implements Runnable {
		// instance data
		private int index;	// which circle is this thread attached to
		
		// constructor
		public TimerThing (int i) {
			index = i;
		}
		
		// when a thread is started, the run method is called and run
		// in the background while the rest of the program proceeds to run
		// at the same time
		public void run() {
			double starttime, endtime;
			
			// a loop to run - here runs forever
			while (circles[circles.length - 1].getNumCycles() < 5) {
				// first do the work of one step of the thread
				// set the diameter to grow or shrink by five pixels
				circles[index].setDiameter(5);
				
				// if a cycle of growing or shrinking was just completed, 
				// swap to a cycle of the other type, increase number of
				// cycles completed by one
				if (circles[index].cycleOver()) {
					circles[index].resetCycle();
				}
				
				// after doing the work of one step of the thread, redraw the
				// screen and then pause before starting the next "step"
				repaint();	// call the paintComponent() method
				starttime = System.nanoTime();	// check the current time
				do {
					endtime = System.nanoTime();
					// repeat checking the time until the required number of
					// nanoseconds have passed since starting the do loop
				} while (endtime - starttime < time);
			}
			
		}
	}
	
}