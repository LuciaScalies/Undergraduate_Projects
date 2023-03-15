// PredatorAMH			Author: AMH
// Predators are animals that appear as yellow rather than green and follow
// a different move pattern.

import java.awt.*;
import java.util.Random;

public class PredatorAMH extends AnimalAMH {
	
	// constructor: predators are set up as animals are except they are drawn
	// in yellow and they start with a higher initial energy of 1.5
	public PredatorAMH(int r, int xPos, int yPos, int maxX, int maxY) {
		super(r, xPos, yPos, maxX, maxY);
		shade = Color.yellow;
		energy = 1.5;
	}
	
	// move: predators potentially both horizontally and vertically in a single
	// step but also possibly do not move at all; for each step, the horizontal
	// move options are selected with 1/4 chance each then the vertical move
	// options are selected with 1/4 chance each
	public void move() {
		if (energy > 0) {
			Random gen = new Random();
			int choice = gen.nextInt(4);
			if ((choice == 0) && (x+RADIUS*2 < XBOUNDS)) { x+=RADIUS; energy -= 0.05; }
			else {
				choice = gen.nextInt(4);
				if ((choice == 0) && (x-RADIUS*2 > 0)) { x-=RADIUS; energy -= 0.05; }
			}

			choice = gen.nextInt(4);
			if ((choice == 0) && (y+RADIUS*2 < YBOUNDS)) { y+=RADIUS; energy -= 0.05; }
			else {
				choice = gen.nextInt(4);
				if ((choice == 0) && (y-RADIUS*2 > 50)) { y-=RADIUS; energy -= 0.05; }
			}
			energy = Math.max(energy, 0);
		}
	}

}