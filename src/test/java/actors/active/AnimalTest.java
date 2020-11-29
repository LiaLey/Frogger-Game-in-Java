package actors.active;

import actors.Actor;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class AnimalTest {


    private Animal animal;
    private JFXPanel panel = new JFXPanel();

    @Test
    public void testgetHealthReset() {

        animal = new Animal("file:src/main/java/images/froggerUp.png");
        int health = animal.getHealth();
        Assert.assertEquals(8, health);   ///changed to 8

        animal.health = 2;
        Assert.assertEquals(2, animal.getHealth());

        animal.reset();
        Assert.assertEquals(8, animal.getHealth());

    }

    @Test
    public void testgetTotalPointsReset() {

        animal = new Animal("file:src/main/java/images/froggerUp.png");
        int points = animal.getTotalPoints();
        Assert.assertEquals(-93, points);

        animal.heightPoints = -63;
        Assert.assertEquals(-63, animal.getTotalPoints());

        animal.reset();
        Assert.assertEquals(-93, animal.getTotalPoints());


    }

    @Test
    public void testHasWon() {

        animal = new Animal("file:src/main/java/images/froggerUp.png");
        int endsActivated = animal.numberOfEndsActivated;
        Assert.assertEquals(0, endsActivated);

        Assert.assertFalse(animal.hasWon());

        animal.numberOfEndsActivated = 2;
        Assert.assertTrue(animal.hasWon());

    }

    @Test
    public void testMaxRangeReset() {

        animal = new Animal("file:src/main/java/images/froggerUp.png");
        int maxRange = animal.maxRangeAchieved;
        Assert.assertEquals(800, maxRange);

        animal.maxRangeAchieved += 1230;
        Assert.assertEquals(2030, animal.maxRangeAchieved);

        animal.reset();
        Assert.assertEquals(800, animal.maxRangeAchieved);

    }
}