package com.ib.demo.web.controller;

import com.ib.demo.model.User;
import com.ib.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExampleController {

    private final UserService userService;

    public ExampleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("Hello World!!!");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/users/country/{country}")
    public ResponseEntity<List<User>> allUsersByCountry(@PathVariable String country) {
        return ResponseEntity.ok(userService.getAllByCountry(country));
    }

    @GetMapping("/users/phone/{phone}")
    public List<User> allUsersByPhone(@PathVariable String phone) {
        return userService.getAllByPhone(phone);
    }
}
