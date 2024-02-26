package com.example.havushop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "Price")
    private double price;

    @Column(columnDefinition = "TEXT",name = "Description")
    private String description;

    @Column(name = "Discount")
    private String discount;

    @Column(name = "Status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_colors",
        joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "productId")},
        inverseJoinColumns = {@JoinColumn(name = "color_id", referencedColumnName = "colorId")})
    private Set<Color> colors = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "prodcut_sizes",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "productId")},
            inverseJoinColumns = {@JoinColumn(name = "size_id", referencedColumnName = "SizeId")})
    private Set<Size> sizes = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_image_id")
    private Image primaryImage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}
