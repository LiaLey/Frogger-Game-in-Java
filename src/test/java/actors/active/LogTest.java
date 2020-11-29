package actors.active;

import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogTest {

    private Log log;
    private JFXPanel panel = new JFXPanel();


    @Test
    public void testSetSpeed() {
        log = new Log("file:src/main/java/images/log3.png", 150, 0, 166, 0.75);
        double expSpeed = log.getSpeed();
        assertEquals(0.75, expSpeed, 0.0);

        log.speed = 2;
        assertEquals(2, log.getSpeed(), 0.0);

    }

    @Test
    public void testSetPos() {
        log = new Log("file:src/main/java/images/log3.png", 150, 0, 166, 0.75);
        double xPos = log.getX();
        double yPos = log.getY();
        assertEquals(0, xPos, 0.0);
        assertEquals(166, yPos, 0.0);


    }

}