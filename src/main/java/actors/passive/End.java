package actors.passive;

import actors.Actor;
import javafx.scene.image.Image;

/**
 * This class sets the display for the frog holes of each level
 * The Ends will have to de deactivated for the frog to be able to enter
 * The activation of each hole is set to false at the start
 */
public class End extends Actor {
	boolean activated = false;

	/**
	 * This method will set how the End (frog hole) is supposed to act.
	 * This method is inherited from the parent class Actors and should be overridden.
	 * In this game the End does not act or do anything so the method is empty.
	 * @param now the timestamp of the frame in nanoseconds
	 */
	@Override
	public void act(long now) {

	}

	/**
	 * Class constructor for End.
	 * Creates an instance of End with the given position
	 * @param x x-coordinate of the frog hole
	 * @param y y-oordinate of the frog hole
	 */
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:src/main/java/images/End.png", 60, 60, true, true));
	}

	/**
	 * This method sets the deactivated frog hole to become activated once called
	 * This method will be called once the frog enters the frog hole
	 */
	public void setEnd() {
		setImage(new Image("file:src/main/java/images/FrogEnd.png", 70, 70, true, true));
		activated = true;
	}

	/**
	 * This method returns a true/false value depending on whether or not the frog hole is activated
	 * If the frog hole is activated, the value will be true
	 * This method is called to check if the frog has entered an activated hole
	 * @return (boolean) returns the true/false value of whether the hole is activated or not
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * This method will change the activated value of a deactivated hole from false to true and the value of an activated hole from true to false
	 */
	public void toggle(){
		if(!activated){
			setImage(new Image("file:src/main/java/images/FrogEnd.png", 70, 70, true, true));
			activated = true;
		}
		else{
			setImage(new Image("file:src/main/java/images/End.png", 60, 60, true, true));
			activated = false;
		}
		//activated = !activated;
	}
	

}
