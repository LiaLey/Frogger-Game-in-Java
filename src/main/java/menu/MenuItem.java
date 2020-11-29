package menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;

public class MenuItem extends StackPane {
    public MenuItem(String name, ButtonAction myButtonAction) {


        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.DARKBLUE),
                new Stop(0.1, Color.BLACK),
                new Stop(0.9, Color.BLACK),
                new Stop(1, Color.DARKBLUE));


        Rectangle bg = new Rectangle(200, 30);
        bg.setOpacity(0.4);

        Text text = new Text(name);
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));

        setAlignment(Pos.CENTER);

        getChildren().addAll(bg, text);


        setOnMouseEntered(event -> {
            bg.setFill(gradient);
            text.setFill(Color.WHITE);

        });

        setOnMouseExited(event -> {
            bg.setFill(Color.BLACK);
            text.setFill(Color.DARKGREY);
        });

        setOnMousePressed(event -> {
            bg.setFill(Color.DARKVIOLET);
            try {
                myButtonAction.act();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setOnMouseReleased(event -> {
            bg.setFill(gradient);
        });

    }


    public MenuItem(String name, ButtonAction myButtonAction, int v, int v1) {
        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop(0, Color.DARKBLUE),
                new Stop(0.1, Color.BLACK),
                new Stop(0.9, Color.BLACK),
                new Stop(1, Color.DARKBLUE));

        Rectangle bg = new Rectangle(v,v1);
        bg.setOpacity(0.4);

        Text text = new Text(name);
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD,20));

        setAlignment(Pos.CENTER);

        getChildren().addAll(bg, text);

        setOnMouseEntered(event -> {
            bg.setFill(gradient);
            text.setFill(Color.WHITE);

        });

        setOnMouseExited(event -> {
            bg.setFill(Color.BLACK);
            text.setFill(Color.DARKGREY);
        });

        setOnMousePressed(event -> {
            bg.setFill(Color.DARKVIOLET);
            try {
                myButtonAction.act();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setOnMouseReleased(event -> {
            bg.setFill(gradient);
        });
    }

}
