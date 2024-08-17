package org.raghaji.street_paw_network.repository;

import java.util.Optional;

import org.raghaji.street_paw_network.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
