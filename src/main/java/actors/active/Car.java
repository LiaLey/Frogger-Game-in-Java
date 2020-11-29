package actors.active;

import actors.Actor;

import javafx.scene.image.Image;


public class Car extends Actor {

    private int speed;

    @Override
    public void act(long now) {
        move(speed , 0);
        if (getX() > 600 && speed>0)
            setX(-250);
        if (getX() < -50 && speed<0)
            setX(600);
    }

    public Car(int xpos, int ypos, int s, int w, int h) {

        setImage(new Image("file:src/main/java/images/car1Left.png", w,h, true, true));
        setX(xpos);
        setY(ypos);
        speed = s;
    }
}
