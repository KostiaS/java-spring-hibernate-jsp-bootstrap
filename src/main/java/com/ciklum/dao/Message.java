package com.ciklum.dao;

import javax.persistence.*;

/**
 * Created by Konstantin on 2016-08-06.
 * Entity class used for providing storing messages in the database using JPA.
 */

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long messageId;

    @ManyToOne
    User user;

    String messageText;

    public Message() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Message(User user, String messageText) {
        this.user = user;
        this.messageText = messageText;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
