package com.ciklum.dao;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Created by Konstantin on 2016-08-06.
 */

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long messageId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
