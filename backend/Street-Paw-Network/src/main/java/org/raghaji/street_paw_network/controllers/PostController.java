package org.raghaji.street_paw_network.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.raghaji.street_paw_network.dto.CommentDto;
import org.raghaji.street_paw_network.dto.PostDto;
import org.raghaji.street_paw_network.models.Comment;
import org.raghaji.street_paw_network.models.Post;
import org.raghaji.street_paw_network.security.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @RequestParam("content") String content,
            @RequestParam("photos") List<MultipartFile> photos) {

        Post post = postService.createPost(content, photos);
        PostDto postDto = convertToDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDto> postDtos = posts.stream()
                                      .map(this::convertToDto)
                                      .collect(Collectors.toList());
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> addComment(@RequestBody CommentDto commentDto) {
        Comment comment = postService.addCommentToPost(commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // Convert Post entity to PostDto
    private PostDto convertToDto(Post post) {
        return new PostDto(post.getId(), post.getContent(), post.getPhotoUrls());
    }

    // Optionally, convert PostDto to Post entity
    private Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setContent(postDto.getContent());
        post.setPhotoUrls(postDto.getPhotoUrls());
        return post;
    }
}
