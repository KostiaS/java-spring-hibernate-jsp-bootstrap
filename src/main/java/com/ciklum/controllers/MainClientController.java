package com.ciklum.controllers;

import com.ciklum.common.Constants;
import com.ciklum.model.UserMessageData;
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
 * Main controller intercepts all client requests.
 */
@Controller
public class MainClientController {
    RestTemplate restTemplate = new RestTemplate();

    /**
     * Auto wire been of data storage mechanism
     */
    @Autowired
    IAgent agent;

    /**
     * Maps client request to home page.
     * @return index view name as String
     */
    @RequestMapping({"/", "index"})
    public String getAllMessages() {
        new UserMessageList().setAgent(agent);
        return "index";
    }

    /**
     * Maps client request to create-edite-message page
     * @return create-edit-message view as String value
     */
    @RequestMapping("create-edit-message")
    public String getCreateEditMessageView() {
        return "create-edit-message";
    }

    /**
     * Maps request to processing-create-edit-message-validation page which provides
     * validation of input data from create-edit-message page. After validation process
     * forwards to home page if success and to retry page if failed.
     * @return processing-create-edit-message-validation view as String value
     */
    @RequestMapping("processing-create-edit-message-validation")
    public String processingCreateEditMessageValidation() {
        return "processing-create-edit-message-validation";
    }

    /**
     * Maps request to retry page if validation of input data from processing-create-edit-message-validation page
     * was failed. Duplicates create-edit-message page but shows error messages for client.
     * @return
     */
    @RequestMapping("retry")
    public String getRetryView() {
        return "retry";
    }

    /**
     * Maps request to save-message. Forwards request to MainRestController via RestTemplate object.
     * After response from MainRestController redirects to home page with attributes contained in Model.
     * @param messageId the message id
     * @param userName the user name
     * @param messageText the message text
     * @param model holder for attributes in requests
     * @return ModelAndView which is holder for both Model and View
     */
    @RequestMapping("save-message")
    public ModelAndView saveMessage(long messageId, String userName, String messageText, Model model) {
        UserMessageData userMessage = new UserMessageData(-1, messageId, userName, messageText);
        String response;
        try {
            response = restTemplate.postForObject(Constants.DOMAIN + Constants.SAVE_MESSAGE, userMessage, String.class);
        } catch (RestClientException e) {
            response = "not saved";
        }
        response = putResponse(response, "saved");
        model.addAttribute("result", response);
        return new ModelAndView(new RedirectView("/"));
    }

    /**
     * Maps request to delete-message. Forwards request to MainRestController via RestTemplate object.
     * After response from MainRestController redirects to home page with attributes contained in Model.
     * @param userId the user id
     * @param messageId the message id
     * @param userName the user name
     * @param model holder for attributes in requests
     * @return ModelAndView which is holder for both Model and View
     */
    @RequestMapping("delete-message")
    public ModelAndView deleteMessage(long userId, long messageId, String userName, Model model) {
        UserMessageData userMessage = new UserMessageData(userId, messageId, userName, null);
        String response;
        try {
            response = restTemplate.postForObject(
                    Constants.DOMAIN + Constants.DELETE_MESSAGE, userMessage, String.class);
        } catch (RestClientException e) {
            response = "not deleted";
        }
        response = putResponse(response, "deleted");
        model.addAttribute("result", response);
        return new ModelAndView(new RedirectView("/"));
    }

    /**
     * Puts action value in response object if response should be positive
     * or adds "not" before action if response should be negative
     * @param response the response object as String value
     * @param action indicates which operation is executed
     * @return response object as String value
     */
    private String putResponse(String response, String action) {
        if(response == null || response.equals("false")) {
            response = "not" + action;
        } else {
            response = action;
        }
        return response;
    }

}
