package app;

import dao.DbConnect;

import java.sql.*;

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

        // Create User table
        createTableUser(conn);
    }

    private static void createTableUser(Connection conn) {
        final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS \"user\"  (\n" +
                "\t\"id\"\tINTEGER,\n" +
                "\t\"name\"\tTEXT,\n" +
                "\t\"email\"\tTEXT,\n" +
                "\t\"country\"\tTEXT\n" +
                ");";
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "user", null);
            if (tables.next()) {
                System.out.println("Table already exist!");
                return;
            }
            Statement statement = conn.createStatement();
            statement.executeUpdate(CREATE_TABLE_USER);
            System.out.println("Table Created");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
