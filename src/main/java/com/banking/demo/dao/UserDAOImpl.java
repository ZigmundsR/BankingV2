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
    private static Logger logger = LogManager.getLogger(UserDAOImpl.class);
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

    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);

        return theQuery.getResultList();
    }

    @Override
    public List<User> findAll(String search) {
        TypedQuery<User> theQuery = entityManager.createQuery("SELECT u FROM User u WHERE u.userName LIKE :search", User.class);
        theQuery.setParameter("search", "%" + search + "%");

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void save(User theUser) {
        entityManager.merge(theUser);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        User user = entityManager.find(User.class, theId);
        entityManager.remove(user);
    }
}
