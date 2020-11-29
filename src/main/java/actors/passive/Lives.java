package actors.passive;


import actors.Actor;
import javafx.scene.image.Image;

/**
 * This class sets the display of Lives of the Frog
 */
public class Lives extends Actor{

    /**
     * This method will set how the Lives is supposed to act
     * This method is inherited from the parent class Actors and should be overridden
     * In this game the Lives does not act or do anything so the method is empty
     * @param now the timestamp of the frame in nanoseconds
     */
    @Override
    public void act(long now) {

    }

    /**
     * Class costructor for the Lives
     * Creates an instance of Lives with the given position
     * @param size size of the Lives image
     * @param x x-coordinates of the Lives image
     * @param y y-coordinates of the Lives image
     */
    public Lives(int size, int x, int y) {
        setImage(new Image("file:src/main/java/images/froggerUp.png", size, size, true, true));
        setX(x);
        setY(y);
    }
}
