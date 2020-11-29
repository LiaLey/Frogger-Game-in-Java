package actors.passive;


import actors.Actor;
import javafx.scene.image.Image;

public class Lives extends Actor{
    @Override
    public void act(long now) {

    }

    public Lives(int dim, int x, int y) {
        setImage(new Image("file:src/main/java/images/froggerUp.png", dim, dim, true, true));
        setX(x);
        setY(y);
    }
}
