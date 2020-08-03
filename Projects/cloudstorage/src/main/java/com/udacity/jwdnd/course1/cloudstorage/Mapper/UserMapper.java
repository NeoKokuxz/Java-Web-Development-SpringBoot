package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    //Get User
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    //Post User
    @Insert("INSERT INTO USERS(username, salt, password, firstname, lastname) " +
            "VALUES(#{username},#{salt},#{password},#{fName}, #{lName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    //PUT User

    //DELETE User
    @Delete("DELETE FROM USERS WHERE username = #{username}, password = #{password}")
    void deleteUser(String username, String password);
}
