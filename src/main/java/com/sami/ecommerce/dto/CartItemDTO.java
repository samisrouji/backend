package com.sami.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItemDTO {
    private Long id;
    private String productName;
    private Double price;
    private Integer quantity;
    
    public Double getSubtotal() {
        return price * quantity;
    }
}
