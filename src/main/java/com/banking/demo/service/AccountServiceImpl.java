package com.banking.demo.service;

import com.banking.demo.dao.AccountDAO;
import com.banking.demo.entity.Account;
import com.banking.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements  AccountService{
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public List<Account> findAllByCustomerId(int CustomerId) {
        return accountDAO.findAllByCustomerId(CustomerId);
    }

    @Override
    public void addAccount(Customer customer) {
        accountDAO.addAccount(customer);
    }

    @Override
    public void deleteAccountById(int theId) {
        accountDAO.deleteAccountById(theId);
    }

    @Override
    public void deposit(int theId, int amount) {
        accountDAO.deposit(theId, amount);
    }

    @Override
    public void withdraw(int theId, int amount) {
        accountDAO.withdraw(theId, amount);
    }

    @Override
    public void transfer(int theId, String transferIBAN, int amount) {
        accountDAO.transfer(theId, transferIBAN, amount);
    }
}
