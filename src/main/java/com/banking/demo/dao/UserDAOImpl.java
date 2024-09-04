package com.banking.demo.dao;

import com.banking.demo.controller.HomePageController;
import com.banking.demo.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    private static Logger logger = LogManager.getLogger(HomePageController.class);
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> theQuery = entityManager.createQuery("from User where userName=:userName", User.class);
        theQuery.setParameter("userName", userName);

        User theUser = null;

        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            logger.error("username not found: " + userName);
        }
        return theUser;
    }
}
