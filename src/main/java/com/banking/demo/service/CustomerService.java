package com.banking.demo.service;

import com.banking.demo.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
}
