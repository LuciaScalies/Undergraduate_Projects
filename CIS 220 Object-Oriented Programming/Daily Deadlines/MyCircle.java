//********************************************************************
//  Circle.java       Author: Lewis/Loftus & AMH
//
//  Represents a circle with a particular position, size, and color.
//********************************************************************

import java.awt.*;

public class MyCircle
{
   // if a class is going to be a parent class or super class to another
   // class, can continue to follow encapsulation by making the data
   // protected instead of private
   protected int diameter, x, y;
   protected Color color;
   // private int width;

   //-----------------------------------------------------------------
   //  Constructor: Sets up this circle with the specified values.
   //-----------------------------------------------------------------
   public MyCircle (int size, Color shade, int upperX, int upperY)
   {
      diameter = size;
      color = shade;
      x = upperX;
      y = upperY;
	  // width = w;
   }
   
   //-----------------------------------------------------------------
   //  Draws this circle in the specified graphics context.
   //-----------------------------------------------------------------
   public void draw (Graphics page)
   {
      page.setColor (color);
      page.fillOval (x, y, diameter, diameter);
	  // page.setColor (Color.black);
	  // page.fillOval (x+width, y+width, diameter-2*width, diameter-2*width);
   }
   
   //-----------------------------------------------------------------
   //  Diameter mutator.
   //-----------------------------------------------------------------
   public void setDiameter (int size)
   {
      diameter = size;
   }

   //-----------------------------------------------------------------
   //  Color mutator.
   //-----------------------------------------------------------------
   public void setColor (Color shade)
   {
      color = shade;
   }

   //-----------------------------------------------------------------
   //  X mutator.
   //-----------------------------------------------------------------
   public void setX (int upperX)
   {
      x = upperX;
   }


   //-----------------------------------------------------------------
   //  Y mutator.
   //-----------------------------------------------------------------
   public void setY (int upperY)
   {
      y = upperY;
   }

   
   // ring width accessor
   // public int getRingWidth() {
   //  return width;
   // }
   
   //-----------------------------------------------------------------
   //  Diameter accessor.
   //-----------------------------------------------------------------
   public int getDiameter ()
   {
      return diameter;
   }

   //-----------------------------------------------------------------
   //  Color accessor.
   //-----------------------------------------------------------------
   public Color getColor ()
   {
      return color;
   }

   //-----------------------------------------------------------------
   //  X accessor.
   //-----------------------------------------------------------------
   public int getX ()
   {
      return x;
   }

   //-----------------------------------------------------------------
   //  Y accessor.
   //-----------------------------------------------------------------
   public int getY ()
   {
      return y;
   }
}