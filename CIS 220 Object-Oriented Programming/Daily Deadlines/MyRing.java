import java.awt.*;

public class MyRing extends MyCircle {

	// inherits from MyCircle class: diameter, x, y, color
	private int width;
	
	public MyRing(int d, Color hue, int upperX, int upperY, int numPixels) {
		super(d, hue, upperX, upperY);
		width = numPixels;
	}

	public void draw(Graphics page) {
		super.draw(page);
		page.setColor (Color.black);
		page.fillOval (x+width, y+width, diameter-2*width, diameter-2*width);
	}
	
	public int getRingWidth() {
		return width;
	}
}