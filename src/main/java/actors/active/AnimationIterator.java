package actors.active;

import javafx.scene.image.Image;

import java.util.ArrayList;

enum AnimationType {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    WATER_DEATH,
    CAR_DEATH
}


/**
 * Assumes that the first index in animations list is the resting position
 */
class AnimationIterator {
    public final ArrayList<Image> animations = new ArrayList<>();
    protected int index = 0;
    protected AnimationType type;

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

    protected void cycleIndex() {
        if (index == animations.size() - 1)
            index = 0;
        else index++;
    }

    protected int nextIndex() {
        int tempIndex = index;
        if (tempIndex == animations.size() - 1)
            tempIndex = 0;
        else tempIndex++;
        return tempIndex;
    }

    protected Image next() {
        Image res = animations.get(index);
        cycleIndex();
        return res;
    }

    protected Image restingPosition() {
        index = 1;      // allows for non-resting animation immediately after resuming from rest
        return animations.get(0);
    }

    protected void resetCycle() {
        index = 0;
    }
}
