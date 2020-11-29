package menu;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class MenuBox extends VBox{

    public MenuBox(MenuItem...items){

        //getChildren().add(createSeparator(210));

        for (MenuItem item : items){
            getChildren().addAll(item, createSeparator(210));
        }

    }

    public MenuBox(MenuItem item , boolean line){

        getChildren().addAll(item);
    }

    public MenuBox(ArrayList<MenuItem> items) {
        getChildren().add(createSeparator(270));

        for(MenuItem item : items) {
            getChildren().addAll(item, createSeparator(270));
        }
    }
    private Line createSeparator(int length){

        Line sep = new Line();
        sep.setEndX(length);
        sep.setStroke(Color.DARKGRAY);
        return sep;

    }
}
