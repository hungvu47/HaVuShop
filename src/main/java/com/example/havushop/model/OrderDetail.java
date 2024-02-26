package com.example.havushop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idOrderDetail")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "order_id")
        private Order order;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @Column(name = "quantity")
        private Integer quantity;

        @Column(name = "price")
        private Double price;

        @Column(name = "total")
        private Double total;
}
