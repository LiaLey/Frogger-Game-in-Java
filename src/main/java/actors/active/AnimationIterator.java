package actors.active;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Animation types that can be used
 * {@link #UP}
 * {@link #DOWN}
 * {@link #LEFT}
 * {@link #RIGHT}
 * {@link #WATER_DEATH}
 * {@link #CAR_DEATH}
 */
enum AnimationType {
    /**
     * Up movement animation
     */
    UP,
    /**
     * Down movement animation
     */
    DOWN,
    /**
     * Left movement animation
     */
    LEFT,
    /**
     * Right movement animation
     */
    RIGHT,
    /** Water Death animation
     *
     */
    WATER_DEATH,
    /**
     * Car Death animation
     */
    CAR_DEATH
}


/**
 * Iterates an ArrayList of different images to form a complete animation for movements.
 * Assumes that the first index in animations list is the resting position.
 * Therefore the resting position image will be at index zero of the ArrayList.
 */
public class AnimationIterator {
    /**
     * ArrayList to store images
     */
    protected final ArrayList<Image> animations = new ArrayList<>();
    /**
     * Index of the ArrayList
     */
    protected int index = 0;
    /**
     * Type of animation to display
     */
    protected AnimationType type;

    /**
     * Class Constructor.
     * Creates an instance of AnimationIterator with given animation type.
     * Stores the images into an ArrayList to iterate.
     * @param animation The types of animations that can be used to construct the animation iterator
     */
    public AnimationIterator(AnimationType animation) {
        type = animation;
        if (animation == AnimationType.UP) {
            animations.add(new Image("file:src/main/resources/images/froggerUp.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/froggerUpJump.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.DOWN) {
            animations.add(new Image("file:src/main/resources/images/froggerDown.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/froggerDownJump.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.LEFT) {
            animations.add(new Image("file:src/main/resources/images/froggerLeft.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/froggerLeftJump.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.RIGHT) {
            animations.add(new Image("file:src/main/resources/images/froggerRight.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/froggerRightJump.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.WATER_DEATH) {
            animations.add(new Image("file:src/main/resources/images/waterdeath1.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/waterdeath2.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/waterdeath3.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/waterdeath4.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.CAR_DEATH) {
            animations.add(new Image("file:src/main/resources/images/cardeath1.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/cardeath2.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/resources/images/cardeath3.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        }
    }

    /**
     * This method loops the index of the ArrayList of images.
     * The index is added by 1 each time until it reaches the end of the array.
     */
    protected void cycleIndex() {
        if (index == animations.size() - 1)
            index = 0;
        else index++;
    }

    /**
     * This method returns the next index in the ArrayList of images when called.
     * @return int - The index of the next image in the ArrayList
     */
    protected int nextIndex() {
        int tempIndex = index;
        if (tempIndex == animations.size() - 1)
            tempIndex = 0;
        else tempIndex++;
        return tempIndex;
    }

    /**
     * This method will return the next required image in the ArrayList.
     * @return Image - The next image in the array
     */
    protected Image next() {
        Image res = animations.get(index);
        cycleIndex();
        return res;
    }

    /**
     * This method will return the image of the frog in resting position.
     * The resting position image will be at index zero of the ArrayList.
     * @return Image - The resting position image of the frog
     */
    protected Image restingPosition() {
        index = 1;      // allows for non-resting animation immediately after resuming from rest
        return animations.get(0);
    }

    /**
     * This method resets the cycle of images in the ArrayList and sets the index back to zero.
     */
    protected void resetCycle() {
        index = 0;
    }
}
