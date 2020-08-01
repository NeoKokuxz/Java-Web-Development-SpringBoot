package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {
    private Integer userId;
    private String fName;
    private String lName;
    private String username;
    private String password;
    private String salt;

    public User(){

    }

    public User(Integer userId, String username, String salt, String password, String fName, String lName) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
