package actors.active;
import actors.Actor;

import javafx.scene.image.Image;

/**
 * This class defines the Truck obstacle in the game.
 * Extended from Actor class.
 */
public class Truck extends Actor {

	private int speed;

	/**
	 * This method moves the truck when it is called.
	 * This method is overridden from its parent class and defines how the truck moves.
	 * @param now The timestamp of each frame of the game in nanoseconds
	 */
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-250);
		if (getX() < -50 && speed<0)
			setX(600);
	}

	/**
	 * Class constructor for Truck.
	 * Creates an instance of Truck with the given position, source image, speed and size.
	 * @param imageLink image source of the truck
	 * @param xpos x-coordinate of the truck
	 * @param ypos y-coordinate of the truck
	 * @param speed Speed of the truck
	 * @param w Width of the truck
	 * @param h Height of the truck
	 */
	public Truck(String imageLink, int xpos, int ypos, int speed, int w, int h) {

		setImage(new Image(imageLink, w,h, true, true));
		setX(xpos);
		setY(ypos);
		this.speed = speed;
	}

}
