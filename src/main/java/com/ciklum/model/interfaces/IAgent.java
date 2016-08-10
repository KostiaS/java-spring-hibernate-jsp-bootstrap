package com.ciklum.model.interfaces;

import com.ciklum.common.UserMessageData;

import java.util.List;

/**
 * Created by Konstantin on 2016-08-06.
 */
public interface IAgent {
    List<UserMessageData> getAllMessagesByUser();
//    void fillDatabase();
    boolean addNewMessage(UserMessageData userMessageData);
//    boolean addNewMessage(String userName, String messageText);
    boolean editMessage(UserMessageData userMessageData);
    boolean deleteMessage(UserMessageData userMessageData);
}
