package actors;

import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import world.World;

import java.util.ArrayList;

/**
 * This the Actor class that sets the movements of each child of the Actor in the scene
 * The Actor class also checks for interaction of each actor in the scene by keeping track of intersections of objects in the scene
 */
public abstract class Actor extends ImageView{

    /**
     * Moves the actors to their given coordinates in the scene
     * @param dx value of increase/decrease (speed) on the horizontal plane
     * @param dy value of increase/decrease (speed) on the vertical plane
     */
    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * This method returns the parent node of the actor when called
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
     * This method will return an arraylist of actors that are intersecting when called
     * @param cls The class of objects that is being checked for intersections
     * @param <A>  Any class that extends from actors
     * @return
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
     * Abstract method of Actor class that should be overridden by inheriting classes
     * @param now the timestamp of current frame in nanoseconds
     */
    public abstract void act(long now);

}
