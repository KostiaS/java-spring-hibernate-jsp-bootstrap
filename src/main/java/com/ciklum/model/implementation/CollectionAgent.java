package com.ciklum.model.implementation;

import com.ciklum.common.UserMessageData;
import com.ciklum.model.interfaces.IAgent;

import java.util.*;

/**
 * Created by Konstantin on 2016-08-10.
 */
public class CollectionAgent implements IAgent {

/*    Map<Long, String> users = new HashMap<>();
    Map<Long, String> messages = new HashMap<>();
    Map<Long, Long> connections = new HashMap<>();*/
//    Map<Long, Map<Long, String>> list = new HashMap<>();
    Map<String, Map<Long, String>> list = new HashMap<>();
    long messageId = 1;

    @Override
    public List<UserMessageData> getAllMessagesByUser() {
        List<UserMessageData> userMessageList = new LinkedList<>();
/*        for(Map.Entry<Long, Long> connection: connections.entrySet()) {
            UserMessageData userMessageData = new UserMessageData();
            long userId = connection.getKey();
            long messageId = connection.getValue();
            userMessageData.setUserId(userId);
            userMessageData.setMessageId(messageId);
            userMessageData.setUserName(users.get(userId));
            userMessageData.setMessageText(messages.get(messageId));
            userMessageList.add(userMessageData);
        }*/
        for(Map.Entry<String, Map<Long, String>> user: list.entrySet()) {
//            String userName = user.getKey();
            for(Map.Entry<Long, String> message: user.getValue().entrySet()) {
/*                UserMessageData userMessageData = new UserMessageData();
                userMessageData.setUserName(userName);
                userMessageData.setMessageId(message.getKey());
                userMessageData.setMessageText(message.getValue());
                userMessageList.add(userMessageData);*/
                userMessageList.add(new UserMessageData(
                        user.getKey(), message.getKey(), message.getValue()
                ));
            }
        }
        return userMessageList;
    }

    @Override
    public boolean addNewMessage(UserMessageData userMessageData) {
/*        long userId = userMessageData.getUserId();
        long messageId = userMessageData.getMessageId();
        connections.put(userId, messageId);
        users.put(userId, userMessageData.getUserName());
        messages.put(messageId, userMessageData.getMessageText());*/
        String userName = userMessageData.getUserName();
        Map<Long, String> messages = list.get(userName);
        if(messages == null) {
            messages = new HashMap<>();
        }
        messages.put(messageId, userMessageData.getMessageText());
        list.put(userName, messages);
        messageId++;
        return true;
    }

    @Override
    public boolean editMessage(UserMessageData userMessageData) {
//        messages.replace(userMessageData.getMessageId(), userMessageData.getMessageText());
        Map<Long, String> messages = list.get(userMessageData.getUserName());
        messages.replace(userMessageData.getMessageId(), userMessageData.getMessageText());
        return true;
    }

    @Override
    public boolean deleteMessage(UserMessageData userMessageData) {
        String userName = userMessageData.getUserName();
        Map<Long, String> messages = list.get(userName);
        messages.remove(userMessageData.getMessageId());
        if(list.get(userName).size() == 0) {
            list.remove(userName);
        }
        return true;
    }
}
