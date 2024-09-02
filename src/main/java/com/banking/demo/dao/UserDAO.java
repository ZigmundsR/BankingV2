package com.banking.demo.dao;


import com.banking.demo.entity.User;

import java.util.List;

public interface UserDAO {
    User findByUserName(String userName);
}
