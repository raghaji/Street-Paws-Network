package org.pawnetwork.street_paw_network.repository;

import org.pawnetwork.street_paw_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
