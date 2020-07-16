package com.udacity.jwdnd.c1.review.model;

public class ChatMessage {
    private String messageId;
    private String username;
    private String messageText;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return messageText;
    }

    public void setMessage(String message) {
        this.messageText = message;
    }
}
