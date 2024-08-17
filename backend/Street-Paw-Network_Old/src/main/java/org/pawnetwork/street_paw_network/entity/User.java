package org.pawnetwork.street_paw_network.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String location;

}
