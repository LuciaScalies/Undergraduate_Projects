// AnimalAMH			Author: AMH
// Animal objects are circular objects located within a known, constrained
// environment that can move randomly and flee from other animals while
// tracking their energy usage.

import java.awt.*;
import java.util.Random;
import java.text.DecimalFormat;

public class AnimalAMH {
	protected int x,y;	// location of center of animal
	protected final int XBOUNDS, YBOUNDS;	// max allowable x,y coordinates
	protected double energy;	// amount of energy animal has, 0.0-1.0
	protected final int RADIUS;	// width of animal, drawn as a circle
	protected Color shade;	// color in which animal is drawn
	
	// constructor: initialize animal to given position within specified
	// range assuming a radius of 20 and an energy of 1, drawn in green
	public AnimalAMH(int r, int xPos, int yPos, int maxX, int maxY) {
		x = xPos;
		y = yPos;
		RADIUS = r;
		XBOUNDS = maxX;
		YBOUNDS = maxY;
		energy = 1.0;
		shade = Color.green;
	}
	
	// draw animal as a circle centered at given location with the current
	// energy level shown
	public void draw(Graphics page) {
		page.setColor(shade);	
		page.fillOval(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
		page.setColor(Color.black);
		page.drawOval(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
		DecimalFormat fmt = new DecimalFormat("0.00");
		page.setFont(new Font("Arial", Font.BOLD, 10));
		page.drawString(fmt.format(energy), x-10, y+5);
	}
	
	// move the animal randomly up, down, left, or right with equal distribution
	// using a step size equal to the animal's radius, ensuring the animal does
	// not leave its specified bounds, assuming buttons are roughly 50 pixels high
	// a single step reduces an animals energy by 0.05 but only if a move is
	// actually made; animals only move if they have energy to do so
	public void move() {
		if (energy > 0) {
			Random gen = new Random();
			int choice = gen.nextInt(4);
			if ((choice == 0) && (x+RADIUS*2 < XBOUNDS)) { x+=RADIUS; energy -= 0.05; }
			if ((choice == 1) && (x-RADIUS*2 > 0)) { x-=RADIUS; energy -= 0.05; }
			if ((choice == 2) && (y+RADIUS*2 < YBOUNDS)) { y+=RADIUS; energy -= 0.05; }
			if ((choice == 3) && (y-RADIUS*2 > 50)) { y-=RADIUS; energy -= 0.05; }
			energy = Math.max(energy, 0);	// zero out negative energies
		}
	}
	
	// adjust this animal's current energy level by the amount indicated and then
	// move it a radius distance away from the other animal along both the x and
	// the y axis without letting this animal leave its specified bounds
	public void flee(AnimalAMH other, double amount) {
		energy += amount;
		
		if (other.getX()<x && x+RADIUS*2 < XBOUNDS) { x+=RADIUS; }
		else { if (x-RADIUS*2 > 0) { x-=RADIUS;}}
		
		if (other.getY()<y && y+RADIUS*2 < YBOUNDS) { y+=RADIUS; }
		else { if (y-RADIUS*2 > 50) { y-=RADIUS;}}		
	}
	
	// given an animal, check that it is not in collision with this animal,
	// assuming all animals are circles with the same radius
	public boolean collides (AnimalAMH other) {
		return (Math.sqrt(Math.pow(x-other.getX(),2) +
			Math.pow(y-other.getY(),2)) < RADIUS*2);
	}
	
	// getter methods for animal position and energy
	public int getX() { return x; }
	public int getY() { return y; }
	public double getEnergy() { return energy; }
}