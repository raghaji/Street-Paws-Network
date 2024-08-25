package org.raghaji.street_paw_network.security.services;

import java.time.LocalDateTime;

import org.raghaji.street_paw_network.dto.CommentDto;
import org.raghaji.street_paw_network.models.Comment;
import org.raghaji.street_paw_network.models.Post;
import org.raghaji.street_paw_network.models.User;
import org.raghaji.street_paw_network.repository.CommentRepository;
import org.raghaji.street_paw_network.repository.PostRepository;
import org.raghaji.street_paw_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    public Comment addComment(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId())
            .orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(commentDto.getuserId())
            .orElseThrow(() -> new RuntimeException("User Not found."));
        
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }
}
