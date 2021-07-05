package com.example.springdataintro.initialisation;

import entities.Account;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import services.AccountService;
import services.UserService;

import java.math.BigDecimal;

public class ConsoleRunner implements CommandLineRunner {
   @Autowired
   private UserService userService;

   @Autowired
   private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("Gosho", 36);
        Account account1 = new Account(new BigDecimal(1500));

        userService.register(user1);
        account1 = accountService.createUserAccount(user1, account1);

        accountService.withdrawMoney(new BigDecimal(1000), account1.getId());
        accountService.depositMoney(new BigDecimal(500), account1.getId());
        accountService.getAllAccounts().forEach(System.out::println);
    }
}
