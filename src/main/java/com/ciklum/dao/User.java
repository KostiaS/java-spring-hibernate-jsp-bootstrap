package com.ciklum.dao;

import javax.persistence.*;

/**
 * Created by Konstantin on 2016-08-06.
 * Entity class used for providing storing users in the database using JPA.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getUserName() {
        return userName;
    }

}
