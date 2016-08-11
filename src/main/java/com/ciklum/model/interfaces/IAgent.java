package com.ciklum.model.interfaces;

import com.ciklum.model.UserMessageData;

import java.util.List;

/**
 * Created by Konstantin on 2016-08-06.
 */
public interface IAgent {
    List<UserMessageData> getAllMessagesByUser();
    boolean addNewMessage(UserMessageData userMessageData);
    boolean editMessage(UserMessageData userMessageData);
    boolean deleteMessage(UserMessageData userMessageData);
}
