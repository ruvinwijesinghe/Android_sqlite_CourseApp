package com.example.coursewebclone.Model;

public class User {
    private Integer UserID;
    private String user;
    private String password;
    private String userCategory;

    public User(){

    }

    public User(String user, String password, String userCategory) {
        this.user = user;
        this.password = password;
        this.userCategory = userCategory;
    }

    public User(Integer userID, String user, String password, String userCategory) {
        UserID = userID;
        this.user = user;
        this.password = password;
        this.userCategory = userCategory;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }
}
