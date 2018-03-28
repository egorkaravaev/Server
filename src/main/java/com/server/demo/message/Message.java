package com.server.demo.message;

import com.server.demo.enums.MessageType;

import java.io.Serializable;

public class Message implements IMessage, Serializable{

    private int type;
    //private MessageType type;
    private String message;

    public Message(int type, String obj) {
        this.type = type;
        this.message = obj;
    }

    public Message() {
    }

//    public MessageType getType() {
//        return type;
//    }
//
//    public void setType(MessageType type) {
//        this.type = type;
//    }

        public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
