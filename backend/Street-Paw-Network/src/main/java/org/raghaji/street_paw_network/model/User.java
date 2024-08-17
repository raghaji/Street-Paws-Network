package org.raghaji.street_paw_network.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Integer id;
    @Column(unique=true, nullable = false)
    private String username;
    @Column(unique=true, nullable = false)
    private String email;
    private String first_name;
    private String last_name;
    @Column(nullable = false)
    private String password;
    private String location;
}
