package com.banking.demo.controller;

import com.banking.demo.entity.Account;
import com.banking.demo.entity.Customer;
import com.banking.demo.entity.User;
import com.banking.demo.service.AccountService;
import com.banking.demo.service.CustomerService;
import com.banking.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {
    private static final Logger logger = LogManager.getLogger(AccountController.class);

    private AccountService accountService;
    private UserService userService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, UserService userService, CustomerService customerService) {
        this.accountService = accountService;
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listAccounts(Model theModel,
                               @AuthenticationPrincipal UserDetails userDetails){

        User user = userService.findByUserName(userDetails.getUsername());
        List<Account> theAccounts;

        int customerId = user.getCustomer().getId();
        theModel.addAttribute("customerId", customerId);

        theAccounts = accountService.findAllByCustomerId(customerId);

        theModel.addAttribute("accounts", theAccounts);
        return "accounts/list-accounts";
    }

    @GetMapping("/{customerId}/add")
    public String addAccount(Model theModel,
                             @PathVariable  int customerId){
        Customer customer = customerService.findById(customerId);
        accountService.addAccount(customer);

        return "redirect:/accounts/list";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("accountId") int theId) {

        accountService.deleteAccountById(theId);

        return "redirect:/accounts/list";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam("accountId") int theId,
                          @RequestParam("amount") int amount) {

        accountService.deposit(theId, amount);

        return "redirect:/accounts/list";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam("accountId") int theId,
                           @RequestParam("amount") int amount) {

        accountService.withdraw(theId, amount);

        return "redirect:/accounts/list";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam("accountId") int theId,
                           @RequestParam("TransferIBAN") String transferIBAN,
                           @RequestParam("amount") int amount) {

        accountService.transfer(theId, transferIBAN, amount);

        return "redirect:/accounts/list";
    }
}
