package org.pawnetwork.street_paw_network.controller;

import org.pawnetwork.street_paw_network.entity.User;
import org.pawnetwork.street_paw_network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}
