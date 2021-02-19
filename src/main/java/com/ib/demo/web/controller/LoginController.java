package com.ib.demo.web.controller;

import com.ib.demo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<String>> loginAccount(@RequestParam String email, @RequestParam String password) {
        List<String> list = new ArrayList<>();
        accountService.login(email, password).ifPresent(account -> {
            list.add(account.getAccessKeyEncoded());
            list.add(account.getSecretKeyEncoded());
        });
        return ResponseEntity.ok(list);
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register.html";
    }

    @PostMapping("/register")
    public ResponseEntity<List<String>> registerAccount(@RequestParam String email, @RequestParam String password) {
        List<String> list = new ArrayList<>();
        accountService.register(email, password).ifPresent(account -> {
            list.add(account.getAccessKeyEncoded());
            list.add(account.getSecretKeyEncoded());
        });
        return ResponseEntity.ok(list);
    }
}
