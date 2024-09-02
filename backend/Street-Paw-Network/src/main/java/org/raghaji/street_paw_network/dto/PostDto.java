package org.raghaji.street_paw_network.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.raghaji.street_paw_network.services.UserDetailsImpl;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private List<String> photoUrls;
    private LocalDateTime createdAt;
    private UserDetailsImpl user;
    private List<CommentDto> commentDtos;

    public PostDto() {
    }
    public List<CommentDto> getCommentDtos() {
        return commentDtos;
    }
    public void setCommentDtos(List<CommentDto> commentDtos) {
        this.commentDtos = commentDtos;
    }
    public PostDto(Long id, String title, String content, List<String> photoUrls, LocalDateTime createdAt, UserDetailsImpl user, List<CommentDto> commentDtos) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.photoUrls = photoUrls;
        this.createdAt = createdAt;
        this.user = user;
        this.commentDtos = commentDtos;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public List<String> getPhotoUrls() {
        return photoUrls;
    }
    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public UserDetailsImpl getUser() {
        return user;
    }
    public void setUser(UserDetailsImpl user) {
        this.user = user;
    }
    
}
