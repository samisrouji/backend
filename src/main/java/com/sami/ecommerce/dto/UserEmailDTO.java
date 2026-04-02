package com.sami.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEmailDTO {
    private Long id;
    private String name;
    private String email;
}
