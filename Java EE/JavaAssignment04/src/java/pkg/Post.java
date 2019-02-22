/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import java.sql.Timestamp;

/**
 *
 * @author c0719943
 */
public class Post {
    
    private int id;
    private int userId;
    private String title;
    private Timestamp createdTime;
    private String contents;

    public Post(int id, int userId, String title, Timestamp createdTime, String contents) {
        
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.createdTime = createdTime;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}
