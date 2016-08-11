package com.ciklum.controllers;

import com.ciklum.common.Constants;
import com.ciklum.model.UserMessageData;
import com.ciklum.model.interfaces.IAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Konstantin on 2016-08-08.
 * Main RestController provides request mapping from MainClientController
 */
@RestController
@RequestMapping("/")
public class MainRestController {

    /**
     * Auto wire bean of data storage mechanism
     */
    @Autowired
    IAgent agent;

    /**
     * Maps request for saving message.
     * @param userMessageData bean used as request body object
     * @return boolean result of operation
     */
    @RequestMapping(value = Constants.SAVE_MESSAGE, method = RequestMethod.POST)
    boolean saveMessage(@RequestBody UserMessageData userMessageData) {
        boolean res;
        if(userMessageData.getMessageId() == -1) {
            res = agent.addNewMessage(userMessageData);
        } else  {
            res = agent.editMessage(userMessageData);
        }
        return res;
    }

    /**
     * Maps request for deleting message
     * @param userMessageData bean used as request body object
     * @return boolean result of operation
     */
    @RequestMapping(value = Constants.DELETE_MESSAGE, method = RequestMethod.POST)
    boolean deleteMessage(@RequestBody UserMessageData userMessageData) {
        return agent.deleteMessage(userMessageData);
    }
}
