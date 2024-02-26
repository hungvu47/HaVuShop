package com.example.havushop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT",name = "details")
    private String details;

    @Column(name = "wards")
    private String wards;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
