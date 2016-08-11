package com.ciklum.model.implementation;

import com.ciklum.common.UserMessageData;
import com.ciklum.model.interfaces.IAgent;

import java.util.*;

/**
 * Created by Konstantin on 2016-08-10.
 */
public class CollectionAgent implements IAgent {

    Map<String, Map<Long, String>> list = new TreeMap<String, Map<Long, String>>();
    long messageId = 1;

    @Override
    public List<UserMessageData> getAllMessagesByUser() {
        List<UserMessageData> userMessageList = new LinkedList<UserMessageData>();
        for(Map.Entry<String, Map<Long, String>> user: list.entrySet()) {
            for(Map.Entry<Long, String> message: user.getValue().entrySet()) {
                userMessageList.add(new UserMessageData(
                        user.getKey(), message.getKey(), message.getValue()
                ));
            }
        }
        return userMessageList;
    }

    @Override
    public boolean addNewMessage(UserMessageData userMessageData) {
        String userName = userMessageData.getUserName();
        Map<Long, String> messages = list.get(userName);
        try {
            if(messages == null) {
                messages = new HashMap<Long, String>();
            }
            messages.put(messageId, userMessageData.getMessageText());
            list.put(userName, messages);
            messageId++;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean editMessage(UserMessageData userMessageData) {
        Map<Long, String> messages = list.get(userMessageData.getUserName());
        try {
            messages.remove(userMessageData.getMessageId());
            messages.put(userMessageData.getMessageId(), userMessageData.getMessageText());
//          since 1.8
//          messages.replace(userMessageData.getMessageId(), userMessageData.getMessageText());
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteMessage(UserMessageData userMessageData) {
        String userName = userMessageData.getUserName();
        Map<Long, String> messages = list.get(userName);
        try {
            messages.remove(userMessageData.getMessageId());
            if(list.get(userName).size() == 0) {
                list.remove(userName);
            }
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
