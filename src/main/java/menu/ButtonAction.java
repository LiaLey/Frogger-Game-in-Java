package menu;

import java.io.IOException;

/**
 * Buttion Action is an abstract class with that sets the action that will be taken when the button is clicked
 *
 */
public abstract class ButtonAction {

    /**
     * This method is an abstract method that should be overridden by inheriting classes to specify actions that should happen when the button is clicked
     * @throws IOException on error during execution of actions in act()
     */
    public abstract void act() throws IOException;
}
