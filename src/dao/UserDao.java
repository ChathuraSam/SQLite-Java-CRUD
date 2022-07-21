package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chathura
 * @Date 2022-07-17
 */
public class UserDao {
    private static final String CREATE_USER = "insert into user(id, name, email,country) values(?,?,?,?)";
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String UPDATE_USER = "update user set name=?, email=?, country=? where id=?";
    //insert user
    public void createUser(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DbConnect.getConnection();
            ps = con.prepareStatement(CREATE_USER);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getCountry());
            ps.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(con!=null)
                    con.close();
                if(ps!=null)
                    ps.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // list all users
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            con = DbConnect.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(SELECT_ALL_USERS);

            while(rs.next()) {
                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(con!=null)
                    con.close();
                if(st!=null)
                    st.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    // update a user by ID
    public User updateUser(int id, User user) {
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = DbConnect.getConnection();
            ps = con.prepareStatement(UPDATE_USER);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getCountry());
            ps.setInt(4, id);
            ps.executeUpdate();

            user.setId(id);
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(con!=null)
                    con.close();
                if(ps!=null)
                    ps.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
