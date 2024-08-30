package org.raghaji.street_paw_network.services;

import java.util.stream.Collectors;

import org.raghaji.street_paw_network.dto.CommentDto;
import org.raghaji.street_paw_network.dto.PostDto;
import org.raghaji.street_paw_network.models.Comment;
import org.raghaji.street_paw_network.models.Post;
import org.raghaji.street_paw_network.models.User;
import org.raghaji.street_paw_network.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Convertto {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;


    public PostDto convertToPostDto(Post post) {
        UserDetailsImpl userDetails = UserDetailsImpl.build(post.getUser());
        PostDto postDto= new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setPhotoUrls(post.getPhotoUrls());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setUser(userDetails);
        if (null != post.getComments()) {
            postDto.setCommentDtos(
                post.getComments()
                .stream()
                .map(comment -> convertToCommentDto(comment))
                .collect(Collectors.toList())
            );            
        }

        return postDto;
    }
    public CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setPostId(comment.getPost().getId());
        commentDto.setUser(
            converttoDetailsImpl(comment.getUser())
        );
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
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
            post.setUser((userRepository.findById(postDto.getUser().getId())).get());
        }
        return post;
    }

    public Comment convertToCommentEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setCreatedAt(commentDto.getCreatedAt());
        comment.setUser(
            convertToUser(commentDto.getUser())
        );
        comment.setPost((postService.getPostById(commentDto.getId())).get());
        return comment;
    }

    public User convertToUser(UserDetailsImpl userDetailsImpl){
        return userRepository.findByUsername(userDetailsImpl.getUsername()).get();
    }

    public UserDetailsImpl converttoDetailsImpl(User user){
        return UserDetailsImpl.build(user);
    }
}
