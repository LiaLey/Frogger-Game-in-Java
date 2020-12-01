package actors.active;

import javafx.embed.swing.JFXPanel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit Test for AnimationIterator
 */
public class AnimationIteratorTest {

    private AnimationIterator upAnimation;
    private AnimationIterator waterDeathAnimation;
    private AnimationIterator carDeathAnimation;
    private AnimationIterator downAnimation;
    private JFXPanel panel = new JFXPanel();

    /**
     * Test setting animation
     */
    @Test
    public void testSet() {

        upAnimation = new AnimationIterator(AnimationType.UP);
        int length = upAnimation.animations.size();
        assertEquals(2, length);

    }

    /**
     * Test index
     */
    @Test
    public void testNextIndex() {

        waterDeathAnimation = new AnimationIterator(AnimationType.WATER_DEATH);
        int length = waterDeathAnimation.animations.size();
        assertEquals(4, length);

        int next = waterDeathAnimation.nextIndex();
        assertEquals(1, next);


    }

    /**
     * Test index reset
     */
    @Test
    public void testResetIndex() {

        carDeathAnimation = new AnimationIterator(AnimationType.CAR_DEATH);
        int length = carDeathAnimation.animations.size();
        assertEquals(3, length);

        carDeathAnimation.cycleIndex();
        carDeathAnimation.cycleIndex();
        assertEquals(2, carDeathAnimation.index);

        carDeathAnimation.resetCycle();
        assertEquals(0, carDeathAnimation.index);


    }

    /**
     * Test auto cycle of index
     */
    @Test
    public void testAutoCycle() {

        waterDeathAnimation = new AnimationIterator(AnimationType.WATER_DEATH);
        int length = waterDeathAnimation.animations.size();
        assertEquals(4, length);

        waterDeathAnimation.cycleIndex();
        waterDeathAnimation.cycleIndex();
        assertEquals(2, waterDeathAnimation.index);

        waterDeathAnimation.cycleIndex();
        waterDeathAnimation.cycleIndex();
        assertEquals(0, waterDeathAnimation.index);


    }
}