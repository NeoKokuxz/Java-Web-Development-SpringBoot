package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT COUNT(*) FROM CREDENTIALS")
    int getCredentialCount();

    @Select("SELECT * FROM CREDENTIALS WHERE username = #{username}")
    Credential getCredential(String username);

    @Insert("INSERT INTO CREDENTIALS( url, username, key, password, userid)" +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE username = #{username}")
    void deleteCredentials(String username);

}

/*
CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    userid INT,
    foreign key (userid) references USERS(userid)
);
 */