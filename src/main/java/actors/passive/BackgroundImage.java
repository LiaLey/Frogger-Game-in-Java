package actors.passive;

import actors.Actor;
import javafx.scene.image.Image;

public class BackgroundImage extends Actor {

	@Override
	public void act(long now) {
		
		
	}
	
	public BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, 612.5, 800, false, true));
		
	}

}
