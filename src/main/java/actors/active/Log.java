package actors.active;
import actors.Actor;

import javafx.scene.image.Image;

public class Log extends Actor {

	public double speed;
	@Override
	public void act(long now) {
		move(speed , 0);
		if (getX()>600 && speed>0)
			setX(-100);
		if (getX()<-300 && speed<0)
			setX(700);
	}
	
	public Log(String imageLink, int size, int xpos, int ypos, double speed) {
		setImage(new Image(imageLink, size,size, true, true));
		setX(xpos);
		setY(ypos);
		this.speed = speed;
		
	}
	public double getSpeed() {
		return this.speed;
	}

}

