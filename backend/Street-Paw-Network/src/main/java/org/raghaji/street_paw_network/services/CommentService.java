package org.raghaji.street_paw_network.services;

import java.util.List;
import java.util.Optional;

import org.raghaji.street_paw_network.models.Comment;
import org.raghaji.street_paw_network.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> listCommentByPostId(Long id){
        return (commentRepository.findByPostId(id)).get();
    }

    public boolean deleteComment(Comment comment){
        commentRepository.delete(comment);
        boolean commentPresent = (commentRepository.findById(comment.getId())).isPresent();
        return !commentPresent;
    }

    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }
}
