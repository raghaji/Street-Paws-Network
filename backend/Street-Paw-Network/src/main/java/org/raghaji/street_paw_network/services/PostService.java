package org.raghaji.street_paw_network.services;
import org.raghaji.street_paw_network.dto.CommentDto;
import org.raghaji.street_paw_network.models.Comment;
import org.raghaji.street_paw_network.models.Post;
import org.raghaji.street_paw_network.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Autowired
    private CommentService commentService; 

    public Post createPost(String title,String content, List<MultipartFile> photos) {
        // Save photos and get their URLs
        List<String> photoUrls = new ArrayList<>();
        for (MultipartFile photo : photos) {
            String photoUrl = photoStorageService.savePhoto(photo);
            photoUrls.add(photoUrl);
        }

        // Create and save the Post entity
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setPhotoUrls(photoUrls);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public Comment addCommentToPost(CommentDto commentDto) {
        commentDto.setCreatedAt(LocalDateTime.now());
        return commentService.addComment(commentDto);
    }

    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }
}
