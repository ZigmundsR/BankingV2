package com.banking.demo.dao;

import com.banking.demo.entity.Account;
import com.banking.demo.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class AccountDAOImpl implements AccountDAO{
    private static Logger logger = LogManager.getLogger(AccountDAOImpl.class);
    private EntityManager entityManager;

    @Autowired
    public AccountDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Account> findAllByCustomerId(int CustomerId) {
        TypedQuery<Account> theQuery = entityManager.createQuery("SELECT u FROM Account u WHERE u.customer.id = :customerId", Account.class);
        theQuery.setParameter("customerId", CustomerId);

        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void addAccount(Customer customer) {
        String iban = generateIban();
        Account account = new Account(customer, iban);
        entityManager.merge(account);
    }

    @Override
    @Transactional
    public void deleteAccountById(int theId) {
        Account account = entityManager.find(Account.class,theId);
        if (account.getBalance() == 0){
            entityManager.remove(account);
        }
    }

    @Override
    @Transactional
    public void deposit(int theId, int amount) {
        Account account = entityManager.find(Account.class, theId);
        account.setBalance(account.getBalance() + amount);
        entityManager.merge(account);
    }

    @Override
    @Transactional
    public void withdraw(int theId, int amount) {
        Account account = entityManager.find(Account.class,theId);
        if(account.getBalance() >= amount){
            account.setBalance(account.getBalance() - amount);
            entityManager.merge(account);
        }
    }

    @Override
    @Transactional
    public void transfer(int theId, String transferIBAN, int amount) {
        Account account = entityManager.find(Account.class,theId);
        if(account.getBalance() >= amount){
            account.setBalance(account.getBalance() - amount);
            entityManager.merge(account);
            Account targetAccount = findByIBAN(transferIBAN);
            targetAccount.setBalance(targetAccount.getBalance() + amount);
            entityManager.merge(targetAccount);
        }
    }

    public Account findByIBAN(String transferIBAN) {
        TypedQuery<Account> theQuery = entityManager.createQuery("SELECT u FROM Account u WHERE u.iban = :transferIBAN", Account.class);
        theQuery.setParameter("transferIBAN", transferIBAN);

        return theQuery.getSingleResult();
    }

    private String generateIban() {
        String countryCode = "LV"; // Latvia country code
        String bankCode = "UNLA"; // Bank code

        // Generate 2-digit check number (00-99)
        String checkNumber = String.format("%02d", new Random().nextInt(100));

        // Generate 13-digit bank account number
        StringBuilder accountNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            accountNumber.append(random.nextInt(10)); // Random digit (0-9)
        }

        // Combine all parts to form the IBAN
        return countryCode + checkNumber + bankCode + accountNumber.toString();
    }
}
