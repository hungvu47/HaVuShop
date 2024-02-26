package com.example.havushop.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;

    private ProductDTO productDTO;

    private int quantity;
}
