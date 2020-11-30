package actors.active;
import actors.Actor;

import javafx.scene.image.Image;

/**
 * This class defines the Turtle in the game. The frog will use the turtle as transport to cross the river.
 */
public class Turtle extends Actor {
	Image turtle1;
	Image turtle2;
	Image turtle3;
	private int speed;

	/**
	 * This method is inherited from the parent class and defines the movement of the turtle when is called.
	 * The method will also change the image according to a time interval determined by specific timestamps to form a smooth animation of the turtle.
	 * @param now The timestamp of each frame of the game
	 */
	@Override
	public void act(long now) {

				if (now/900000000  % 3 ==0) {
					setImage(turtle2);
					
				}
				else if (now/900000000 % 3 == 1) {
					setImage(turtle1);
					
				}
				else if (now/900000000 %3 == 2) {
					setImage(turtle3);
					
				}
			
		move(speed , 0);
		if (getX() > 600 && speed>0)
			setX(-200);
		if (getX() < -75 && speed<0)
			setX(600);
	}

	/**
	 * Class constructor for Turtle.
	 * Creates an instance of Turtle with given position, speed and size.
	 * @param xpos x-coordinate of the turtle
	 * @param ypos y-coordinate of the turtle
	 * @param speed Speed of the turtle
	 * @param w Width of the turtle
	 * @param h Height of the turtle
	 */
	public Turtle(int xpos, int ypos, int speed, int w, int h) {

		turtle1 = new Image("file:src/main/java/images/TurtleAnimation1.png", w, h, true, true);
		turtle2 = new Image("file:src/main/java/images/TurtleAnimation2.png", w, h, true, true);
		turtle3 = new Image("file:src/main/java/images/TurtleAnimation3.png", w, h, true, true);
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		setImage(turtle2);

	}

	/**
	 * This method returns the speed of the turtle when called.
	 * @return (double) The speed of the turtle
	 */
	public double getSpeed() {
		return this.speed;
	}
}
