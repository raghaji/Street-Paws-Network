package org.raghaji.street_paw_network.security.services;
import org.raghaji.street_paw_network.dto.CommentDto;
import org.raghaji.street_paw_network.models.Comment;
import org.raghaji.street_paw_network.models.Post;
import org.raghaji.street_paw_network.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PhotoStorageService photoStorageService;

    @Autowired
    private CommentService commentService; 

    public Post createPost(String content, List<MultipartFile> photos) {
        // Save photos and get their URLs
        List<String> photoUrls = new ArrayList<>();
        for (MultipartFile photo : photos) {
            String photoUrl = photoStorageService.savePhoto(photo);
            photoUrls.add(photoUrl);
        }

        // Create and save the Post entity
        Post post = new Post();
        post.setContent(content);
        post.setPhotoUrls(photoUrls);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public Comment addCommentToPost(CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }
}
