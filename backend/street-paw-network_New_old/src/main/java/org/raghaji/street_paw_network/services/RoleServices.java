package org.raghaji.street_paw_network.services;

import org.raghaji.street_paw_network.model.Role;

public interface RoleServices {
    Role createNewRole(Role role);
    Role getRoleByName(String roleName);
}
