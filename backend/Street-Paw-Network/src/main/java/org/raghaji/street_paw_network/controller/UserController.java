package org.raghaji.street_paw_network.controller;

import java.util.List;

import org.raghaji.street_paw_network.model.User;
import org.raghaji.street_paw_network.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("findbyid/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id){
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }
    @GetMapping("findbyusername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<User>(userService.getUserByUserId(username), HttpStatus.OK);
    }
    @GetMapping("findbyemail/{email}")
    public ResponseEntity<User> getUserByemail(@PathVariable("email")String email){
        return new ResponseEntity<User>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    
}
