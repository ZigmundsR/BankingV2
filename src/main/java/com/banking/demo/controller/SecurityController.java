package com.banking.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    private static Logger logger = LogManager.getLogger(SecurityController.class);

    @GetMapping("/login")
    public String showLoginPage() {
        logger.info("in login page");
        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        logger.info("in in access denied page");
        return "access-denied";
    }
}
