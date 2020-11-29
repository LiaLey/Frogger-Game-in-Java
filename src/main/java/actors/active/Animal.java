package actors.active;


import actors.*;
import actors.passive.End;

import javafx.event.EventHandler;
import javafx.scene.image.Image;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Class for the player which is the Frog.
 * Displays the movement and the animation of the Frog
 * Displays and controls interaction of the frog with the obstacles in the game
 */
public class Animal extends Actor {

	MediaPlayer mediaPlayer;
	protected static final int IMAGE_SIZE = 40;
	protected final double MOVEMENT = 13.3333333 * 2;
	protected final double MOVEMENT_X = 10.666666 * 2;
	private final AnimationIterator upAnimation = new AnimationIterator(AnimationType.UP);
	private final AnimationIterator downAnimation = new AnimationIterator(AnimationType.DOWN);
	private final AnimationIterator leftAnimation = new AnimationIterator(AnimationType.LEFT);
	private final AnimationIterator rightAnimation = new AnimationIterator(AnimationType.RIGHT);
	private final AnimationIterator waterDeathAnimation = new AnimationIterator(AnimationType.WATER_DEATH);
	private final AnimationIterator carDeathAnimation = new AnimationIterator(AnimationType.CAR_DEATH);

	public boolean blockInputControls = false;
	private boolean carDeath = false;
	private boolean waterDeath = false;

	protected int lives = 8;
	protected int auxPoints = 0;
	protected int heightPoints = -93;
	int numberOfEndsActivated = 0;
	int maxRangeAchieved = 800;

	/**
	 * Class constructor for animal
	 * Creates an instance of the animal player (frog)
	 * Sets the position of the frog and controls the movement and animation displays of the frog during each movement
	 * Controls of the frog are the WASD keys
	 */
	public Animal() {
		setImage(new Image("file:src/main/java/images/froggerUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
		setX(300);
		setY(679.8+MOVEMENT);

		setOnKeyPressed(event -> {
			if (!blockInputControls) {
				if (event.getCode() == KeyCode.W) {
					move(0, -MOVEMENT);
					setImage(upAnimation.next());
				} else if (event.getCode() == KeyCode.A) {
					move(-MOVEMENT_X, 0);
					setImage(leftAnimation.next());
				} else if (event.getCode() == KeyCode.S) {
					move(0, MOVEMENT);
					setImage(downAnimation.next());
				} else if (event.getCode() == KeyCode.D) {
					move(MOVEMENT_X, 0);
					setImage(rightAnimation.next());
				}
			}
		});

		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!blockInputControls) {
					if (event.getCode() == KeyCode.W) {
						move(0, -MOVEMENT);
						setImage(upAnimation.restingPosition());
					} else if (event.getCode() == KeyCode.A) {
						move(-MOVEMENT_X, 0);
						setImage(leftAnimation.restingPosition());
					} else if (event.getCode() == KeyCode.S) {
						move(0, MOVEMENT);
						setImage(downAnimation.restingPosition());
					} else if (event.getCode() == KeyCode.D) {
						move(MOVEMENT_X, 0);
						setImage(rightAnimation.restingPosition());
					}
				}
			}
		});
	}

	/**
	 * This method tells the frog how to act when called. The method is called every frame by the animation timer.
	 * This method keeps track of the maximum range the frog has travelled as well as the position of the frog in each frame
	 * In each frame, by keeping track of the position of the frog, the game will know if the frog has hit an obstacle or drowned in the river.
	 * The method checks for intersections with other objects and adds points or calls for death accordingly
	 * The method also checks if the frog has entered the frog hole and awards points
	 * @param now
	 */
	@Override
	public void act(long now) {

		if (getY() < maxRangeAchieved) {	// keep tracks of maximum range reached and increments score each time we break the threshold
			addPoints((int) (maxRangeAchieved - getY()), 0);
			maxRangeAchieved = (int) getY();
		}

		if (getY()<0 || getY()>734) {  //respawn if vertically out of map
			respawn(false);
		}

		if (getX()<0) {    //push animal back if moved horizontally out of map
			move(MOVEMENT*2, 0);
		}else if (getX()>600){
			move(-MOVEMENT*2,0);
		}

		if (carDeath) {						// check for death condition
			initiateDeathAnimation(carDeathAnimation, now);
			return;
		} else if (waterDeath) {
			initiateDeathAnimation(waterDeathAnimation, now);
			return;
		}

		if (getIntersectingObjects(Truck.class).size() >= 1) {   //collision with Truck
			carDeath = true;
		}
		if (getIntersectingObjects(Car.class).size() >= 1) {   //collision with car
			carDeath = true;
		}
//		if (getX() == 240 && getY() == 82) {
//			stop = true;
//		}
		if (getIntersectingObjects(Log.class).size() >= 1 && !blockInputControls) {  //standing on log
			Log myLog = getIntersectingObjects(Log.class).get(0);
			move(myLog.getSpeed(), 0);
		}
		else if (getIntersectingObjects(Turtle.class).size() >= 1 && !blockInputControls) {  //standing on turtle
			Turtle myTurtle = getIntersectingObjects(Turtle.class).get(0);
			move(myTurtle.getSpeed(), 0);
		}
		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {  //standing on wet turtle
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				WetTurtle myWetTurtle = getIntersectingObjects(WetTurtle.class).get(0);
				move(myWetTurtle.getSpeed(), 0);
			}
		}
		else if (getIntersectingObjects(End.class).size() >= 1) {
			End currentEnd = getIntersectingObjects(End.class).get(0);
			if (currentEnd.isActivated()) {
				addPoints(0,-50);
				waterDeath = true;
				currentEnd.toggle();
				numberOfEndsActivated--;
			}
			else {
				addPoints(0,50);
				maxRangeAchieved = 800;
				currentEnd.toggle();
				numberOfEndsActivated++;
				playWinMusic();
				respawn(false);
			}
			//respawn(false);
		}
		else if (getY()<413){
			waterDeath = true;

		}
	}

	/**
	 * The death animation of the frog will start to display once the method is called.
	 * The method will show the death images in sequence and forms an animation loop
	 * @param animation the arrayList of images that will loop to form an animation
	 * @param now the timestamp of each frame in the game in nanoseconds
	 */
	public void initiateDeathAnimation(AnimationIterator animation, long now){
		blockInputControls = true;
		if((now) % 11 == 0){
			setImage(animation.next());
		}
		if(animation.nextIndex() == 0){
			if (animation.type == AnimationType.CAR_DEATH){
				carDeath = false;
			}
			else if (animation.type == AnimationType.WATER_DEATH){
				waterDeath = false;
			}
			animation.resetCycle();
			respawn(true);
			lives--;
		}

	}

	/**
	 * This method returns a true/false value each time it is called.
	 * The value will be true if the frog has managed to enter all 5 frog holes in the level
	 * @return (boolean) true/ false depending on whether the frog has filled up all 5 frog holes
	 */
	public boolean hasWon() {
		return numberOfEndsActivated == 2;
	}

	/**
	 * This method will return the total points of the frog at present when called
	 * @return (int)totalPoints - total points scored by the player
	 */
	public int getTotalPoints() {
		return auxPoints+heightPoints;
	}

	/**
	 * This method will respawn the frog to its starting position when called
	 * It will also check if the respawn is triggered by the death of the frog or not
	 * If so, scores will be deducted
	 * @param died boolean value of whether of not the frog has died when triggering the respawn
	 */
	public void respawn(boolean died) {
		setX(300);
		setY(679.8 + MOVEMENT);
		setImage(new Image("file:src/main/java/images/froggerUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
		blockInputControls = false;
		if (died) {
			addPoints(0, -50);
		}
	}

	/**
	 * This method will reset the number of frog holes filled back to 0
	 */
	public void resetEnds() {
		numberOfEndsActivated = 0;
	}

	/**
	 * This method will reset the health and points of the game
	 * it will also call for the reset of the number of frog holes entered back to 0
	 */
	public void reset(){
		resetEnds();
		lives = 8;
		auxPoints = 0;
		maxRangeAchieved = 800;
		heightPoints = -93;
	}

	/**
	 * This method wil add the points scored by the player
	 * @param heightScore the vertical distance travelled by the frog
	 * @param auxScore the scores earned when winning the level or entering a frog hole
	 */
	public void addPoints(int heightScore, int auxScore) {
		this.heightPoints += heightScore;
		if (auxPoints + auxScore < 0)
			this.auxPoints = 0;
		else
			this.auxPoints += auxScore;
	}

	/**
	 * This method returns the current health of the frog when called
	 * @return (int) the health (number of lives) left
	 */
	public int getLives(){
		return lives;
	}


	/**
	 * This method will play the winning music when called.
	 * This method is called when the frog manages to enter the frog hole
	 */
	public void playWinMusic() {
		String musicFile = "src/main/java/sound/win.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(1);
		if (!hasWon()){
			mediaPlayer.play();
		}

	}
}
