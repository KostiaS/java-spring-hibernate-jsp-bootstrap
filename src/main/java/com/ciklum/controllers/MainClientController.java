package com.ciklum.controllers;

import com.ciklum.common.Constants;
import com.ciklum.common.UserMessageData;
import com.ciklum.model.UserMessageList;
import com.ciklum.model.interfaces.IAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Konstantin on 2016-08-06.
 */
@Controller
public class MainClientController {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    IAgent agent;

    @RequestMapping({"/", "index"})
    public String getAllMessages() {
        new UserMessageList().setAgent(agent);
        return "index";
    }

    @RequestMapping("create-edit-message")
    public String getCreateEditMessageView() {
        return "create-edit-message";
    }

    @RequestMapping("processing-create-edit-message-validation")
    public String processingCreateEditMessageValidation() {
        return "processing-create-edit-message-validation";
    }

    @RequestMapping("retry")
    public String getRetryView() {
        return "retry";
    }

    @RequestMapping("save-message")
    public ModelAndView saveMessage(long messageId, String userName, String messageText, Model model) {
        UserMessageData userMessage = new UserMessageData(-1, messageId, userName, messageText);
        String response;
        try {
            response = restTemplate.postForObject(Constants.DOMAIN/* + Constants.PORT*/ + Constants.SAVE_MESSAGE, userMessage, String.class);
        } catch (RestClientException e) {
            response = "not saved";
        }
        response = putResponse(response, "saved");
        model.addAttribute("result", response);
        return new ModelAndView(new RedirectView("/"));
    }

    @RequestMapping("delete-message")
    public ModelAndView deleteMessage(long userId, long messageId, String userName, Model model) {
        UserMessageData userMessage = new UserMessageData(userId, messageId, userName, null);
        String response;
        try {
            response = restTemplate.postForObject(
                    Constants.DOMAIN/* + Constants.PORT*/ + Constants.DELETE_MESSAGE, userMessage, String.class);
        } catch (RestClientException e) {
            response = "not deleted";
        }
        response = putResponse(response, "deleted");
        model.addAttribute("result", response);
        return new ModelAndView(new RedirectView("/"));
    }

    private String putResponse(String response, String action) {
        if(response == null || response.equals("false")) {
            response = "not" + action;
        } else {
            response = action;
        }
        return response;
    }

}
