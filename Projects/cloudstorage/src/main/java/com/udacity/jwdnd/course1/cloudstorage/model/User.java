package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class User {

    private Integer userId;
    private String fName;
    private String lName;
    private String username;
    private String password;
    private String salt;

    public User(Integer userId, String username, String salt, String password, String fName, String lName) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

}
