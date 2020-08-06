package com.udacity.jwdnd.c1.review.model;

public class ChatForm {
    private String username;
    private String messageText;
    private String messageType;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getMessageText(){
        return this.messageText;
    }

    public void setMessageText(String text){
        this.messageText = text;
    }

    public String getMessageType(){
        return this.messageType;
    }

    public void setMessageType(String type){
        this.messageType = type;
    }
}
