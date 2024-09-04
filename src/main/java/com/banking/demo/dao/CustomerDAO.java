package com.banking.demo.dao;

import com.banking.demo.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
}
