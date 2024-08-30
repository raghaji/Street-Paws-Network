package org.raghaji.street_paw_network.dto;

import java.time.LocalDateTime;

import org.raghaji.street_paw_network.models.User;


public class CommentDto {
    
    private Long id;
    private Long postId;
    private String content;
    private LocalDateTime createdAt;
    private User user;
    
    public CommentDto(Long id, Long postId, String content, LocalDateTime createdAt, User user) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }
    public CommentDto() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPostId() {
        return postId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
