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

	protected int health = 8;
	protected int auxPoints = 0;
	protected int heightPoints = -93;
	int numberOfEndsActivated = 0;
	int maxRangeAchieved = 800;

	public Animal(String imageLink) {
		setImage(new Image(imageLink, IMAGE_SIZE, IMAGE_SIZE, true, true));
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
			health--;
		}

	}

	public boolean hasWon() {
		return numberOfEndsActivated == 2;
	}
	
	public int getTotalPoints() {
		return auxPoints+heightPoints;
	}

	public void respawn(boolean died) {
		setX(300);
		setY(679.8 + MOVEMENT);
		setImage(new Image("file:src/main/java/images/froggerUp.png", IMAGE_SIZE, IMAGE_SIZE, true, true));
		blockInputControls = false;
		if (died) {
			addPoints(0, -50);
		}
	}

	public void resetEnds() {
		numberOfEndsActivated = 0;
	}

	public void reset(){
		resetEnds();
		health = 8;
		auxPoints = 0;
		maxRangeAchieved = 800;
		heightPoints = -93;
	}

	public void addPoints(int heightScore, int auxScore) {
		this.heightPoints += heightScore;
		if (auxPoints + auxScore < 0)
			this.auxPoints = 0;
		else
			this.auxPoints += auxScore;
	}

	public int getHealth(){
		return health;
	}


	public void playWinMusic() {
		String musicFile = "src/main/java/sound/win.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(1);
		mediaPlayer.play();
	}


}
