package actors.active;

import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit test for WetTurtle
 */
public class WetTurtleTest {

    private WetTurtle wetTurtle;
    private JFXPanel panel = new JFXPanel();

    /**
     * Test set position
     */
    @Test
    public void testSetPos() {
        wetTurtle = new WetTurtle(500, 376, -1, 130, 130);
        double xPos = wetTurtle.getX();
        double yPos = wetTurtle.getY();
        assertEquals(500, xPos, 0.0);
        assertEquals(376, yPos, 0.0);

    }

    /**
     * Test set speed, get speed
     */
    @Test
    public void testSpeed() {
        wetTurtle = new WetTurtle(500, 376, -1, 130, 130);
        double expSpeed = wetTurtle.getSpeed();
        assertEquals(-1, expSpeed, 0.0);

        wetTurtle.speed = 2;
        assertEquals(2.0,wetTurtle.getSpeed(),0.0);

    }

    /**
     * Test method isSunk()
     */
    @Test
    public void testSunk() {
        wetTurtle = new WetTurtle(500, 376, -1, 130, 130);
        assertFalse(wetTurtle.isSunk());

    }


}