package actors.passive;

import actors.Actor;
import javafx.scene.image.Image;

/**
 * This class defines the display of a background image for the game.
 * Background image are set with a specific size in the game.
 */
public class BackgroundImage extends Actor {

	/**
	 * Inherited abstract method that must be overridden.
	 * This method sets how the background image should act in each frame.
	 * In this game the background image will do nothing.
	 * @param now The timestamp of the frame in nanoseconds
	 */
	@Override
	public void act(long now) {
		
		
	}

	/**
	 * Class constructor for the background image.
	 * Creates an instance of a Background image with given image source.
	 * Sets the size, ratio of the image.
	 * @param imageLink The source for the image to be displayed
	 */
	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, 612.5, 800, false, true));
		
	}

}
