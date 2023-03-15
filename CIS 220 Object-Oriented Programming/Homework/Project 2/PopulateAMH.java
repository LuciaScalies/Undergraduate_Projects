// PopulateAMH			Author: AMH
// Establish GUI framework for population simulation program

import javax.swing.JFrame;

public class PopulateAMH {
	public static void main (String[] args) {
		JFrame frame = new JFrame ("Populate");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new PopulatePanelAMH());
		frame.pack();
		frame.setVisible(true);
	}
}