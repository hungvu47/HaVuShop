package com.example.havushop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "colorId")
    private Integer colorId;

    @Column(name = "ColorName")
    private String colorName;

    @ManyToMany(mappedBy = "colors")
    private Set<Product> products = new HashSet<>();

}
