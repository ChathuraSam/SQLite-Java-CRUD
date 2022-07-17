package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author chathura
 * @Date 2022-07-17
 */
public class DbConnect {
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:sqlite:myDb.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
