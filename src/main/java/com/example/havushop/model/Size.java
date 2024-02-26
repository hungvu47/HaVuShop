package com.example.havushop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Size")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SizeId")
    private Long sizeId;

    @Column(name = "SizeName")
    private String sizeName;

    @ManyToMany(mappedBy = "sizes")
    private Set<Product> products = new HashSet<>();
}
