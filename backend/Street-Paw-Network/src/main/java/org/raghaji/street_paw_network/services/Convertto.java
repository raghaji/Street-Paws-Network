package org.raghaji.street_paw_network.services;

import org.raghaji.street_paw_network.dto.CommentDto;
import org.raghaji.street_paw_network.dto.PostDto;
import org.raghaji.street_paw_network.models.Comment;
import org.raghaji.street_paw_network.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Convertto {

    @Autowired
    private PostService postService;

    public PostDto convertToPostDto(Post post) {
        return new PostDto(post.getId(),post.getTitle(), post.getContent(), post.getPhotoUrls(),post.getCreatedAt(),post.getUser(), null);
    }
    public CommentDto convertToCommentDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getPost().getId(), comment.getContent(),comment.getCreatedAt(),comment.getUser());
    }

    public Post convertToPostEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPhotoUrls(postDto.getPhotoUrls());
        if (null != postDto.getCreatedAt()) {
            post.setCreatedAt(postDto.getCreatedAt());
        }
        if (null != postDto.getUser()) {
            post.setUser(postDto.getUser());
        }
        return post;
    }

    public Comment convertToCommentEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setUser(commentDto.getUser());
        comment.setPost((postService.getPostById(commentDto.getId())).get());
        return comment;
    }
}
