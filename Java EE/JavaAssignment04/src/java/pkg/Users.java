/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author c0719943
 */
@Named
@ApplicationScoped
public class Users {
    
    private List<User> users;
    private static Users instance;
    
    public Users() {
        instance = this;
        getUsersFromDatabase();
    }

    public List<User> getUsers() {
        return users;
    }

    public static Users getInstance() {
        return instance;
    }
    
    public String getUsernameById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u.getUsername();
            }
        }
        return null;
    }
   
    public int getUserIdByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u.getId();
            }
        }
        return -1;
    }
       
    private void getUsersFromDatabase() {
        try (Connection conn = DBUtils.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            users = new ArrayList<>();
            while (rs.next()) {
                User u = new User (
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("passhash")
                );
            users.add(u);   
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
            users = new ArrayList<>();
        }
    }
    
    public void addUser(String username, String password) {
        try (Connection conn = DBUtils.getConnection()) {
            String passhash = DBUtils.hash(password);
            String sql = "INSERT INTO users (username, passhash) VALUES(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, passhash);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Users.class.getName()).log(Level.SEVERE, null, ex);
        }
        getUsersFromDatabase();
    }
}

