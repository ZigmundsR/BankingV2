package com.banking.demo.controller;

import com.banking.demo.entity.Customer;
import com.banking.demo.entity.User;
import com.banking.demo.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private static final Logger logger = LogManager.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel){
        List<Customer> theCustomers;
        theCustomers = customerService.findAll();

        theModel.addAttribute("customers", theCustomers);
        return "customers/list-customers";
    }
}
