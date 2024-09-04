package com.banking.demo.dao;

import com.banking.demo.entity.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDAOImplTest {

    @Autowired
    private UserDAO userDao;

    @ParameterizedTest
    @ValueSource(strings = {"admin", "manager"})
    void findByUserNameValid(String userName) {
        User user = userDao.findByUserName(userName);
        assertEquals(userName,user.getUserName());
        assertNotNull(user);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test123", "user123", ""})
    void findByUserNameInvalid(String userName) {
        User user = userDao.findByUserName(userName);
        assertNull(user);
    }
}
