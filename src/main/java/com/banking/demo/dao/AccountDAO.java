package com.banking.demo.dao;

import com.banking.demo.entity.Account;
import com.banking.demo.entity.Customer;

import java.util.List;

public interface AccountDAO {
    List<Account> findAllByCustomerId(int CustomerId);

    void addAccount(Customer customer);

    void deleteAccountById(int theId);

    void deposit(int theId, int amount);

    void withdraw(int theId, int amount);

    void transfer(int theId, String transferIBAN, int amount);
}
