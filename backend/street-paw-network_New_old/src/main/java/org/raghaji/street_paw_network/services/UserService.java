package org.raghaji.street_paw_network.services;

import java.util.List;

import org.raghaji.street_paw_network.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Integer id);
    User getUserByUserId(String username);
    User getUserByEmail(String email);
    UserDetails loadUserByUsername(String username);
    // void initRoleAndUser();
}
