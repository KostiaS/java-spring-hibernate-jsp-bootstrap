package com.ciklum.model.implementation;

import com.ciklum.model.UserMessageData;
import com.ciklum.dao.Message;
import com.ciklum.dao.User;
import com.ciklum.model.interfaces.IAgent;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Konstantin on 2016-08-06.
 */
public class DBAgent implements IAgent {

    @PersistenceContext(unitName="springHibernate", type=PersistenceContextType.EXTENDED)
    EntityManager em;

    @Transactional
    @Override
    public List<UserMessageData> getAllMessagesByUser() {
        List<UserMessageData> userMessageList = new LinkedList<UserMessageData>();
        Query queryUsers = em.createQuery("SELECT user FROM User user ORDER BY userName");
        List<User> users;
        try {
            users = queryUsers.getResultList();
            Query queryMessagesByUserId = em.createQuery("SELECT message FROM Message message WHERE user.userId=?1");
            List<Message> messages;
            for(User user: users) {
                queryMessagesByUserId.setParameter(1, user.getUserId());
                messages = queryMessagesByUserId.getResultList();
                if(messages.size() > 0) {
                    for(Message message: messages) {
                        userMessageList.add(new UserMessageData(
                                user.getUserId(),
                                message.getMessageId(),
                                user.getUserName(),
                                message.getMessageText()
                        ));
                    }
                }
            }
        } catch (QueryTimeoutException e) {
            return null;
        } catch (TransactionRequiredException e) {
            return null;
        } catch (PersistenceException e) {
            return null;
        }
        return userMessageList;
    }

    @Transactional
    @Override
    public boolean addNewMessage(UserMessageData userMessageData) {
        User user;
        Query query = em.createQuery("SELECT user FROM User user WHERE userName=?1");
        query.setParameter(1, userMessageData.getUserName());
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            user = new User(userMessageData.getUserName());
            try {
                em.persist(user);
            } catch (TransactionRequiredException eTransaction) {
                return false;
            } catch (QueryTimeoutException eTimeout) {
                return false;
            } catch (PersistenceException ePersistence) {
                return false;
            }
        } catch (QueryTimeoutException e) {
            return false;
        } catch (TransactionRequiredException e) {
            return false;
        } catch (PersistenceException e) {
            return false;
        }
        Message message = new Message(user, userMessageData.getMessageText());
        try {
            em.persist(message);
        } catch (QueryTimeoutException e) {
            return false;
        } catch (TransactionRequiredException e) {
            return false;
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean editMessage(UserMessageData userMessageData) {
        Query query = em.createQuery("SELECT message FROM Message message WHERE id=?1");
        query.setParameter(1, userMessageData.getMessageId());
        Message message;
        try {
            message = (Message) query.getSingleResult();
            message.setMessageText(userMessageData.getMessageText());
            em.merge(message);
        } catch(EntityNotFoundException e) {
            return false;
        } catch (QueryTimeoutException e) {
            return false;
        } catch (TransactionRequiredException e) {
            return false;
        } catch (PersistenceException e) {
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public boolean deleteMessage(UserMessageData userMessageData) {
        int res;
        try {
            res = em.createQuery("DELETE FROM Message message WHERE message.messageId="
                    + userMessageData.getMessageId()).executeUpdate();
            if(res == 0) return false;
            Object count = em.createQuery("SELECT COUNT(message.user) FROM Message message WHERE user.userId="
                    + userMessageData.getUserId()).getSingleResult();
            if(count.toString().equals("0")) {
                res = em.createQuery("DELETE FROM User user WHERE user.userId="
                        + userMessageData.getUserId()).executeUpdate();
            }
        } catch (NoResultException e) {
            return false;
        } catch (QueryTimeoutException e) {
            return false;
        } catch (TransactionRequiredException e) {
            return false;
        } catch (PersistenceException e) {
            return false;
        }
        return (res == 0) ? false : true;
    }
}
