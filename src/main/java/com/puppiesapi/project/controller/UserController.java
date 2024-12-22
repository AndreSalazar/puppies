package com.puppiesapi.project.controller;

import com.puppiesapi.project.model.User;
import com.puppiesapi.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // REST API to create a user
    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user.getName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok("User created successfully!");
    }

    // REST API to login
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User user) {
        Optional<User> authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());
       if (authenticatedUser.isPresent()) {
           return ResponseEntity.ok("Login successfully!");
       }
        return ResponseEntity.status(401).build();
    }

    // REST API to get user's info details
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
