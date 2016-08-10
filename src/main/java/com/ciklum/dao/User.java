package com.ciklum.dao;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Konstantin on 2016-08-06.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long userId;

    String userName;


/*    @OneToMany
    List<Message> message;*/

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String userName) {
        this.userName = userName;
    }

    /*    public User(String userName, List<Message> message) {
        this.userName = userName;
        this.message = message;
    }*/

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

/*    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> messages) {
        this.message = messages;
    }*/
}
