package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author chathura
 * @Date 2022-07-17
 */
public class UserDao {
    private static final String CREATE_USER = "insert into user(id, name, email,country) values(?,?,?,?)";

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
}
