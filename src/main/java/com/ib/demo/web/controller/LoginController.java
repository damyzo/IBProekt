package com.ib.demo.web.controller;

import com.ib.demo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @PostMapping("/login")
    public String loginAccount(@RequestParam String email, @RequestParam String password) {
        accountService.login(email, password);
        return "redirect:/api/test";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register.html";
    }

    @PostMapping("/register")
    public String registerAccount(@RequestParam String email, @RequestParam String password) {
        if (accountService.register(email, password).isEmpty()) {
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
