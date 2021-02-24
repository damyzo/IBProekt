package com.ib.demo.service.impl;

import com.ib.demo.model.User;
import com.ib.demo.repository.UserRepository;
import com.ib.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<User> putNewUser(String name, String email, String phone, String country) {
        User user = new User(name, email, phone, country);
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
        return user;
    }
}
