package actors.passive;

import actors.Actor;
import javafx.scene.image.Image;

/**
 * Class to set the digits for the score display
 */
public class Digit extends Actor {

	/**
	 * Inherited abstract method that must be overridden
	 * This method sets how the Digit Images should act in each frame
	 * In this game the Images will do nothing
	 * @param now the timestamp of the frame in nanoseconds
	 */
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Class constructor for the Digit
	 * Creates an instance of Digit with given image source, size and position
	 * @param n digit to be displayed
	 * @param size size of the image
	 * @param x x-coordinate of the image
	 * @param y y-coordinate of the image
	 */
	public Digit(int n, int size, int x, int y) {
		setImage(new Image("file:src/main/java/images/"+n+".png", size, size, true, true));
		setX(x);
		setY(y);
	}
	
}
