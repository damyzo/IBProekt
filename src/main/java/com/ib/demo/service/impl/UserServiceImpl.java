package com.ib.demo.service.impl;

import com.ib.demo.model.User;
import com.ib.demo.repository.UserRepository;
import com.ib.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllByCountry(String country) {
        return userRepository.findAllByCountryContaining(country);
    }

    @Override
    public List<User> getAllByPhone(String phone) {
        return userRepository.findAllByPhoneContaining(phone);
    }
}
