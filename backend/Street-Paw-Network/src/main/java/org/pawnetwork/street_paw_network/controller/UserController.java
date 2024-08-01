package org.pawnetwork.street_paw_network.controller;

import java.util.List;

import org.pawnetwork.street_paw_network.entity.User;
import org.pawnetwork.street_paw_network.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);

    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username) {
        return userMapper.findByUsername(username);
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userMapper.findAll();
    }
    
}




