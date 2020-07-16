package com.kokuxz.Spring.Security.mapper;

import com.kokuxz.Spring.Security.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(User user);

    @Insert("INSERT INTO USERS(username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}. #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer insertUser(User user);
}
