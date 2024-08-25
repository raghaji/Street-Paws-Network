package org.raghaji.street_paw_network.repository;

import org.raghaji.street_paw_network.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}