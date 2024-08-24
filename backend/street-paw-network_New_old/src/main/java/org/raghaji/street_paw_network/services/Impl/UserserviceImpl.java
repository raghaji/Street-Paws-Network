package org.raghaji.street_paw_network.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.raghaji.street_paw_network.exception.ResourceNotFoundException;
import org.raghaji.street_paw_network.model.Role;
import org.raghaji.street_paw_network.model.User;
import org.raghaji.street_paw_network.repository.RoleRepository;
import org.raghaji.street_paw_network.repository.UserRepository;
import org.raghaji.street_paw_network.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class UserserviceImpl implements UserService,UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserserviceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initDefaultUser() {
        
        if((roleRepository.findByRoleName("Admin")).isEmpty()){
            Role defaultAdminRole = new Role();
            defaultAdminRole.setRoleName("Admin");
            defaultAdminRole.setRoleDescription("Administrator User. Having full access");
            roleRepository.save(defaultAdminRole);
        }
        if((roleRepository.findByRoleName("User")).isEmpty()){
            Role defaultUserRole = new Role();
            defaultUserRole.setRoleName("User");
            defaultUserRole.setRoleDescription("Default User permission.");
            roleRepository.save(defaultUserRole);
        }
        if (userRepository.findByUsername("defaultAdmin").isEmpty()) {
            Role defaultRole = (roleRepository.findByRoleName("Admin")).get();

            User defaultUser = new User();
            defaultUser.setFirst_name("Default");
            defaultUser.setLast_name("Admin");
            defaultUser.setUsername("defaultAdmin");
            defaultUser.setEmail("defaultAdmin@example.com");
            defaultUser.setLocation("Default Location");
            defaultUser.setPassword(passwordEncoder.encode("Admin@pass"));
            defaultUser.setRole(Set.of(defaultRole));
            userRepository.save(defaultUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        Set<SimpleGrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());
        System.out.println(authorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
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
