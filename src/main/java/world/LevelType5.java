package world;

import actors.active.*;
import actors.passive.Digit;
import actors.passive.End;

public class LevelType5 extends MyLevel{
    static int turn = 0;

    //public static Class myNextLevel = Level1.class;
    public static Class myNextLevel = LevelType1.class;

    public LevelType5() {
        super(myNextLevel);
        //System.out.println(myNextLevel);
//        turn++;
//        if (turn == 3) {
//            myNextLevel = null;
//        }

        this.add(new Log("file:src/main/java/images/log3.png", 150, 0, 166, 0.75));
        this.add(new Log("file:src/main/java/images/log3.png", 150, 220, 166, 0.75));
        this.add(new Log("file:src/main/java/images/log3.png", 150, 440, 166, 0.75));
        this.add(new Log("file:src/main/java/images/logs.png", 250, 50, 276, -3));
        this.add(new Log("file:src/main/java/images/logs.png", 250, 450, 276, -3));
        this.add(new Log("file:src/main/java/images/log3.png", 150, 50, 329, 0.75));
        this.add(new Log("file:src/main/java/images/log3.png", 150, 270, 329, 0.75));
        this.add(new Log("file:src/main/java/images/log3.png", 150, 490, 329, 0.75));
        this.add(new WetTurtle(500, 376, -1, 130, 130));
        this.add(new WetTurtle(300, 376, -1, 130, 130));
        this.add(new Turtle(700, 376, -1, 130, 130));
        this.add(new WetTurtle(600, 217, -1, 130, 130));
        this.add(new WetTurtle(400, 217, -1, 130, 130));
        this.add(new WetTurtle(200, 217, -1, 130, 130));
        this.add(new Truck("file:src/main/java/images/truck1"+"Right.png", 0, 649, 3, 120, 120));
        this.add(new Truck("file:src/main/java/images/truck1"+"Right.png", 300, 649, 3, 120, 120));
        this.add(new Truck("file:src/main/java/images/truck1"+"Right.png", 600, 649, 3, 120, 120));
        this.add(new Car(100, 597, -1, 50, 50));
        this.add(new Car(250, 597, -1, 50, 50));
        this.add(new Car(400, 597, -1, 50, 50));
        this.add(new Car(550, 597, -1, 50, 50));
        this.add(new Truck("file:src/main/java/images/truck2Right.png", 0, 540, 1, 200, 200));
        this.add(new Truck("file:src/main/java/images/truck2Right.png", 500, 540, 1, 200, 200));
        this.add(new Car(500, 490, -5, 50, 50));

        this.add(new Digit(0, 30, 575, 0));

        this.add(new End(13,96));
        this.add(new End(141,96));
        this.add(new End(141 + 141-13,96));
        this.add(new End(141 + 141-13+141-13+1,96));
        this.add(new End(141 + 141-13+141-13+141-13+3,96));


    }

}
