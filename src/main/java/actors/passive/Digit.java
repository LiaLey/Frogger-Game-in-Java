package actors.passive;

import actors.Actor;
import javafx.scene.image.Image;

public class Digit extends Actor {
	int dim;
	//Image im1;
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	public Digit(int n, int dim, int x, int y) {
		setImage(new Image("file:src/main/java/images/"+n+".png", dim, dim, true, true));
		setX(x);
		setY(y);
	}
	
}
