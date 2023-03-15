// PopulatePanelAMH			Author: AMH
// Describes a JPanel containing three animals moving within an environment
// with the simulation of their movement and interaction triggered through
// button presses.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class PopulatePanelAMH extends JPanel {
	private JButton step;	// button to advance simulation one step
	private AnimalAMH animal1, animal2, animal3;	// three animals to simulate
	private boolean done;	// true if all animals are out of energy
	private int steps;		// number of times button has been pressed

	// constructor: initialize all animals to random locations within the
	// environment and set up GUI components
	public PopulatePanelAMH() {
		Random gen = new Random();
		// generate animals at random locations within window leaving 50 pixels
		// free at top for area button is in
		int radius = 20;
		int winSize = 200;
		if (gen.nextInt(3)==0) {
			animal1 = new PredatorAMH(radius, gen.nextInt(winSize-radius*2)+radius,
				gen.nextInt(winSize-radius*2-50)+radius+50, winSize, winSize);
		} else {
			animal1 = new AnimalAMH(radius, gen.nextInt(winSize-radius*2)+radius,
				gen.nextInt(winSize-radius*2-50)+radius+50, winSize, winSize);
		}
		if (gen.nextInt(3)==0) {
			animal2 = new PredatorAMH(radius, gen.nextInt(winSize-radius*2)+radius,
				gen.nextInt(winSize-radius*2-50)+radius+50, winSize, winSize);
		} else {
			animal2 = new AnimalAMH(radius, gen.nextInt(winSize-radius*2)+radius,
				gen.nextInt(winSize-radius*2-50)+radius+50, winSize, winSize);
		}
		if (gen.nextInt(3)==0) {
			animal3 = new PredatorAMH(radius, gen.nextInt(winSize-radius*2)+radius,
				gen.nextInt(winSize-radius*2-50)+radius+50, winSize, winSize);
		} else {
			animal3 = new AnimalAMH(radius, gen.nextInt(winSize-radius*2)+radius,
				gen.nextInt(winSize-radius*2-50)+radius+50, winSize, winSize);
		}
		done = false;
		steps = 0;
		
		step = new JButton("Step");
		step.addActionListener(new StepListener());
		add(step);
		setPreferredSize(new Dimension(winSize,winSize));
		setBackground(Color.black);
	}
	
	// paintComponent: draw each animal within the window
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		animal1.draw(page);
		animal2.draw(page);
		animal3.draw(page);		
	}
	
	// StepListener: with each button press, simulate a single move of the animals
	// consisting of a move of all animals, followed by a check for collision
	// between each pair of animals and any needed flee actions
	private class StepListener implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			if (!done) {
				// some animal still has energy
				Random gen = new Random();
				
				// move all three animals
				animal1.move();
				animal2.move();
				animal3.move();				
				steps++;
				
				// for any collision that occurs between two animals both still
				// having energy, determine an amount of energy to transfer from
				// one animal to the other and which animal should receive the
				// energy, having both flee each other
				if (animal1.getEnergy()>0 && animal2.getEnergy()>0 &&
					animal1.collides(animal2)) {
						// animal1 and animal2 collide
						double amount = gen.nextDouble()*0.07+0.01;
						if (animal1 instanceof PredatorAMH || animal2 instanceof PredatorAMH) {
							amount *= 1.5;
						}
						if (gen.nextInt(2)==0) {
							animal1.flee(animal2, amount);
							animal2.flee(animal1, -amount);
						} else {
							animal1.flee(animal2, -amount);
							animal2.flee(animal1, amount);
						}
				}
				if (animal1.getEnergy()>0 && animal3.getEnergy()>0 &&
					animal1.collides(animal3)) {
						// animal1 and animal3 collide
						double amount = gen.nextDouble()*0.07+0.01;
						if (animal1 instanceof PredatorAMH || animal3 instanceof PredatorAMH) {
							amount *= 1.5;
						}
						if (gen.nextInt(2)==0) {
							animal1.flee(animal3, amount);
							animal3.flee(animal1, -amount);
						} else {
							animal1.flee(animal3, -amount);
							animal3.flee(animal1, amount);
						}
				}
				if (animal2.getEnergy()>0 && animal3.getEnergy()>0 &&
					animal2.collides(animal3)) {
						// animal2 and animal3 collide
						double amount = gen.nextDouble()*0.07+0.01;
						if (animal2 instanceof PredatorAMH || animal3 instanceof PredatorAMH) {
							amount *= 1.5;
						}
						if (gen.nextInt(2)==0) {
							animal2.flee(animal3, amount);
							animal3.flee(animal2, -amount);
						} else {
							animal2.flee(animal3, -amount);
							animal3.flee(animal2, amount);
						}
				}
				
				if (animal1.getEnergy() <= 0 && animal2.getEnergy() <= 0 &&
					animal3.getEnergy() <= 0) {
						// it is now the case that all animals are out of energy
						// because this check is done within outer if statement
						// must be first time this is the case
						done = true;
						JOptionPane.showMessageDialog(null,
							"All animals stopped after " + steps + " steps.");
				}
				repaint();
			}
		}
	}
}