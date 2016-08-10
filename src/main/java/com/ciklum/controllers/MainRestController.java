package com.ciklum.controllers;

import com.ciklum.common.Constants;
import com.ciklum.common.UserMessageData;
import com.ciklum.model.interfaces.IAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Konstantin on 2016-08-08.
 */
@RestController
@RequestMapping("/")
public class MainRestController {

    @Autowired
    IAgent agent;

    @RequestMapping(value = Constants.SAVE_MESSAGE, method = RequestMethod.POST)
    boolean saveMessage(@RequestBody UserMessageData userMessageData) {
        boolean res;
//        System.out.println(userMessageData.getMessageId());
//        System.out.println(userMessageData.getUserName());
        if(userMessageData.getMessageId() == -1) {
            res = agent.addNewMessage(userMessageData);
        } else  {
            res = agent.editMessage(userMessageData);
        }
        return res;
    }

    @RequestMapping(value = Constants.DELETE_MESSAGE, method = RequestMethod.POST)
//    boolean deleteMessage(@PathVariable long messageId, @PathVariable String userName) {
    boolean deleteMessage(@RequestBody UserMessageData userMessageData) {
        return agent.deleteMessage(userMessageData);
    }
}
