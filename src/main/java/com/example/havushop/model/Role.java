package com.example.havushop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Long roleId;

    @Column(name = "roleName")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
