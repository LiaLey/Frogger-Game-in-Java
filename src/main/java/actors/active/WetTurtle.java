package actors.active;
import actors.Actor;

import javafx.scene.image.Image;

/**
 * Class for the WetTurtle in the game. The frog will use the WetTurtle as transport to cross the river
 */
public class WetTurtle extends Actor {

	Image turtle1;
	Image turtle2;
	Image turtle3;
	Image turtle4;
	int speed;
	boolean sunk = false;

	/**
	 * This method moves the wet turtle when is called
	 * The method will also change the image according to the timestamp of each frame to form a smooth animation of the wet turtle
	 * The wet turtle will sink at a certain time interval and the frog will die if it stands on a sinking wet turtle
	 * @param now the timestamp of each frame of the game
	 */
	@Override
	public void act(long now) {

				if (now/900000000  % 4 ==0) {
					setImage(turtle2);
					sunk = false;
					
				}
				else if (now/900000000 % 4 == 1) {
					setImage(turtle1);
					sunk = false;
				}
				else if (now/900000000 %4 == 2) {
					setImage(turtle3);
					sunk = false;
				} else if (now/900000000 %4 == 3) {
					setImage(turtle4);
					sunk = true;
				}
			
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}

	/**
	 * Class constructor for Wet Turtle
	 * Creates an instance of WetTurtle with the given position, speed and size
	 * @param xpos x-coordinate of the wet turtle
	 * @param ypos y-coordinate of the wet turtle
	 * @param speed speed of the wet turtle
	 * @param w width of the wet turtle
	 * @param h height of the wet turtle
	 */
	public WetTurtle(int xpos, int ypos, int speed, int w, int h) {

		turtle1 = new Image("file:src/main/java/images/TurtleAnimation1.png", w, h, true, true);
		turtle2 = new Image("file:src/main/java/images/TurtleAnimation2Wet.png", w, h, true, true);
		turtle3 = new Image("file:src/main/java/images/TurtleAnimation3Wet.png", w, h, true, true);
		turtle4 = new Image("file:src/main/java/images/TurtleAnimation4Wet.png", w, h, true, true);
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		setImage(turtle2);

	}

	/**
	 * This method returns a true/false value depending on whether the wet turtle has sunk or not
	 * The return value will be true if the log has sunk
	 * @return (boolean) sunk - true if the log has sunk and false if it has not
	 */
	public boolean isSunk() {
		return sunk;

	}

	/**
	 * This method returns the speed of the wet turtle when called
	 * @return (double) speed of the wet turtle
	 */
	public double getSpeed() {
		return this.speed;
	}
}
