package com.ciklum.model;

/**
 * Created by Konstantin on 2016-08-06.
 * Holds data such as user id, message id, user name and message text for interchanging.
 */
public class UserMessageData {
    long userId;
    long messageId;
    String userName;
    String messageText;

    public UserMessageData() {
    }

    public UserMessageData(long userId, long messageId, String userName, String messageText) {
        this.userId = userId;
        this.messageId = messageId;
        this.userName = userName;
        this.messageText = messageText;
    }

    public UserMessageData(String userName, long messageId, String messageText) {
        this.userName = userName;
        this.messageId = messageId;
        this.messageText = messageText;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
