//********************************************************************
//  CircleGrowth.java       Author: Scalies, Jaworski
//
//  Runs the interactive expanding circle
//********************************************************************

import javax.swing.JFrame;

public class CircleGrowth
{
	//-----------------------------------------------------------------
   //  Creates and displays the expanding circle program.
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Expanding Circle");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      CircleGrowthPanel panel = new CircleGrowthPanel();

      frame.getContentPane().add(panel);
      frame.pack();
      frame.setVisible(true);
   }
}