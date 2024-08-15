package org.pawnetwork.street_paw_network.controller;

import java.util.List;

import org.pawnetwork.street_paw_network.entity.User;
import org.pawnetwork.street_paw_network.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
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

    @PostMapping("/login")
    public boolean loginUser(@RequestBody User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        //userMapper.insert(user);
         String getPassword = (userMapper.findByUsername(user.getUsername())).getPassword();
         String inputPassword = passwordEncoder.encode(user.getPassword());
         System.out.println(getPassword +" New Password" + inputPassword);
         if (getPassword == inputPassword) {
            return true;
         } else {
            return false;
         }
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);

    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userMapper.findByUsername(username);
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userMapper.findAll();
    }

    @GetMapping("/role/{role}")
    public List<User> findByRole(@PathVariable String role) {
        return userMapper.findByRole(role);

    }

    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username){
        Long id = (userMapper.findByUsername(username)).getId();
        userMapper.delete(id);
    }
    
}




