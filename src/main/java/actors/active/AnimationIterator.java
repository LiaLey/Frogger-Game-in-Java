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
 * Iterates an arrayList of different images to form a complete animation for movements.
 * Assumes that the first index in animations list is the resting position.
 * Therefore the resting position image will be at index 0 of the arrayList.
 */
class AnimationIterator {
    public final ArrayList<Image> animations = new ArrayList<>();
    protected int index = 0;
    protected AnimationType type;

    /**
     * Class Constructor.
     * Creates an instance of AnimationIterator with given animation type
     * Stores the images into an arrayList to iterate.
     * @param animation The types of animations that can be used to construct the animation iterator
     * @see AnimationType
     */
    protected AnimationIterator(AnimationType animation) {
        type = animation;
        if (animation == AnimationType.UP) {
            animations.add(new Image("file:src/main/java/images/froggerUp.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/froggerUpJump.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.DOWN) {
            animations.add(new Image("file:src/main/java/images/froggerDown.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/froggerDownJump.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.LEFT) {
            animations.add(new Image("file:src/main/java/images/froggerLeft.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/froggerLeftJump.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.RIGHT) {
            animations.add(new Image("file:src/main/java/images/froggerRight.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/froggerRightJump.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.WATER_DEATH) {
            animations.add(new Image("file:src/main/java/images/waterdeath1.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/waterdeath2.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/waterdeath3.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/waterdeath4.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        } else if (animation == AnimationType.CAR_DEATH) {
            animations.add(new Image("file:src/main/java/images/cardeath1.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/cardeath2.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
            animations.add(new Image("file:src/main/java/images/cardeath3.png", Animal.IMAGE_SIZE, Animal.IMAGE_SIZE, true, true));
        }
    }

    /**
     * This method loops the index of the arrayList of images.
     */
    protected void cycleIndex() {
        if (index == animations.size() - 1)
            index = 0;
        else index++;
    }

    /**
     * This method returns the next index in the arrayList of images when called.
     * @return int - the index of the next image in the arrayList
     */
    protected int nextIndex() {
        int tempIndex = index;
        if (tempIndex == animations.size() - 1)
            tempIndex = 0;
        else tempIndex++;
        return tempIndex;
    }

    /**
     * This method will return the next required image in the arrayList.
     * @return Image - the next image in the array
     */
    protected Image next() {
        Image res = animations.get(index);
        cycleIndex();
        return res;
    }

    /**
     * This method will return the image of the frog in resting position.
     * The resting position image will be at index 0 of the arrayList.
     * @return Image - the resting position image of the frog
     */
    protected Image restingPosition() {
        index = 1;      // allows for non-resting animation immediately after resuming from rest
        return animations.get(0);
    }

    /**
     * This method resets the cycle of images in the arrayList and sets the index back to 0.
     */
    protected void resetCycle() {
        index = 0;
    }
}
