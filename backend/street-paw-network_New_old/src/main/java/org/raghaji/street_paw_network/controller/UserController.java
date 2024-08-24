package org.raghaji.street_paw_network.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.raghaji.street_paw_network.model.Role;
import org.raghaji.street_paw_network.model.User;
import org.raghaji.street_paw_network.services.RoleServices;
import org.raghaji.street_paw_network.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleServices roleServices;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a new user with roles, assign default role if none provided
    @PostMapping("/create")
    public ResponseEntity<User> createUserWithRoles(@RequestBody User user) {
        Set<Role> roles = user.getRole();
        if (roles == null || roles.isEmpty()) {
            roles = new HashSet<>();
            Role defaultRole = roleServices.getRoleByName("User");
            roles.add(defaultRole);
            user.setRole(roles);
        } else {
            roles = assignExistingRoles(roles);
            user.setRole(roles);
        }
        User createdUser = userService.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Update the roles of an existing user, assign default role if none provided
    @PutMapping("/{id}/updateRoles")
    public ResponseEntity<User> updateUserRoles(@PathVariable("id") Integer id, @RequestBody Set<Role> roles) {
        User user = userService.getUserById(id);
        if (roles == null || roles.isEmpty()) {
            roles = new HashSet<>();
            Role defaultRole = roleServices.getRoleByName("User");
            roles.add(defaultRole);
        } else {
            roles = assignExistingRoles(roles);
        }
        user.setRole(roles);
        User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Helper method to assign existing roles from the database
    private Set<Role> assignExistingRoles(Set<Role> roles) {
        Set<Role> assignedRoles = new HashSet<>();
        for (Role role : roles) {
            Role existingRole = roleServices.getRoleByName(role.getRoleName());
            assignedRoles.add(existingRole);
        }
        return assignedRoles;
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
