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


//Comparable for serialising
class ScorePair<String,Integer> extends Pair<String, Integer> implements Comparable{
	public ScorePair(String user, Integer score){
		super(user,score);
	}

	@Override
	public int compareTo(Object o) {
		int compareScore = (int) ((ScorePair) o).getValue();
		return (int) this.getValue() - compareScore;
	}
}

public class Main extends Application {
	AnimationTimer timer;
	Animal animal = new Animal("file:src/main/java/images/froggerUp.png");
	MyLevel level; //test
	Stage currentStage;
	String currentUser;
	MediaPlay mediaPlayer = new MediaPlay();
	ArrayList<Lives> lives = new ArrayList<>(); //test
	ArrayList<ScorePair> scoreBoard = new ArrayList<>();

	//Parent root ;

	private class LeaderBoard extends Pane {
		private class PassiveMenuItem extends ButtonAction{
			@Override
			public void act() throws IOException {}
		}

		public LeaderBoard(){
			BackgroundImage mainMenubg = new BackgroundImage("file:src/main/java/images/bg.jpg");
			//getChildren().add(mainMenubg);

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

	private class MainMenu extends Pane{
		public MainMenu() throws IOException {

			Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));


			BackgroundImage mainMenubg = new BackgroundImage("file:src/main/java/images/arcade_bg.jpg");
			//getChildren().add(mainMenubg);

			Title title = new Title ("F R O G G E R");
			title.setTranslateX(117);
			title.setTranslateY(200);


			MenuBox vbox = new MenuBox(
					new MenuItem("Start Game", new ButtonAction() {
						@Override
						public void act() throws IOException {
							changeLevel(new LevelType1(), currentStage, animal);
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

	private class Menu extends Pane{
		public Menu(Stage pauseStage){
			//BackgroundImage mainMenubg = new BackgroundImage("file:src/images/arcade_bg.jpg");
			//getChildren().add(mainMenubg);
			ImageView image = new ImageView("file:src/main/java/images/bg.jpg");

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
							animal.blockInputControls = false;
							mediaPlayer.playMusic();

						}
					}),
					new MenuItem("Restart", new ButtonAction() {
						@Override
						public void act() throws IOException {
							animal.reset();
							animal.respawn(false);
							changeLevel(new LevelType1(), currentStage, animal);
							pauseStage.close();

						}
					}),
					new MenuItem("Exit To Main Menu", new ButtonAction() {
						@Override
						public void act() throws IOException {
							animal.reset();
							animal.respawn(false);
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

	public static void main(String[] args) {
		launch(args);
	}

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

	public void changeLevel(MyLevel myLevel, Stage currentStage, Animal animal) throws IOException{

		level = myLevel;
		MenuBox menu = new MenuBox(
				new MenuItem("Menu", new ButtonAction() {
					@Override
					public void act() throws IOException {
						mediaPlayer.stopMusic();
						level.stop();
						animal.blockInputControls = true;
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
		level.add(animal);
		currentStage.setScene(new Scene(level, 600, 800));
		currentStage.show();
		startGame();

	}

	public void saveScore() throws IOException{

		ScorePair score = new ScorePair(currentUser, animal.getTotalPoints());
		scoreBoard.add(score);
		FileOutputStream leaderboard_fos = new FileOutputStream("LeaderBoard");
		ObjectOutputStream leaderboard_oos = new ObjectOutputStream(leaderboard_fos);
		leaderboard_oos.writeObject(scoreBoard);

		System.out.println("Score Successfully Saved");

	}

	public void resetGame() throws IOException {
		animal.reset();
		currentStage.setScene(new Scene(new MainMenu()));
		currentStage.show();
	}

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


	public void createTimer() {
		timer = new AnimationTimer() {
			int currentPoints = 0;
			int currentHealth = 8;

			@Override
			public void handle(long now) {

				if (currentPoints != animal.getTotalPoints()) {
					currentPoints = animal.getTotalPoints();
					level.setNumber(currentPoints);

				}
				if(animal.getHealth() != 0){
					if (currentHealth > animal.getHealth()) {
						currentHealth = animal.getHealth();
						level.setNumberOfHearts(currentHealth);
					}
				}

				if(animal.getHealth() == 0){
					level.removeHearts();
					System.out.print("GAME OVER");
					mediaPlayer.stopMusic();
					mediaPlayer.playGameOverMusic();
					stopGame();
					Alert alert = new Alert(AlertType.NONE, "Your Total Score: " + animal.getTotalPoints() + "!", ButtonType.CLOSE);
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

				if (animal.hasWon()) {
					animal.resetEnds();
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
						changeLevel(level.getNextLevel(), currentStage, animal);
					}
					catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		};
	}
	public void startGame() {
		((World) currentStage.getScene().getRoot()).start();
		level.setNumberOfHearts(8);
		//level.playMusic();
		mediaPlayer.playMusic();
		createTimer();
		timer.start();
	}

	public void stopGame() {
		((World) currentStage.getScene().getRoot()).stop();
		timer.stop();
	}

	public String getComputerName(){
		Map<String, String> env = System.getenv();
		if (env.containsKey("COMPUTERNAME")){
			return env.get("COMPUTERNAME");
		}
		else return env.getOrDefault("HOSTNAME","UNKNOWN");
	}
}
