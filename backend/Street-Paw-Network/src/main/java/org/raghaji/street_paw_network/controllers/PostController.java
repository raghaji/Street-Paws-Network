package org.raghaji.street_paw_network.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.raghaji.street_paw_network.dto.CommentDto;
import org.raghaji.street_paw_network.dto.PostDto;
import org.raghaji.street_paw_network.models.Comment;
import org.raghaji.street_paw_network.models.Post;
import org.raghaji.street_paw_network.services.PostService;
import org.raghaji.street_paw_network.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

import org.raghaji.street_paw_network.services.CommentService;
import org.raghaji.street_paw_network.services.Convertto;
@CrossOrigin(origins = "*", maxAge = 86400000)
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private Convertto convertto;
    @Autowired
    private CommentService commentService;

    @GetMapping("all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDto> postDtos = posts.stream()
                                      .map(post -> convertto.convertToPostDto(post))
                                      .collect(Collectors.toList());
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    
    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.getPostById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            PostDto postDto = convertto.convertToPostDto(post);
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("photos") List<MultipartFile> photos, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Post post = postService.createPost(title,content, photos,userDetails.getId());
        PostDto postDto = convertto.convertToPostDto(post); 
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
        
    }

    @PostMapping("comments")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addComment(@RequestBody Map<String, Object> request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Comment comment = new Comment();
        comment.setContent((String) request.get("content"));
        Optional<Post> post=  postService.getPostById(Long.valueOf(request.get("postId").toString()));
        if (post.isPresent()) {
            comment.setPost(post.get());
        } else {
            return new ResponseEntity<String>("Post not found",HttpStatus.NOT_FOUND);
        }
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(convertto.convertToUser(userDetails));
        CommentDto commentDto = convertto.convertToCommentDto(postService.addCommentToPost(comment));
        return new ResponseEntity<CommentDto>(commentDto, HttpStatus.CREATED);
    }

    @PostMapping("deletepost/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> postDelete(@PathVariable Long id ) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            boolean isDeleted = postService.deletePost(post.get());
            if (isDeleted) {
                return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping("deletecomment/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> commentDelete(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        if (comment.isPresent()) {
            boolean isDeleted = commentService.deleteComment(comment.get());
            if (isDeleted) {
                return new ResponseEntity<>("comment deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
