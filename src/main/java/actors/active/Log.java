package actors.active;
import actors.Actor;

import javafx.scene.image.Image;

/**
 * This class defines the Log obstacle in the game
 * extended from Actor
 */
public class Log extends Actor {

	/**
	 * speed of the moving log
	 */
	protected double speed;

	/**
	 * This method moves the log when it is called.
	 * This method is overridden from its parent class and define how the log moves.
	 * @param now The timestamp of each frame of the game in nanoseconds
	 */
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-100);
		if (getX()<-300 && speed<0)
			setX(700);
	}

	/**
	 * Class constructor for Log.
	 * Creates an instance of Log with the given position, image source, speed and size
	 * @param xpos x-coordinate of the log
	 * @param ypos y-coordinate of the log
	 * @param speed Speed of the log
	 * @param size Size of the log
	 * @param imageLink Image source of the log
	 */
	public Log(String imageLink, int size, int xpos, int ypos, double speed) {
		setImage(new Image(imageLink, size,size, true, true));
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		
	}

	/**
	 * This method returns the speed of the log when called.
	 * @return (double) Speed of the car
	 */
	public double getSpeed() {
		return this.speed;
	}

}

