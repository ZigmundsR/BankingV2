package com.banking.demo.service;


import com.banking.demo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    List<User> findAll(String search);

    void deleteById(int theId);

    User findByUserName(String userName);
}
