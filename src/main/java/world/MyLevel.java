package world;
import actors.passive.BackgroundImage;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import actors.passive.Digit;
import actors.passive.Lives;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MyLevel extends World{
	MediaPlayer mediaPlayer;
	Class<MyLevel> nextLevel;
	ArrayList<Lives> lives = new ArrayList<>();
	@Override
	public void act(long now) {
		
	}
	
	public MyLevel(Class<MyLevel> nextLevel) {
		

		BackgroundImage froggerback = new actors.passive.BackgroundImage("file:src/main/java/images/iKogsKW2.png");
		this.add(froggerback);
		this.nextLevel = nextLevel;
	}

	public void setNumberOfHearts(int numberOfHearts){
		getChildren().removeIf(x -> x instanceof Lives);
		lives.clear();
		int x = 10;
		for (int i = 0; i < numberOfHearts; i++) {
			Lives temp = new Lives(35, x, 755);
			x += 40;
			lives.add(temp);
		}
		this.addHealth(lives);
	}

	public void removeHearts(){
		this.removeHealth(lives);
	}


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

	public MyLevel getNextLevel() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		return nextLevel.getConstructor().newInstance();
	}

}
