package com.ib.demo.service;

import com.ib.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public List<User> getAll();

    public List<User> getAllByCountry(String country);

    public List<User> getAllByPhone(String phone);

    public Optional<User> putNewUser(String name, String email, String phone, String country);

    public Optional<User> deleteUser(Long id);
}
