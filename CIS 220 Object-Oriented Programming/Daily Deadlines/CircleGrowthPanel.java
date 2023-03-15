//********************************************************************
//  CircleGrowthPanel.java       Author: Scalies, Jaworski
//
//  Creates an environment for the expanding circle
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CircleGrowthPanel extends JPanel
{
   private JButton expand, decrease;
   private MyCircle visual;
   private final int WIN_SIZE;

   //-----------------------------------------------------------------
   //  Constructor: Sets up the main GUI components.
   //-----------------------------------------------------------------
   public CircleGrowthPanel()
   {
      expand = new JButton("Click me!");
      expand.addActionListener (new ExpandListener());
	  
	  decrease = new JButton("Or me!");
	  decrease.addActionListener(new DecreaseListener());

	  visual = new MyRing(10, Color.blue, 145, 145, 10);
      add (expand);
	  add (decrease);
      
	  WIN_SIZE = 300;
	  setPreferredSize (new Dimension(WIN_SIZE, WIN_SIZE));
      setBackground (Color.white);
   }
   
    public void paintComponent (Graphics page)
   {
      super.paintComponent(page);

      visual.draw(page);
   }
   
   //*****************************************************************
   //  Represents an action listener for the expand button
   //*****************************************************************
   private class ExpandListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Performs the conversion when the enter key is pressed in
      //  the text field.
      //--------------------------------------------------------------
      public void actionPerformed (ActionEvent event)
      {
		if(visual.getDiameter() + 20 <= WIN_SIZE)
		{
			visual.setDiameter(visual.getDiameter() + 20);
			visual.setX(visual.getX() - 10);
			visual.setY(visual.getY() - 10);
		}
		else
		{
			visual.setDiameter(WIN_SIZE);
			visual.setX(0);
			visual.setY(0);
		}
		
		if(visual instanceof MyRing)
		{
			System.out.println("Growing the ring.");
		}
		else
		{
			System.out.println("Growing the circle." );
		}
		repaint();
      }
   }
   
   private class DecreaseListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Performs the conversion when the enter key is pressed in
      //  the text field.
      //--------------------------------------------------------------
      public void actionPerformed (ActionEvent event)
      {
		  visual.setDiameter(visual.getDiameter() - 20);
		  visual.setX(visual.getX() + 10);
		  visual.setY(visual.getY() + 10);
		  
		  repaint();
      }
   }
}
