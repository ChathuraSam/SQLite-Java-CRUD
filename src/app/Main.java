package app;

import dao.DbConnect;

import java.sql.Connection;

/**
 * @author chathura
 * @Date 2022-07-17
 */
public class Main {
    public static void main(String[] args) {
        Connection conn = DbConnect.getConnection();
        if(conn!=null)
            System.out.println("Connected to SQLite Database Successfully!");
        else
            System.out.println("Connection failed to SQLite database!");
    }
}
