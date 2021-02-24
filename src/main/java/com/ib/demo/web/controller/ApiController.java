package com.ib.demo.web.controller;

import com.ib.demo.model.User;
import com.ib.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;

    public ApiController(UserService userService) {
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

    @PutMapping("/users/put")
    public ResponseEntity<String> putUser(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) String email,
                                          @RequestParam(required = false) String phone,
                                          @RequestParam(required = false) String country) {
        Optional<User> user = userService.putNewUser(name, email, phone, country);
        if (user.isPresent()) {
            return ResponseEntity.ok("Successful entry");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.deleteUser(id);
        if (user.isPresent()) {
            return ResponseEntity.ok("user deleted");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
