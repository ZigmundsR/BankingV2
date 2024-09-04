package com.banking.demo.dao;

import com.banking.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    @ValueSource(strings = {"test123", "user123", "", "yes"})
    void findByUserNameInValid(String userName) {
        User user = userDao.findByUserName(userName);
        assertNull(user);
    }

    @Test
    void findAll() {
        List<User> list = userDao.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "admin", "ma", ""})
    void findAllBySearchValid(String search) {
        List<User> list = userDao.findAll(search);
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid", "123", " "})
    void findAllBySearchInvalid(String search) {
        List<User> list = userDao.findAll(search);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void save(){
        User user = new User("saveUser", "saveUser",true);
        userDao.save(user);
        User userTest = userDao.findByUserName("saveUser");
        assertEquals("saveUser", userTest.getUserName());
    }

    @Test
    void deleteById(){
        // based on save() test working
        User user = userDao.findByUserName("saveUser");
        userDao.deleteById(user.getId());
        User userTest = userDao.findByUserName("saveUser");
        assertNull(userTest);
    }


}
