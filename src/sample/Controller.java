package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import sample.SQL.MySQL;

public class Controller {
    public Label signIn;

    public void signIn(ActionEvent actionEvent) {

        signIn.setText("sign in");
    }
}
