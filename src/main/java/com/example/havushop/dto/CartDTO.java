package com.example.havushop.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CartDTO {
    private Long cart_id;

    private UserDTO userDTO;

    private double totalPrice;

    private int totalItems;

    private Set<CartItemDTO> cartItems;
}
