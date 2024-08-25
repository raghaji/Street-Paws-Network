package org.raghaji.street_paw_network.dto;

import java.time.LocalDateTime;

public class CommentDto {
    
    private Long id;
    private Long postId;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;

    // Constructors
    public CommentDto() {}

    public CommentDto(Long id, Long postId, String content, Long userId, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    // Getters and Setters
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

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
