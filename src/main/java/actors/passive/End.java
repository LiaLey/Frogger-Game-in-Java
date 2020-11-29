package actors.passive;

import actors.Actor;
import javafx.scene.image.Image;

public class End extends Actor {
	boolean activated = false;
	@Override
	public void act(long now) {
		// TODO Auto-generated method st
	}
	
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:src/main/java/images/End.png", 60, 60, true, true));
	}
	
	public void setEnd() {
		setImage(new Image("file:src/main/java/images/FrogEnd.png", 70, 70, true, true));
		activated = true;
	}
	
	public boolean isActivated() {
		return activated;
	}

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
