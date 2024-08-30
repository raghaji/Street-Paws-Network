package org.raghaji.street_paw_network.repository;

import java.util.List;
import java.util.Optional;

import org.raghaji.street_paw_network.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findByPostId(Long postId);
}