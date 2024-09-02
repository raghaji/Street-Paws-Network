package org.raghaji.street_paw_network.dto;

import java.time.LocalDateTime;

import org.raghaji.street_paw_network.services.UserDetailsImpl;


public class CommentDto {
    
    private Long id;
    private Long postId;
    private String content;
    private LocalDateTime createdAt;
    private UserDetailsImpl userDetailsImpl;
    
    public CommentDto(Long id, Long postId, String content, LocalDateTime createdAt, UserDetailsImpl userDetailsImpl) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;
        this.userDetailsImpl = userDetailsImpl;
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
    public UserDetailsImpl getUser() {
        return userDetailsImpl;
    }
    public void setUser(UserDetailsImpl userDetailsImpl) {
        this.userDetailsImpl = userDetailsImpl;
    }

}
