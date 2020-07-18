package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.MessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MessageService{

    private MessageMapper messageMapper;

    @PostConstruct
    public void postConstruct(){
        System.out.println("Creating Message service bean");
    }

    public void addMessage(ChatForm chatform){
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatform.getUsername());
        switch (chatform.getMessageType()){
            case "Say":
                newMessage.setMessage(chatform.getMessageText());
                break;

            case "Shout":
                newMessage.setMessage(chatform.getMessageText().toUpperCase());
                break;

            case "Whisper":
                newMessage.setMessage(chatform.getMessageText().toLowerCase());
                break;
        }
        //System.out.println("Add message: " + chatform.getMessageText());
        //messageMapper.
    }

    public List<ChatMessage> getChatMessage(){
        return messageMapper.message()
    }

}
