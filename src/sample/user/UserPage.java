package sample.user;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.SQL.MySQL;

import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserPage extends Application {
    public int user_id;
    public double x1;
    public double y1;
    public HBox userPage;
    public ListView ListView_L;
    public Label name;
    Stage stage = new Stage();

    public UserPage(){

    }

    public void init(Parent parent){
        this.userPage = (HBox)parent.lookup("#user_Page");
        this.ListView_L = (ListView)parent.lookup("#ListView_L");
        this.name = (Label)parent.lookup("#name");
    }

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("userPage.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene(root, 600, 400));
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

        init(root);
        getFriend();

    }

    private void getFriend() {
        try {
            MySQL mySQL = new MySQL("information");
            ObservableList<String> strlist = FXCollections.observableArrayList();
            ArrayList<Integer> friends_id = mySQL.findFriends(this.user_id);
            ArrayList<String> friends_name = new ArrayList<>();
            while(!friends_id.isEmpty()){
                friends_name.add(mySQL.select(friends_id.remove(0)).getString("user_name"));
            }

            ListView_L.setPlaceholder(new Label("没有好友"));
            while(!friends_name.isEmpty()) {
                strlist.add(friends_name.remove(0));
            }
            ListView_L.setItems(strlist);

            ListView_L.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    name.setText(newValue);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void newPage() throws Exception {
        start(stage);
    }

    public void logOut(ActionEvent actionEvent) {
        MySQL mySQL = new MySQL("information");
        mySQL.updateStatus("offline", user_id);
        mySQL.close();
        userPage.getScene().getWindow().hide();
    }
}
