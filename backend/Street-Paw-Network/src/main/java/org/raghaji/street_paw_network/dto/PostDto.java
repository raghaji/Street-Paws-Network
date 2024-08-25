package org.raghaji.street_paw_network.dto;

import java.util.List;

public class PostDto {
    private Long id;
    private String content;
    private List<String> photoUrls;

    // Constructors
    public PostDto() {}

    public PostDto(Long id, String content, List<String> photoUrls) {
        this.id = id;
        this.content = content;
        this.photoUrls = photoUrls;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
