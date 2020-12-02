package actors.active;

import actors.Actor;

import javafx.scene.image.Image;

/**
 * This class defines the Car obstacle in the game.
 * Extended from Actor class.
 */
public class Car extends Actor {

    /**
     * Speed of the car
     */
    private int speed;

    /**
     * This method moves the car when it is called.
     * This method is overridden from its parent class and define how the car moves in each frame.
     * @param now The timestamp of each frame of the game in nanoseconds
     */
    @Override
    public void act(long now) {
        move(speed , 0);
        if (getX() > 600 && speed>0)
            setX(-250);
        if (getX() < -50 && speed<0)
            setX(600);
    }

    /**
     * Class constructor for Car.
     * Creates an instance of Car with given position, speed and size.
     * @param xpos x-coordinate of the car
     * @param ypos y-coordinate of the car
     * @param speed Speed of the car
     * @param w Width of the car
     * @param h Height of the car
     */
    public Car(int xpos, int ypos, int speed, int w, int h) {

        setImage(new Image("file:src/main/resources/images/car1Left.png", w,h, true, true));
        setX(xpos);
        setY(ypos);
        this.speed = speed;
    }
}
