package com.ib.demo.service;

import com.ib.demo.model.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccountService {

    Optional<Account> register(String email, String password);
    Optional<Account> login(String email, String password);

}
