package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UserPage extends Application {
    Stage stage = new Stage();

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Chatting");
        primaryStage.setScene(new Scene(root, 430, 700));
        primaryStage.show();
    }

    public void newPage() throws Exception {
        start(stage);
    }
}
