package sample.SQL;

import java.sql.*;

public class MySQL {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "QazpLm321#@";
    Connection conn = null;

    public MySQL(String SetSchema){
        try{
            Class.forName(JDBC_DRIVER);

            String DB_URL = "jdbc:mysql://localhost:3306/" + SetSchema +
                    "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        try{
            this.conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean login(int account, String password){
        String sql = "select * from user where user_id = ? and password = ?";
        try {
            PreparedStatement preSta = this.conn.prepareStatement(sql);
            preSta.setInt(1, account);
            preSta.setString(2, password);
            ResultSet rs = preSta.executeQuery();
            if(rs.next()){
                updateStatus(true);
                return true;
            }
            else return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void add_user(int user_id, String name, String password, String gender,
                    short age, boolean online){
        String sql = "insert into user(user_id, user_name, password, gender, age, online)" +
                "values(? ,?, ?, ?, ?, ?)";
        try{
            PreparedStatement preSta = this.conn.prepareStatement(sql);
            preSta.setInt(1, user_id);
            preSta.setString(2, name);
            preSta.setString(3, password);
            preSta.setString(4, gender);
            preSta.setShort(5, age);
            preSta.setBoolean(6, online);
            preSta.executeUpdate();
            preSta.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete_user(int user_id){
        String sql = "delete from user where user_id = ?";
        try{
            PreparedStatement preSta = this.conn.prepareStatement(sql);
            preSta.setInt(1, user_id);
            preSta.executeUpdate();
            preSta.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet select(int id){
        String sql = "select * from user where user_id = " + id;
        try{
            Statement sta = this.conn.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while (rs.next()){
                return rs;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet select(String name){
        String sql = "select * from user where user_name = '" + name + " '";
        try{
            Statement sta = this.conn.createStatement();
            ResultSet rs = sta.executeQuery(sql);
            while(rs.next()){
                return rs;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void update(int user_id, String user_name, String password, String gender, short age){
        String sql_1 = "update user set user_name = ?, password = ?, gender = ?, age = ? where user_id = ?";
        try{
            PreparedStatement preSta_1 = this.conn.prepareStatement(sql_1);

            preSta_1.setString(1, user_name);
            preSta_1.setString(2, password);
            preSta_1.setString(3, gender);
            preSta_1.setShort(4, age);
            preSta_1.setInt(5, user_id);

            preSta_1.executeUpdate();
            preSta_1.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateStatus(boolean status){
        String sql = "update user set online = ?";
        try {
            PreparedStatement preSta = this.conn.prepareStatement(sql);
            preSta.setBoolean(1, status);
            preSta.executeUpdate();
            preSta.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
