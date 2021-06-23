package com.ib.demo.service;

import com.ib.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> getAll();

    List<User> getAllByCountry(String country);

    List<User> getAllByPhone(String phone);

    Optional<User> putNewUser(String name, String email, String phone, String country);

    Optional<User> deleteUser(Long id);
}
