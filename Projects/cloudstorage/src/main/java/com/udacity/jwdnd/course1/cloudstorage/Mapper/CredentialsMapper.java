package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    //Get all credential count
    @Select("SELECT COUNT(*) FROM CREDENTIALS")
    int getCredentialCount();

    //Get credential
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getCredentialList(Integer userId);

    //Get single credential
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential selectCredential(Integer credentialId);

    //Insert into credentials
    @Insert("INSERT INTO CREDENTIALS( url, username, key, password, userid)" +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer insertCredential(Credential credential);

    //Delete from credentials
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredentials(Integer credentialId);

    //Update credential in credentials
    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password}, key = #{key} WHERE credentialid = #{credentialId}")
    Integer updateCredential(Credential credential);
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