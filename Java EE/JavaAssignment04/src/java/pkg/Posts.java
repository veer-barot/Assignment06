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
public class Posts {
    
    private List<Post> posts;
    private Post currentPost;
    
    public Posts() {       
        currentPost = new Post(-1, -1, "", null, "");
        getPostsFromDatabase();
    }
    
    public List<Post> getPosts() {
        return posts;
    }
    
    public Post getCurrentPost() {
        return currentPost;
    }
    
    public Post getPostById(int id) {
        for (Post p : posts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
   
    public Post getPostByTitle(String title) {
        for (Post p : posts) {
            if (p.getTitle().equals(title)) {
                return p;
            }
        }
        return null;
    }
    
    public String viewPost(Post p) {
        currentPost = p;
        return "viewPost";
    }
    
    public String addPost() {
        currentPost = new Post(-1, -1, "", null, "");
        return "editPost";
    }
    
    public String editPost() {
        return "editPost";
    }
    
    public String cancelPost() {
        int id = currentPost.getId();
        getPostsFromDatabase();
        currentPost = getPostById(id);
        return "viewPost";
    }
    
    public String savePost(User user) {
        try (Connection conn = DBUtils.getConnection())  {
           if (currentPost.getId() >=0) {
            String sql = "UPDATE posts SET title = ?, contents = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, currentPost.getTitle());
            pstmt.setString(2, currentPost.getContents());
            pstmt.setInt(3, currentPost.getId());
            pstmt.executeUpdate();
        }
        else {
            String sql = "INSERT INTO posts (user_id, title, created_time, contents) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, currentPost.getTitle());
            pstmt.setString(3, currentPost.getContents());
            pstmt.executeUpdate();   
           }
        } 
        catch (SQLException ex) {
            Logger.getLogger(Posts.class.getName()).log(Level.SEVERE, null, ex);
        }
        getPostsFromDatabase();
        currentPost = getPostByTitle(currentPost.getTitle());
        return "ViewPost";
    }
    
    private void getPostsFromDatabase() {
        try{
            Connection conn = DBUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM posts");
            posts = new ArrayList<>();
            while (rs.next()) {
                Post p = new Post (
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getTimestamp("created_time"),
                    rs.getString("contents")
                );
                posts.add(p);   
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
            posts = new ArrayList<>();
        }
    }
}
