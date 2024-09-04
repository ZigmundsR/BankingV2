package com.banking.demo.service;

import com.banking.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;

    @ParameterizedTest
    @ValueSource(strings = {"admin", "manager"})
    void loadUserByUsernameValid(String userName) {
        UserDetails userDetails = userService.loadUserByUsername(userName);
        assertEquals(userName,userDetails.getUsername());
        assertNotNull(userDetails);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test123", "user123", "", "yes"})
    void loadUserByUsernameInvalid(String userName) {
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(userName));
    }

    @Test
    void findAll() {
        List<User> list = userService.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "admin", "ma", ""})
    void findAllBySearchValid(String search) {
        List<User> list = userService.findAll(search);
        assertNotNull(list);
        assertFalse(list.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "123", " "})
    void findAllBySearchInvalid(String search) {
        List<User> list = userService.findAll(search);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

}
