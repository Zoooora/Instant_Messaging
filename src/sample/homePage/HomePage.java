package sample.homePage;

import javafx.css.Match;
import sample.SQL.MySQL;

import javax.sql.rowset.serial.SerialStruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage {

    public HomePage(){

    }

    public boolean login(String name, String password){
        MySQL mySQL = new MySQL("information");
        boolean re = mySQL.login(name, password);
        mySQL.close();
        return re;
    }

    public boolean register(String name, String password, String gender, Short age)
            throws SQLException {
        String name_pattern = "^[a-zA-Z][\\w.]{4,14}$";
        Pattern name_Pattern = Pattern.compile(name_pattern);
        Matcher matcher = name_Pattern.matcher(name);
        if(!matcher.matches()) return false;
        else{
            MySQL mySQL = new MySQL("information");
            mySQL.add_user(GetRandomId("user"), name, password, gender, age);
        }
        return false;
    }

    public int GetRandomId(String type)
            throws SQLException {
        int id = 0;
        MySQL mySQL = new MySQL("information");
        ResultSet rs;
        Random random = new Random();
        do{
            if(type.equalsIgnoreCase("user")) id = random.nextInt(89999) + 10000;
            else if(type.equalsIgnoreCase("union")) id = random.nextInt(8999999) + 1000000;
            rs = mySQL.select(id);
        }while(!rs.wasNull());
        return id;
    }
}
