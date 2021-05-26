package sample;

import com.sun.glass.ui.Window;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.events.Event;

public class Main extends Application {


    double x1;
    double y1;
    double x_stage;
    double y_stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("Hello Chat");
        primaryStage.setScene(new Scene(root, 430, 328));
        primaryStage.show();

        primaryStage.getScene().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x1 = mouseEvent.getSceneX();
                y1 = mouseEvent.getSceneY();
                x_stage = primaryStage.getY();
                y_stage = primaryStage.getX();
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


    public static void main(String[] args) {
        launch(args);
    }
}
