package actors.passive;

import actors.active.WetTurtle;
import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit Test for End Class
 */
public class EndTest {

    private End end;
    private JFXPanel panel = new JFXPanel();

    /**
     * Test for method isActivated
     */
    @Test
    public void testSetActivate() {
        end = new End(13, 96);
        boolean activate = end.isActivated();
        assertFalse(activate);

        end.setEnd();
        assertTrue(end.isActivated());

    }

    /**
     * Test for toggle
     */
    @Test
    public void testToggleActivated() {

        end = new End(13, 96);
        boolean activate = end.isActivated();
        assertFalse(activate);

        end.setEnd();
        assertTrue(end.isActivated());

        end.toggle();
        assertFalse(end.isActivated());

        end.toggle();
        assertTrue(end.isActivated());


    }

    /**
     * Test for setting position
     */
    @Test
    public void testSetPos() {
        end = new End(13, 96);
        double xPos = end.getX();
        double yPos = end.getY();
        assertEquals(13, xPos, 0.0);
        assertEquals(96, yPos, 0.0);

    }

}