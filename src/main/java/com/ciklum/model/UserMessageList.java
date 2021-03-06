package com.ciklum.model;

import com.ciklum.model.interfaces.IAgent;

import java.util.List;

/**
 * Created by Konstantin on 2016-08-07.
 * Bean for index.jsp as collection of messages retriever
 */
public class UserMessageList {

    public static IAgent agent;

    List<UserMessageData> userMessageList;

    public static void setAgent(IAgent agentAutowired) {
        agent = agentAutowired;
    }

    public List<UserMessageData> getUsersMessages() {
        userMessageList = agent.getAllMessagesByUser();
        return userMessageList;
    }

}
