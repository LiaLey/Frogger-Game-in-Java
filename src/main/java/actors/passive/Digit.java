package actors.passive;

import actors.Actor;
import javafx.scene.image.Image;

/**
 * This class defines the properties of the digits images for the score display.
 */
public class Digit extends Actor {

	/**
	 * Inherited abstract method that must be overridden.
	 * This method defines how the Digit Images should act in each frame.
	 * In this game the Digit images will do nothing.
	 * @param now The timestamp of the frame in nanoseconds
	 */
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Class constructor for the Digit.
	 * Creates an instance of Digit with given image source, size and position.
	 * @param n Digit to be displayed
	 * @param size Size of the image
	 * @param x x-coordinate of the image
	 * @param y y-coordinate of the image
	 */
	public Digit(int n, int size, int x, int y) {
		setImage(new Image("file:src/main/resources/images/"+n+".png", size, size, true, true));
		setX(x);
		setY(y);
	}
	
}
