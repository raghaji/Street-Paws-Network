package org.raghaji.demo.Assets;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_Name", nullable = false, updatable = false)
    private String first_Name;

    @Column(name = "last_Name", nullable = false, updatable = false)
    private String last_Name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String username, String first_Name, String last_Name, String password, String email) {
        this.username = username;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.password = password;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfirst_Name() {
        return first_Name;
    }

    public void setfirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getlast_Name() {
        return last_Name;
    }

    public void setlast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getdisplayName() {
        return first_Name + " " + last_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
