package world;

import java.util.ArrayList;
import java.util.List;

import actors.passive.Lives;
import javafx.scene.image.Image;
import actors.Actor;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

/**
 * This class defines the main pane of the stage in which all actors and levels are added onto
 * The class also defines the event handler and how it should work
 */
public abstract class World extends Pane {

    private AnimationTimer timer;

    /**
     * Class constructor
     * Creates an instance of World
     */
    public World() {
    	
    	sceneProperty().addListener(new ChangeListener<Scene>() {

			@Override
			public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
				if (newValue != null) {
					newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyReleased() != null) 
								getOnKeyReleased().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyReleased() != null) {
									anActor.getOnKeyReleased().handle(event);
								}
							}
						}
						
					});
					
					newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {

						@Override
						public void handle(KeyEvent event) {
							if(getOnKeyPressed() != null) 
								getOnKeyPressed().handle(event);
							List<Actor> myActors = getObjects(Actor.class);
							for (Actor anActor: myActors) {
								if (anActor.getOnKeyPressed() != null) {
									anActor.getOnKeyPressed().handle(event);
								}
							}
						}
						
					});
				}
			}
    		
		});
    }

    /**
     * This method creates a AnimationTimer for the game.
     * Once the timer is created and started the method act for the actors in the scene will be called in each frame of the game
     * @see Actor
     */
    public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //act(now);
                List<Actor> actors = getObjects(Actor.class);
                
                for (Actor anActor: actors) {
                	anActor.act(now);
                }
      
            }
        };
    }

    /**
     * This method will start the timer created
     */
    public void start() {
    	createTimer();
        timer.start();
    }

    /**
     * This method will stop the created timer
     */
    public void stop() {
        timer.stop();
    }

    /**
     * This method will add objects that are instances of Actors into the scene
     * @param actor actors to be added
     */
    public void add(Actor actor) {
        getChildren().add(actor);
    }

    /**
     * This method will remove objects that are instances of Actors from the Scene
     * @param actor actors to be removed
     */
    public void remove(Actor actor) {
        getChildren().remove(actor);
    }

    /**
     * This method will return a List of objects of the given class
     * @param cls class of the objects that will be returned
     * @param <A> class that is the child of Actor
     * @return a list of objects of the given class
     */
    public <A extends Actor> List<A> getObjects(Class<A> cls) {
        ArrayList<A> someArray = new ArrayList<A>();
        for (Node n: getChildren()) {
            if (cls.isInstance(n)) {
                someArray.add((A) n);
            }
        }
        return someArray;
    }

    /**
     * This method will add lives into the scene given the array of Lives
     * @param lives Array of Lives instances to be added into the scene
     */
    public void addHealth(ArrayList<Lives> lives){
        for (Lives l: lives) {
            add(l);
        }
    }

    /**
     * This method will remove lives from the scene given the array of Lives
     * @param lives Array of Lives instances to be removed
     */
    public void removeHealth(ArrayList<Lives> lives){
        if (lives.size() > 0) {
            getChildren().remove(lives.get(lives.size() - 1));
            lives.remove(lives.size()-1);
        }
    }

    /**
     * Abstract method of World class that should be overridden by inheriting classes
     * @param now the timestamp of current frame in nanoseconds
     */
    public abstract void act(long now);
}
