package com.banking.demo.controller;

import com.banking.demo.entity.User;
import com.banking.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    private static Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model theModel,
                            @RequestParam(name = "search", required = false) String search){

        List<User> theUsers;
        if (search == null || search.isEmpty()){
            logger.info("searching for user list...");
            theUsers = userService.findAll();
        } else {
            logger.info("using search parameter: " + search + " searching for user list ...");
            theUsers = userService.findAll(search);
        }

        theModel.addAttribute("users", theUsers);
        return "users/list-users";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("userId") int theId){
        logger.info("deleting user with id: " + theId);
        userService.deleteById(theId);
        return "redirect:/users/list";
    }
}
