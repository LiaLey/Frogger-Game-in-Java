package actors.active;

import javafx.embed.swing.JFXPanel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Junit Test for Animal
 */
public class AnimalTest {


    private Animal animal;
    private JFXPanel panel = new JFXPanel();

    /**
     * Test lives reset
     */
    @Test
    public void testLivesReset() {

        animal = new Animal();
        int health = animal.getLives();
        Assert.assertEquals(8, health);   ///changed to 8

        animal.lives = 2;
        Assert.assertEquals(2, animal.getLives());

        animal.reset();
        Assert.assertEquals(8, animal.getLives());

    }

    /**
     * Test points reset
     */
    @Test
    public void testTotalPointsReset() {

        animal = new Animal();
        int points = animal.getTotalPoints();
        Assert.assertEquals(-93, points);

        animal.heightPoints = -63;
        Assert.assertEquals(-63, animal.getTotalPoints());

        animal.reset();
        Assert.assertEquals(-93, animal.getTotalPoints());


    }

    /**
     * Test method HasWon()
     */
    @Test
    public void testHasWon() {

        animal = new Animal();
        int endsActivated = animal.numberOfEndsActivated;
        Assert.assertEquals(0, endsActivated);

        Assert.assertFalse(animal.hasWon());

        animal.numberOfEndsActivated = 2;
        Assert.assertTrue(animal.hasWon());

    }

    /**
     * Test max range reset
     */
    @Test
    public void testMaxRangeReset() {

        animal = new Animal();
        int maxRange = animal.maxRangeAchieved;
        Assert.assertEquals(800, maxRange);

        animal.maxRangeAchieved += 1230;
        Assert.assertEquals(2030, animal.maxRangeAchieved);

        animal.reset();
        Assert.assertEquals(800, animal.maxRangeAchieved);

    }
}