package com.banking.demo.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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

}
