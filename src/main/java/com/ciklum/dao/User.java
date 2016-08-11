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

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String userName) {
        this.userName = userName;
    }

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
}
