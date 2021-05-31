package sample.user;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.SQL.MySQL;

public class UserPage extends Application {
    public int user_id;
    public double x1;
    public double y1;
    public AnchorPane userPage;
    Stage stage = new Stage();

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root, 350, 750));
        primaryStage.show();

        primaryStage.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x1 = mouseEvent.getSceneX();
                y1 = mouseEvent.getSceneY();
            }
        });
        primaryStage.getScene().setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                primaryStage.setX(mouseEvent.getScreenX() - x1);
                primaryStage.setY(mouseEvent.getScreenY() - y1);
            }
        });
    }

    public void newPage() throws Exception {
        start(stage);
    }

    public void logOut(ActionEvent actionEvent) {
        MySQL mySQL = new MySQL("information");
        mySQL.update(00001, "tom", "1", "male", (short) 24, "offline");
        mySQL.close();
        userPage.getScene().getWindow().hide();
    }
}
