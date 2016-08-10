package com.ciklum.model;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Konstantin on 2016-08-09.
 */
public class CreateEditFormValidator {
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_-]{2,15}$";

    private String userName;
    private String messageText;
    private Hashtable errors;

    private Pattern pattern;
    private Matcher matcher;


    public CreateEditFormValidator() {
        this.userName = "";
        this.messageText = "";
        this.errors = new Hashtable();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Hashtable getErrors() {
        return errors;
    }

    public void setErrors(Hashtable errors) {
        this.errors = errors;
    }

    public void setErrors(String key, String message) {
        this.errors.put(key, message);
    }

    public String getErrorMsg(String errorMessage) {
        String errorMsg = (String)errors.get(errorMessage.trim());
        return (errorMsg == null) ? "" : errorMsg;
    }

    public boolean validate() {
        boolean allOk = true;
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(userName);
        if(userName.equals("")) {
            errors.put("userName", "Please enter your name");
            userName = "";
            allOk = false;
        }
        if(!matcher.matches()) {
            errors.put("userNameRegex", "user name should be minimum 2 characters length, and doesn't contain special characters");
            allOk = false;
        }
        if(messageText.equals("")) {
            errors.put("messageText", "Please enter message text");
            messageText = "";
            allOk = false;
        }
        return allOk;
    }

}
