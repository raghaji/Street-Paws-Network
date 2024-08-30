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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

    @PostMapping("create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createPost(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("photos") List<MultipartFile> photos) {

        Post post = postService.createPost(title,content, photos);
        PostDto postDto = convertto.convertToPostDto(post); //convertToPostDto(post);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
        
    }

    @GetMapping("all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostDto> postDtos = posts.stream()
                                      .map(post -> convertto.convertToPostDto(post))
                                      .collect(Collectors.toList());
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.getPostById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setContent(post.getContent());
            postDto.setTitle(post.getTitle());
            postDto.setUser(post.getUser());
            postDto.setCreatedAt(post.getCreatedAt());
            postDto.setPhotoUrls(post.getPhotoUrls());
            postDto.setCommentDtos(
                commentService.listCommentByPostId(post.getId())
                .stream()
                .map(comment -> convertto.convertToCommentDto(comment))
                .collect(Collectors.toList())
                );
            return new ResponseEntity<>(postDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/comments")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Comment> addComment(@RequestBody Map<String, Object> request) {

        CommentDto commentDto = new CommentDto();
        commentDto.setContent((String) request.get("content"));
        commentDto.setPostId(Long.valueOf(request.get("postId").toString()));
        commentDto.setUser(null);
        commentDto.setCreatedAt(LocalDateTime.now());
        Comment comment = postService.addCommentToPost(commentDto);
        return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
    }


}
