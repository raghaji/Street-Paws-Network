package org.raghaji.street_paw_network.security.services;

import java.util.Set;

import org.raghaji.street_paw_network.models.ERole;
import org.raghaji.street_paw_network.models.Role;
import org.raghaji.street_paw_network.models.User;
import org.raghaji.street_paw_network.repository.RoleRepository;
import org.raghaji.street_paw_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class CreateDefaultUserandRole {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @PostConstruct
    public void initDefaultUserAndRole(){
        if((roleRepository.findByName(ERole.ROLE_ADMIN)).isEmpty() ){
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }

        if ((roleRepository.findByName(ERole.ROLE_USER)).isEmpty()) {
            Role userRole= new Role(ERole.ROLE_USER);
            roleRepository.save(userRole);
        }
        if ((userRepository.findByUsername("admin")).isEmpty()) {
            Role defaultRoleForAdmin = (roleRepository.findByName(ERole.ROLE_ADMIN)).get();
            User defaultAdminUser = new User();
            defaultAdminUser.setEmail("raulraghaji@gmail.com");
            defaultAdminUser.setUsername("admin");
            defaultAdminUser.setPassword(encoder.encode("admin@pass"));
            defaultAdminUser.setRoles(Set.of(defaultRoleForAdmin));
            userRepository.save(defaultAdminUser);

            
        }
    }
}
