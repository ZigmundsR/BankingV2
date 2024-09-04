package com.banking.demo.dao;


import com.banking.demo.entity.User;

import java.util.List;

public interface UserDAO {
    User findByUserName(String userName);

    List<User> findAll();

    List<User> findAll(String search);

    void save(User theUser);

    void deleteById(int theId);

}
