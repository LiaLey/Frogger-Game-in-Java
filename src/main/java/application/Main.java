package application;

import actors.active.Animal;
import actors.passive.BackgroundImage;
import actors.passive.Lives;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Pair;
import menu.*;
import sound.MediaPlay;
import world.*;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


/**
 * This class defines a Pair of String and Integer to keep records of the name and score of the player.
 * This class extends from Pair and implements the public interface Comparable.
 * Comparable imposes total ordering of objects in the class that implements it so it'll sort the scores of the player accordingly.
 * @param <String> The name of the device/player (user)
 * @param <Integer> Total score of the player (score)
 */
class ScorePair<String,Integer> extends Pair<String, Integer> implements Comparable{
	/**
	 * ScorePair class constructor.
	 * Creates an instance of ScorePair containing a String (Name) and Integer (Score)
	 * @param user Name of device
	 * @param score Score
	 */
	public ScorePair(String user, Integer score){
		super(user,score);
	}

	/**
	 * Method implemented from Comparable for the total ordering of objects.
	 * @param o  The ScorePair
	 * @return Scores sorted in order
	 */
	@Override
	public int compareTo(Object o) {
		int compareScore = (int) ((ScorePair) o).getValue(); // get the value(score)
		return (int) this.getValue() - compareScore; //compare
	}
}

/**
 * Main Class of the Frogger Application
 */

public class Main extends Application {

	AnimationTimer timer;
	Animal frog = new Animal();
	MyLevel level; //test
	Stage currentStage;
	String currentUser;
	MediaPlay mediaPlayer = new MediaPlay();
	ArrayList<Lives> lives = new ArrayList<>(); //test
	ArrayList<ScorePair> scoreBoard = new ArrayList<>();

	/**
	 * Leaderboard class to define a Leaderboard to store scores of the players.
	 * The scores are obtained from the arrayList of scores read from the LeaderBoard file
	 * Has a Main Menu button to allow players to go back to the application.Main Menu.
	 */
	private class LeaderBoard extends Pane {

		/**
		 * Class that defines Menu Items that does not do anything on click.
		 */
		private class PassiveMenuItem extends ButtonAction{
			/**
			 * Abstract method inherited from ButtonAction.
			 * @throws IOException on input error
			 * Does nothing, the Button will not act in Passive Menu Items
			 */
			@Override
			public void act() throws IOException {}
		}

		/**
		 * LeaderBoard class Constructor.
		 * Creates an instance of a Leaderboard displaying the scores of each player.
		 */
		public LeaderBoard(){
			BackgroundImage mainMenubg = new BackgroundImage("file:src/main/resources/images/bg.jpg");

			Title title = new Title("LeaderBoard");
			title.setTranslateX(120);
			title.setTranslateY(75);


			MenuBox mainMenuBox = new MenuBox(
					new MenuItem("Main Menu", new ButtonAction() {
						@Override
						public void act() throws IOException {
							currentStage.setScene(new Scene(new MainMenu()));
							currentStage.show();
						}
					})
			);
			mainMenuBox.setTranslateX(200);
			mainMenuBox.setTranslateY(700);

			Collections.sort(scoreBoard);
			ArrayList<MenuItem> myList = new ArrayList<>();
			if (scoreBoard.size() > 0) {
				for (int i = scoreBoard.size() - 1; i >= 0 && i > scoreBoard.size() - 11; i--) {
					myList.add(new MenuItem(scoreBoard.get(i).getKey() + " - " + scoreBoard.get(i).getValue(), new PassiveMenuItem(), 270, 30));
				}
			}

			MenuBox leaderList = new MenuBox(myList);
			leaderList.setTranslateX(173);
			leaderList.setTranslateY(170);
			getChildren().addAll(mainMenubg,title,leaderList, mainMenuBox);
		}
	}

	/**
	 * Main Menu class that defines the Main Menu of the game.
	 * Allows players to access the start of the game, the Leaderboard and the information of the game as well as exit the game on click.
	 */
	private class MainMenu extends Pane{

		/**
		 * Main menu class constructor.
		 * Creates an instance of MainMenu with clickable items.
		 * @throws IOException
		 */
		public MainMenu() throws IOException {

			Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));
			BackgroundImage mainMenubg = new BackgroundImage("file:src/main/resources/images/arcade_bg.jpg");

			Title title = new Title ("F R O G G E R");
			title.setTranslateX(117);
			title.setTranslateY(200);


			MenuBox vbox = new MenuBox(
					new MenuItem("Start Game", new ButtonAction() {
						@Override
						public void act() throws IOException {
							changeLevel(new LevelType1(), currentStage, frog);
						}
					}),
					new MenuItem("LeaderBoard", new ButtonAction() {
						@Override
						public void act() {
							currentStage.setScene(new Scene(new LeaderBoard()));
							currentStage.show();
						}
					}),
					new MenuItem("Info", new ButtonAction() {
						@Override
						public void act() throws IOException {

							Stage infoStage = new Stage();
							infoStage.setScene(new Scene(root));
							infoStage.setTitle("Information");
							infoStage.showAndWait();
							currentStage.setScene(new Scene(new MainMenu()));
						}
					}),

					new MenuItem("Exit Game", new ButtonAction() {
						@Override
						public void act() {
							Platform.exit();
						}
					})
			);

			vbox.setTranslateX(200);
			vbox.setTranslateY(350);
			getChildren().addAll(mainMenubg,title,vbox);

		}
	}

	/**
	 * PauseMenu class defines a Pause Menu with items to let players restart the game,
	 * resume the game, exit the game and return to Main Menu upon a click.
	 */
	private class Menu extends Pane{

		/**
		 * Class constructor for Menu.
		 * Creates and instance of Menu to list the menu items that can be accessed by the player
		 * which includes the Resume, Restart, Exit and Exit to Main Menu buttons.
		 * @param pauseStage The separate window (Stage) that displays the pause menu
		 */
		public Menu(Stage pauseStage){

			ImageView image = new ImageView("file:src/main/resources/images/bg.jpg");

			Text text = new Text("GAME PAUSED");
			text.setTranslateY(65);
			text.setTranslateX(68);
			text.setTextAlignment(TextAlignment.CENTER);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 28));


			MenuBox vbox = new MenuBox(
					new MenuItem("Resume Game", new ButtonAction() {
						@Override
						public void act() throws IOException {
							pauseStage.close();
							level.start();
							frog.blockInputControls = false;
							mediaPlayer.playMusic();

						}
					}),
					new MenuItem("Restart", new ButtonAction() {
						@Override
						public void act() throws IOException {
							frog.reset();
							frog.respawn(false);
							changeLevel(new LevelType1(), currentStage, frog);
							pauseStage.close();

						}
					}),
					new MenuItem("Exit To Main Menu", new ButtonAction() {
						@Override
						public void act() throws IOException {
							frog.reset();
							frog.respawn(false);
							currentStage.setScene(new Scene(new MainMenu()));
							currentStage.show();
							pauseStage.close();
							mediaPlayer.stopMusic();
						}
					}),
					new MenuItem("Exit", new ButtonAction() {
						@Override
						public void act() {
							pauseStage.close();
							mediaPlayer.stopMusic();
							Platform.exit();
						}
					})
			);

			vbox.setTranslateX(63);
			vbox.setTranslateY(100);
			getChildren().addAll(image,text,vbox);

		}
	}

	/**
	 * Static method of the Application class.
	 * Launches the application.
	 * @param args Argument passed to the application
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Method that runs whenever the application launches.
	 * Starts the application and shows the stage (window).
	 * @param primaryStage Stage that shows whenever the program starts
	 * @throws Exception upon error in starting the application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		loadsScore();
		currentUser = getComputerName();
		System.out.println("COMPUTER NAME: " + getComputerName());
		currentStage = primaryStage;
		currentStage.setTitle("FROGGER");
		currentStage.setScene(new Scene( new MainMenu()));
		currentStage.show();

	}

	/**
	 * This method starts or changes and displays the levels of the game each time it is called.
	 * The method adds the game menu into the scene so players will only have access to the mid game menu once the level starts.
	 * This method also adds the animal into the scene at the start of each level.
	 * @param myLevel The level that the game is supposed to change to
	 * @param currentStage The current window running the game
	 * @param frog The animal in the game
	 * @throws IOException upon input error
	 */
	public void changeLevel(MyLevel myLevel, Stage currentStage, Animal frog) throws IOException{

		level = myLevel;
		MenuBox menu = new MenuBox(
				new MenuItem("Menu", new ButtonAction() {
					@Override
					public void act() throws IOException {
						mediaPlayer.stopMusic();
						level.stop();
						frog.blockInputControls = true;
						Stage pauseStage = new Stage();
						pauseStage.setScene(new Scene(new Menu(pauseStage)));
						pauseStage.setHeight(350);
						pauseStage.setWidth(350);
						pauseStage.show();
					}
				}), false
		);
		menu.setTranslateX(430);
		menu.setTranslateY(755);
		level.getChildren().add(menu);
		level.add(frog);
		currentStage.setScene(new Scene(level, 600, 800));
		currentStage.show();
		startGame();

	}

	/**
	 * This method saves the score of the player in a ScorePair.
	 * The score will be stored in a ScorePair array list.
	 * The array of scores will be written into the 'LeaderBoard' file each time.
	 * @throws IOException upon error in reading/loading files
	 */
	public void saveScore() throws IOException{

		ScorePair score = new ScorePair(currentUser, frog.getTotalPoints());
		scoreBoard.add(score);
		FileOutputStream leaderboard_fos = new FileOutputStream("LeaderBoard");
		ObjectOutputStream leaderboard_oos = new ObjectOutputStream(leaderboard_fos);
		leaderboard_oos.writeObject(scoreBoard);

		System.out.println("Score Successfully Saved");

	}

	/**
	 * Resets the animal to its original state, restores health and scores.
	 * Returns the scene of the game back to the Main Menu.
	 * @throws IOException upon error in resetting game
	 */
	public void resetGame() throws IOException {
		frog.reset();
		currentStage.setScene(new Scene(new MainMenu()));
		currentStage.show();
	}

	/**
	 * This methods loads the score that was stored in the LeaderBoard file from the previous game.
	 * Scores are read from the file and stored into the ScorePair ArrayList to be displayed in the LeaderBoard.
	 */
	public void loadsScore() {

		try{
			FileInputStream leaderboard_fis = new FileInputStream("LeaderBoard");
			ObjectInputStream leaderboard_ois = new ObjectInputStream(leaderboard_fis);
			scoreBoard = (ArrayList<ScorePair>) leaderboard_ois.readObject();
			System.out.println("LeaderBoard Loaded From Save");
		}
		catch (Exception e){
			System.out.println("Error in Loading LeaderBoard");
			//e.printStackTrace();
		}

	}


	/**
	 * This method creates the timer for the game and the timer will be called in each frame while active
	 * The handle method inherited from AnimationTimer is overridden to check the points of the player and health of the frog in each frame
	 * It also checks if the player has won or lost the round so the game can proceed to the next level or end.
	 * This is called in each timestamp of the frame (in nanoseconds)
	 */
	public void createTimer() {
		timer = new AnimationTimer() {
			int currentPoints = 0;
			int currentlives = 8;
			@Override
			public void handle(long now) {

				if (currentPoints != frog.getTotalPoints()) {
					currentPoints = frog.getTotalPoints();
					level.setNumber(currentPoints);

				}
				if(frog.getLives() != 0){
					if (currentlives > frog.getLives()) {
						currentlives = frog.getLives();
						level.setNumberOfLives(currentlives);
					}
				}

				if(frog.getLives() == 0){
					level.removeLives();
					System.out.print("GAME OVER\n");
					mediaPlayer.stopMusic();
					mediaPlayer.playGameOverMusic();
					stopGame();
					Alert alert = new Alert(AlertType.NONE, "Your Total Score: " + frog.getTotalPoints() + "!", ButtonType.CLOSE);
					alert.setTitle("You Have Lost The Game!");
					alert.show();
					try{
						saveScore();
					}catch (Exception e){
						e.printStackTrace();
					}
					try {
						resetGame();
					} catch (IOException e) {
						e.printStackTrace();
					}
					currentStage.setScene(new Scene(new LeaderBoard()));
					currentStage.show();
				}

				if (frog.hasWon()) {
					frog.resetEnds();
					mediaPlayer.stopMusic();
					mediaPlayer.playChangeLevelMusic();
					stopGame();
					try {
						System.out.println("This is : " + level.getNextLevel().getClass().getSimpleName());
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
					try{
						changeLevel(level.getNextLevel(), currentStage, frog);
					}
					catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		};
	}

	/**
	 * This method starts the game.
	 * It calls for the creation of the timer and starts the timer.
	 * The background music of the game will also start playing when this method is called.
	 */
	public void startGame() {
		((World) currentStage.getScene().getRoot()).start();
		level.setNumberOfLives(8);
		//level.playMusic();
		mediaPlayer.playMusic();
		createTimer();
		timer.start();
	}

	/**
	 * This method stops the game by calling for the stop of the timer.
	 */
	public void stopGame() {
		((World) currentStage.getScene().getRoot()).stop();
		timer.stop();
	}

	/**
	 * This method gets the name of the device the player is currently using.
	 * The method gets the name of the device from its specific environment variable.
	 * @return Name of the device
	 */
	public String getComputerName(){
		Map<String, String> env = System.getenv();
		if (env.containsKey("COMPUTERNAME")){
			return env.get("COMPUTERNAME");
		}
		else return env.getOrDefault("HOSTNAME","UNKNOWN");
	}
}
