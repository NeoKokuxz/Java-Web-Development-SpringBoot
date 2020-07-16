package com.udacity.jwdnd.c1.review.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.logging.log4j.message.Message;

@Mapper
public interface messageMapper {

    @Select("SELECT * FROM MESSAGES WHERE messageid = #{messageId} ")
    Message message(String messageId);

    @Insert("INSERT INTO MESSAGES(username, messagetext) VALUES (#{username}, #{messageText})")
    Integer insert(Message message);

}
