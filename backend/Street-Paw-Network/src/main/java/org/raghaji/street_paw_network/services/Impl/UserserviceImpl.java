package org.raghaji.street_paw_network.services.Impl;

import java.util.List;
import java.util.Optional;

import org.raghaji.street_paw_network.exception.ResourceNotFoundException;
import org.raghaji.street_paw_network.model.User;
import org.raghaji.street_paw_network.repository.UserRepository;
import org.raghaji.street_paw_network.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements UserService {
    private UserRepository userRepository;
    
    @Autowired
    public UserserviceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }

    @Override
    public User getUserByUserId(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User", "username", username);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("User", "email", email);
        }
    }

}
