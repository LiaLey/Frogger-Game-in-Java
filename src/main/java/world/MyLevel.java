package world;
import actors.passive.BackgroundImage;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import actors.passive.Digit;
import actors.passive.Lives;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class defines the standard properties that should be present in each level type of the game.
 * This includes setting the lives, scores and passing levels along each class.
 */

public class MyLevel extends World{
	MediaPlayer mediaPlayer;
	Class<MyLevel> nextLevel;
	ArrayList<Lives> lives = new ArrayList<>();

	/**
	 * This method defines how the levels should act.
	 * The method is inherited from the parent class World.
	 * In this game, the level classes does nothing so the method is empty.
	 * @param now the timestamp of the current frame in nanoseconds
	 */
	@Override
	public void act(long now) {
		
	}

	/**
	 * Class constructor.
	 * Creates an instance of MyLevel.
	 * @param nextLevel The class that the game change to in the next round
	 */
	public MyLevel(Class<MyLevel> nextLevel) {
		

		BackgroundImage froggerback = new actors.passive.BackgroundImage("file:src/main/resources/images/iKogsKW2.png");
		this.add(froggerback);
		this.nextLevel = nextLevel;
	}

	/**
	 * This method sets the number of lives that will be visible in the scene.
	 * @param numberOfLives The number of lives the frog has left
	 */
	public void setNumberOfLives(int numberOfLives){
		getChildren().removeIf(x -> x instanceof Lives);
		lives.clear();
		int x = 10;
		for (int i = 0; i < numberOfLives; i++) {
			Lives temp = new Lives(35, x, 755);
			x += 40;
			lives.add(temp);
		}
		this.addHealth(lives);
	};

	/**
	 * This method visibly removes lives from the level scene when called.
	 */
	public void removeLives(){
		this.removeHealth(lives);
	}


	/**
	 * This method sets the number(scores) visible in the level scene.
	 * @param points The current points of the player to be displayed
	 */
	public void setNumber(int points) {
		getChildren().removeIf(x -> x instanceof Digit);
		int shift = 0;
		int length = (int) Math.log10(points) + 1;    //get individual digit of a certain number
		for (int i = 0; i < length; i++) {
			int number = ((int) (points / Math.pow(10, i))) % 10;
			this.add(new Digit(number, 30, 575 - shift, 0));
			shift += 30;
		}
	}

	/**
	 * This method gets the the next level to be displayed when called.
	 * The next level is instantiated according to the given class passed by the previous level.
	 * @return The next level to be displayed
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public MyLevel getNextLevel() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		return nextLevel.getConstructor().newInstance();
	}

}
