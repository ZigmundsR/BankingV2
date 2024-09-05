package com.banking.demo.dao;

import com.banking.demo.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
    private static Logger logger = LogManager.getLogger(CustomerDAOImpl.class);
    private EntityManager entityManager;

    @Autowired
    public CustomerDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> theQuery = entityManager.createQuery("from Customer", Customer.class);

        return theQuery.getResultList();
    }

    @Override
    public Customer findById(int customerId) {
        return entityManager.find(Customer.class,customerId);
    }
}
