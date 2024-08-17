package org.raghaji.street_paw_network.services;

import java.util.List;

import org.raghaji.street_paw_network.model.User;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Integer id);
    User getUserByUserId(String username);
    User getUserByEmail(String email);
}
