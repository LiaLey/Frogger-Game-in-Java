package menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class defines the display of the Titles used in scenes in the game.
 * Defines the specific font, style and alignment used for titles in the game.
 */
public class Title extends StackPane {

    /**
     * Class constuctor.
     * Creates an instance of Title with given string.
     * @param name String of the title
     */
    public Title(String name){
        Rectangle bg = new Rectangle(375,60);
        bg.setStroke(Color.WHITE);
        bg.setStrokeWidth(2);
        bg.setFill(null);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg,text);
    }
}
