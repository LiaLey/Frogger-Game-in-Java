package actors;

import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import world.World;

import java.util.ArrayList;

/**
 * The Actor class defines the movements of each instance of the Actor class or its child class in the scene.
 * Checks for interaction of each of its instance by keeping track of their intersections.
 */
public abstract class Actor extends ImageView{

    /**
     * Constructor for Actor class.
     */
    public Actor(){

    }

    /**
     * Moves the actors to their given coordinates on the pane.
     * @param dx Value of increase/decrease (speed) on the horizontal plane
     * @param dy Value of increase/decrease (speed) on the vertical plane
     */
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * This method returns the parent of the scene when called.
     * @return World
     */
    public World getWorld() {
        return (World) getParent();
    }

//    public double getWidth() {
//        return this.getBoundsInLocal().getWidth();
//    }
//
//    public double getHeight() {
//        return this.getBoundsInLocal().getHeight();
//    }

    /**
     * This method will return an Arraylist of actors that are intersecting when called.
     * @param cls The class of objects that is being checked for intersections
     * @param <A>  Any class that extends from actors
     * @return An arrayList of objects intersecting with the object that calls the method
     */
    public <A extends Actor> ArrayList<A> getIntersectingObjects(Class<A> cls){
        ArrayList<A> someArray = new ArrayList<A>();
        for (A actor: getWorld().getObjects(cls)) {
            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
                someArray.add(actor);
            }
        }
        return someArray;
    }
    
//    public void manageInput(InputEvent e) {
//
//    }

//    public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
//        ArrayList<A> someArray = new ArrayList<A>();
//        for (A actor: getWorld().getObjects(cls)) {
//            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
//                someArray.add(actor);
//                break;
//            }
//        }
//        return someArray.get(0);
//    }

    /**
     * Abstract method of Actor class that should be overridden by inheriting classes.
     * Defines how the instances of Actors should act in each timeframe.
     * @param now The timestamp of current frame in nanoseconds
     */
    public abstract void act(long now);

}
