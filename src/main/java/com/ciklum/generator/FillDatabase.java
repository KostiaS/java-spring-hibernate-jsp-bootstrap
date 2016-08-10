package com.ciklum.generator;

import com.ciklum.dao.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by Konstantin on 2016-08-06.
 */
public class FillDatabase {
    private static final int MESSAGES_AMOUNT = 500;

    @PersistenceContext(unitName = "springHibernate", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    void fillDatabase() {

    }

    void generateUser() {
        for(int i = 0; i < MESSAGES_AMOUNT; i++) {
            User user = new User();
            user.setUserName("user" + i);
        }
    }
}
