package org.raghaji.street_paw_network.services.Impl;

import java.util.Optional;

import org.raghaji.street_paw_network.exception.ResourceNotFoundException;
import org.raghaji.street_paw_network.model.Role;
import org.raghaji.street_paw_network.repository.RoleRepository;
import org.raghaji.street_paw_network.services.RoleServices;
import org.springframework.stereotype.Service;
@Service
public class RoleServiceImpl implements RoleServices{
    private RoleRepository roleRepository;
    @Override
    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public Role getRoleByName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);
        if (role.isPresent()) {
            return role.get();
        } else {
            throw new ResourceNotFoundException("Role", "roleName", roleName);
        }

    }

}
