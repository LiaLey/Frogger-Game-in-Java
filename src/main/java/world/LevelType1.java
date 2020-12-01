package world;

import actors.active.Car;
import actors.active.Log;
import actors.active.Truck;
import actors.active.Turtle;
import actors.passive.Digit;
import actors.passive.End;

/**
 * This Class defines the settings and obstacles for the first level type in the game.
 * The class will also pass the next level type class to its parent class to be called during the change of levels.
 */
public class LevelType1 extends MyLevel {

    /**
     * next level to be displayed
     */
    public static Class myNextLevel = LevelType2.class;

    /**
     * Class constructor.
     * Creates an instance of LevelType1.
     */
    public LevelType1(){
        super(myNextLevel);

        this.add(new Log("file:src/main/resources/images/log3.png", 150, 0, 166, 0.75));
        this.add(new Log("file:src/main/resources/images/log3.png", 150, 220, 166, 0.75));
        this.add(new Log("file:src/main/resources/images/log3.png", 150, 440, 166, 0.75));
        this.add(new Log("file:src/main/resources/images/logs.png", 370, 0, 276, -0.75));
        this.add(new Log("file:src/main/resources/images/logs.png", 370, 400, 276, -0.75));
        this.add(new Log("file:src/main/resources/images/log3.png", 150, 50, 329, 0.75));
        this.add(new Log("file:src/main/resources/images/log3.png", 150, 270, 329, 0.75));
        this.add(new Log("file:src/main/resources/images/log3.png", 150, 490, 329, 0.75));
        this.add(new Log("file:src/main/resources/images/log3.png", 150, 570, 329, 0.75));
        this.add(new Turtle(500, 376, -1, 130, 130));
        this.add(new Turtle(300, 376, -1, 130, 130));
        this.add(new Turtle(700, 376, -1, 130, 130));
        this.add(new Turtle(600, 217, -1, 130, 130));
        this.add(new Turtle(400, 217, -1, 130, 130));
        this.add(new Turtle(200, 217, -1, 130, 130));
        this.add(new Truck("file:src/main/resources/images/truck1"+"Right.png", 300, 649, 1, 120, 120));
        this.add(new Car( 100, 597, -1, 50, 50));
        this.add(new Truck("file:src/main/resources/images/truck2Right.png", 0, 540, 1, 200, 200));
        this.add(new Car( 500, 490, -3, 50, 50));

        this.add(new Digit(0, 30, 575, 0));

        this.add(new End(13,96));
        this.add(new End(141,96));
        this.add(new End(141 + 141-13,96));
        this.add(new End(141 + 141-13+141-13+1,96));
        this.add(new End(141 + 141-13+141-13+141-13+3,96));

    }


}

    

