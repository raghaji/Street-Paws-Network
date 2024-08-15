package org.pawnetwork.street_paw_network.repository;

import org.pawnetwork.street_paw_network.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, String>{

    javax.management.relation.Role save(javax.management.relation.Role role);
    
}
