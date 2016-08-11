package com.ciklum.model.implementation;

import com.ciklum.model.UserMessageData;
import com.ciklum.model.interfaces.IAgent;

import java.util.*;

/**
 * Created by Konstantin on 2016-08-10.
 * Implementation of storing data via Java collections.
 */
public class CollectionAgent implements IAgent {

    /**
     * Collection where data is stored
     */
    Map<String, Map<Long, String>> dataStorage = new TreeMap<String, Map<Long, String>>();

    long messageId = 1;

    /**
     * Gets all messages by user names
     * @return list of messages sorted by user name
     */
    @Override
    public List<UserMessageData> getAllMessagesByUser() {
        List<UserMessageData> userMessageList = new LinkedList<UserMessageData>();
        for(Map.Entry<String, Map<Long, String>> user: dataStorage.entrySet()) {
            for(Map.Entry<Long, String> message: user.getValue().entrySet()) {
                userMessageList.add(new UserMessageData(
                        user.getKey(), message.getKey(), message.getValue()
                ));
            }
        }
        return userMessageList;
    }

    /**
     * Adds new message to dataStorage mapped to corresponding user.
     * If the corresponding user was not found in storage method creates new user.
     * @param userMessageData holds data of users and messages. Contains user name and message text.
     * @return boolean result of operation: true if operation was successful and false if not
     */
    @Override
    public boolean addNewMessage(UserMessageData userMessageData) {
        String userName = userMessageData.getUserName();
        Map<Long, String> messages = dataStorage.get(userName);
        try {
            if(messages == null) {
                messages = new HashMap<Long, String>();
            }
            messages.put(messageId, userMessageData.getMessageText());
            dataStorage.put(userName, messages);
            messageId++;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Change message text of exact message. The message id is passed in userMessageData object.
     * @param userMessageData holds data of users and messages. Contains user id, message id and message text.
     * @return boolean result of operation: true if operation was successful and false if not
     */
    @Override
    public boolean editMessage(UserMessageData userMessageData) {
        Map<Long, String> messages = dataStorage.get(userMessageData.getUserName());
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

    /**
     * Deletes message from storage. The message id is passed in userMessageData object.
     * If all messages of the user were deleted user also is removed from storage.
     * @param userMessageData holds data of users and messages. Contains user id and message id.
     * @return boolean result of operation: true if operation was successful and false if not
     */
    @Override
    public boolean deleteMessage(UserMessageData userMessageData) {
        String userName = userMessageData.getUserName();
        Map<Long, String> messages = dataStorage.get(userName);
        try {
            messages.remove(userMessageData.getMessageId());
            if(dataStorage.get(userName).size() == 0) {
                dataStorage.remove(userName);
            }
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
