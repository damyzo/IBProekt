package com.ib.demo.service;

import com.ib.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<User> getAll();

    public List<User> getAllByCountry(String country);

}
