package menu;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

/**
 * This Class defines the properties of the Menu Boxes needed to construct the Pause and Main Menu.
 */
public class MenuBox extends VBox{

    /**
     * Class constructor.
     * Creates new instance of Menu Box with given menu items.
     * @param items Menu items needed to be added into the menu box
     */
    public MenuBox(MenuItem...items){

        //getChildren().add(createSeparator(210));

        for (MenuItem item : items){
            getChildren().addAll(item, createSeparator(210));
        }

    }

    /**
     * Class constructor.
     * Creates new instance of Menu Box with given menu item.
     * @param item Menu item needed to be added into the menu box
     * @param line true/false value depending on whether the item needs a line separator
     */
    public MenuBox(MenuItem item , boolean line){

        getChildren().addAll(item);
    }

    /**
     * Class constructor.
     * Creates new instance of Menu Box with the given list of menu items.
     * @param items Arraylist of items needed to be added into the menu box
     */
    public MenuBox(ArrayList<MenuItem> items) {
        getChildren().add(createSeparator(270));

        for(MenuItem item : items) {
            getChildren().addAll(item, createSeparator(270));
        }
    }

    /**
     * This method creates a line separator for each item that needs to be added into the menu box.
     * @param length Length of the line separator
     * @return The Line that separates the menu items
     */
    private Line createSeparator(int length){

        Line sep = new Line();
        sep.setEndX(length);
        sep.setStroke(Color.DARKGRAY);
        return sep;

    }
}
