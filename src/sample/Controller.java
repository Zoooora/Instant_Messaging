package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import sample.homePage.HomePage;
import sample.user.UserPage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public Label signIn;
    public Label getOut;
    public TextField account;
    public PasswordField password;
    public GridPane root;





    public void signIn(ActionEvent actionEvent) throws Exception {

        String patt = "^[0-9]{5}$";
        Pattern pattern = Pattern.compile(patt);
        Matcher matcher = pattern.matcher(account.getText());
        if(!matcher.matches()) {
            new Alert(Alert.AlertType.NONE, "请输入正确账号", new ButtonType[]{ButtonType.OK}).show();
            account.clear();
            password.clear();
            return;
        }
        int id = Integer.parseInt(account.getText());
        String paswd = password.getText();
        if(new HomePage().login(id, paswd)) {
            UserPage userPage = new UserPage();
            userPage.user_id = id;
            userPage.newPage();
            root.getScene().getWindow().hide();
        }
        else{
            new Alert(Alert.AlertType.NONE, "账号或密码错误", new ButtonType[]{ButtonType.OK}).show();
        }
    }

    public void getOut(ActionEvent actionEvent) {
        root.getScene().getWindow().hide();//获取stage并将其hide
    }


    public void regis(ActionEvent actionEvent) {

    }
}
