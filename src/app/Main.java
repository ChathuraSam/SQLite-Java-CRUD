package app;

import dao.DbConnect;
import dao.UserDao;
import model.User;

import java.sql.*;
import java.util.List;

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
        // createTableUser(conn);

        UserDao userDao = new UserDao();

        // Create user
         insertUser(new User(1, "Madawa", "madawa@gmail.com", "IN"), userDao);

        // List all users
        // listAllusers(userDao.getAllUsers());

        // Update user
        //User newUser =  new User("AAA", "BBB", "CCC");
        //updateUser(1, newUser, userDao);
    }

    public static void insertUser(User user, UserDao userDao) {
        userDao.createUser(new User(2, user.getName(), user.getEmail(), user.getCountry()));
    }

    public static void listAllusers(List<User> userList) {
        for(User u: userList) {
            System.out.println(u);
        }
    }

    public static void updateUser(int id, User newUser, UserDao userDao) {
        User user = userDao.updateUser(id, newUser);
        System.out.println("Updated");
        System.out.println(user);
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
