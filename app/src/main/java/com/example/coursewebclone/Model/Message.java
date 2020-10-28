package com.example.coursewebclone.Model;

public class Message {

    private Integer messageID;
    private Integer userID;
    private String subject;
    private String message;

    public Message(){

    }

    public Message(Integer userID, String subject, String message) {
        this.userID = userID;
        this.subject = subject;
        this.message = message;
    }

    public Message(Integer messageID, Integer userID, String subject, String message) {
        this.messageID = messageID;
        this.userID = userID;
        this.subject = subject;
        this.message = message;
    }

    public Integer getMessageID() {
        return messageID;
    }

    public void setMessageID(Integer messageID) {
        this.messageID = messageID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
