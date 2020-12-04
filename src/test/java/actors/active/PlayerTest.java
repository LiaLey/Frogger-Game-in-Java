package actors.active;

import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Junit Test for Player
 */
public class PlayerTest {


    private Player player;
    private JFXPanel panel = new JFXPanel();

    /**
     * Test lives reset
     */
    @Test
    public void testLivesReset() {

        player = new Player();
        int health = player.getLives();
        Assert.assertEquals(8, health);   ///changed to 8

        player.lives = 2;
        Assert.assertEquals(2, player.getLives());

        player.reset();
        Assert.assertEquals(8, player.getLives());

    }

    /**
     * Test points reset
     */
    @Test
    public void testTotalPointsReset() {

        player = new Player();
        int points = player.getTotalPoints();
        Assert.assertEquals(-93, points);

        player.heightPoints = -63;
        Assert.assertEquals(-63, player.getTotalPoints());

        player.reset();
        Assert.assertEquals(-93, player.getTotalPoints());


    }

    /**
     * Test method HasWon()
     */
    @Test
    public void testHasWon() {

        player = new Player();
        int endsActivated = player.numberOfEndsActivated;
        Assert.assertEquals(0, endsActivated);

        Assert.assertFalse(player.hasWon());

        player.numberOfEndsActivated = 2;
        Assert.assertTrue(player.hasWon());

    }

    /**
     * Test max range reset
     */
    @Test
    public void testMaxRangeReset() {

        player = new Player();
        int maxRange = player.maxRangeAchieved;
        Assert.assertEquals(800, maxRange);

        player.maxRangeAchieved += 1230;
        Assert.assertEquals(2030, player.maxRangeAchieved);

        player.reset();
        Assert.assertEquals(800, player.maxRangeAchieved);

    }
}