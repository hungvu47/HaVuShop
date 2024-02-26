package com.example.havushop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ImageId")
    private Integer imageId;

    @Column(name = "ImagePath")
    private String ImagePath;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "IsPrimary")
    private boolean isPrimary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return Objects.equals(imageId, image.imageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId);
    }
}
