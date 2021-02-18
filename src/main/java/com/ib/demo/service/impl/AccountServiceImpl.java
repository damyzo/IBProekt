package com.ib.demo.service.impl;

import com.ib.demo.model.Account;
import com.ib.demo.repository.AccountRepository;
import com.ib.demo.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<Account> register(String email, String password) {
        if (accountRepository.findByEmail(email).isPresent()) {
            return Optional.empty();
        }
        Account account = new Account(email, passwordEncoder.encode(password));
        accountRepository.save(account);
        return Optional.of(account);
    }

    @Override
    public Optional<Account> login(String email, String password) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            return Optional.empty();
        }
        if (passwordEncoder.matches(account.get().getPassword(), password)) {
            return account;
        }
        return Optional.empty();
    }
}
