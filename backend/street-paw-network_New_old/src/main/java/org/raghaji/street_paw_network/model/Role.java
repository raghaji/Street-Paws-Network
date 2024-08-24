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
@Table(name="roles")
public class Role {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Integer id;
    @Column(unique = true)
    private String roleName;
    private String roleDescription;

}
