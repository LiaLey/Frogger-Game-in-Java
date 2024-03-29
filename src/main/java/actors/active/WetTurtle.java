package actors.active;
import actors.Actor;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This class defines the WetTurtle in the game. The frog will use the WetTurtle as transport to cross the river.
 */
public class WetTurtle extends Actor {


	/**
	 * ArrayList of WetTurtle images
	 */
	ArrayList<Image> wetTurtle = new ArrayList<>();
	/**
	 * Movement speed of the WetTurtle
	 */
	int speed;
	/**
	 * Whether the WetTurtle has sunk or not
	 */
	boolean sunk = false;

	/**
	 * This method is inherited from the parent class and defines the movement of the wet turtle.
	 * The method will also change the image according to a time interval determined by specific timestamps to form a smooth animation of the WetTurtle.
	 * The wet turtle will sink at a certain time interval and the frog will die if it stands on a sinking wet turtle.
	 * @param now The timestamp of each frame of the game in nanoseconds
	 */
	@Override
	public void act(long now) {

				if (now/900000000  % 4 ==0) {
					setImage(wetTurtle.get(1));
					sunk = false;
					
				}
				else if (now/900000000 % 4 == 1) {
					setImage(wetTurtle.get(0));
					sunk = false;
				}
				else if (now/900000000 %4 == 2) {
					setImage(wetTurtle.get(2));
					sunk = false;
				} else if (now/900000000 %4 == 3) {
					setImage(wetTurtle.get(3));
					sunk = true;
				}
			
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}

	/**
	 * Class constructor for Wet Turtle.
	 * Creates an instance of WetTurtle with the given position, speed and size.
	 * @param xpos x-coordinate of the wet turtle
	 * @param ypos y-coordinate of the wet turtle
	 * @param speed Speed of the wet turtle
	 * @param w Width of the wet turtle
	 * @param h Height of the wet turtle
	 */
	public WetTurtle(int xpos, int ypos, int speed, int w, int h) {

		wetTurtle.add(new Image("file:src/main/resources/images/TurtleAnimation1.png", w, h, true, true));
		wetTurtle.add(new Image("file:src/main/resources/images/TurtleAnimation2Wet.png", w, h, true, true));
		wetTurtle.add(new Image("file:src/main/resources/images/TurtleAnimation3Wet.png", w, h, true, true));
		wetTurtle.add(new Image("file:src/main/resources/images/TurtleAnimation4Wet.png", w, h, true, true));
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		setImage(wetTurtle.get(1));

	}

	/**
	 * This method returns a true/false value depending on whether the wet turtle has sunk or not.
	 * The return value will be true if the log has sunk and false if not.
	 * @return (boolean) Sunk - True if the log has sunk and false if it has not
	 */
	public boolean isSunk() {
		return sunk;

	}

	/**
	 * This method returns the speed of the wet turtle when called.
	 * @return (double) Speed of the wet turtle
	 */
	public double getSpeed() {
		return this.speed;
	}
}
