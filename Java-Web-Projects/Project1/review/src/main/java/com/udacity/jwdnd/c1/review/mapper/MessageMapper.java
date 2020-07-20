package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.logging.log4j.message.Message;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("SELECT * FROM MESSAGES WHERE messageid = #{messageId} ")
    Message getMessageById(String messageId);

    @Select("SELECT * FROM MESSAGES WHERE messageid = #{messageId} ")
    List<ChatMessage> getMessages();

    @Insert("INSERT INTO MESSAGES(username, messagetext) VALUES (#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "MessageId")
    Integer insertMessage(ChatMessage message);

}
