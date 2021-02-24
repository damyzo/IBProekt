package com.ib.demo.service.impl;

import com.ib.demo.model.Account;
import com.ib.demo.model.Role;
import com.ib.demo.repository.AccountRepository;
import com.ib.demo.service.AccountService;
import com.ib.demo.service.UtilsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final UtilsService utilsService;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, UtilsService utilsService) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.utilsService = utilsService;
    }

    @Override
    public Optional<Account> register(String email, String password) {
        if (accountRepository.findByEmail(email).isPresent()) {
            return Optional.empty();
        }
        String encPass = passwordEncoder.encode(password);
        SecretKey accessKey = utilsService.generateAccessKey();
        SecretKey secretKey = utilsService.generateSecretKey();
        Account account = new Account(email, encPass, accessKey, secretKey, Role.USER);
        accountRepository.save(account);
        return Optional.of(account);
    }

    @Override
    public Optional<Account> login(String email, String password) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            return Optional.empty();
        }
        if (passwordEncoder.matches(password, account.get().getPassword())) {
            return account;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Account> findByAccessKey(String accessKey) {
        return accountRepository.findByAccessKey(accessKey);
    }
}
