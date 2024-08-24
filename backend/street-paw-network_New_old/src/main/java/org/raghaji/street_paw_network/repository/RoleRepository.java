package org.raghaji.street_paw_network.repository;

import java.util.Optional;

import org.raghaji.street_paw_network.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);

}
