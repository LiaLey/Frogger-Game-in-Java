import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

public class InfoController {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        Window window =   ((Node)(event.getSource())).getScene().getWindow();
        if (window instanceof Stage){
            ((Stage) window).close();
        }
        //((Node)(event.getSource())).getScene().getWindow().hide();

    }
}