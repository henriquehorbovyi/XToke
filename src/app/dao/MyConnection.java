package app.dao;

import com.mysql.jdbc.*;
import com.mysql.jdbc.PreparedStatement;

import java.sql.*;
import java.sql.Connection;

/**
 * Created by henry on 03/04/17.
 */
public class MyConnection {

    public Connection connect(){
        String HOST = "jdbc:mysql://localhost:3306/xtoke";
        String USER = "root";
        String PASS = "";

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(HOST,USER,PASS);
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
