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
 * Implementation of storing data via JPA.
 */
public class DBAgent implements IAgent {

    @PersistenceContext(unitName="springHibernate", type=PersistenceContextType.EXTENDED)
    EntityManager em;

    /**
     * Gets all messages by user names
     * @return list of messages sorted by user name
     */
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

    /**
     * Adds new message to dataStorage mapped to corresponding user.
     * If the corresponding user was not found in storage method creates new user.
     * @param userMessageData holds data of users and messages. Contains user name and message text.
     * @return boolean result of operation: true if operation was successful and false if not
     */
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

    /**
     * Change message text of exact message. The message id is passed in userMessageData object.
     * @param userMessageData holds data of users and messages. Contains message id and message text.
     * @return boolean result of operation: true if operation was successful and false if not
     */
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

    /**
     * Deletes message from storage. The message id is passed in userMessageData object.
     * If all messages of the user were deleted user also is removed from storage.
     * @param userMessageData holds data of users and messages. Contains user id and message id.
     * @return boolean result of operation: true if operation was successful and false if not
     */
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
