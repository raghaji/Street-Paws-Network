package org.raghaji.street_paw_network.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import org.raghaji.street_paw_network.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Modifying
  @Transactional
  @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
  int resetPassword(@Param("password") String password, @Param("id") Long id);  
}