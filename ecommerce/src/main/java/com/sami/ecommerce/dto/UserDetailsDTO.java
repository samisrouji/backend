package com.sami.ecommerce.dto;

import com.sami.ecommerce.entity.Address;
import com.sami.ecommerce.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsDTO {
    private Long id;
    private String name;
    private String email;
    private List<Address> addresses;
    private Profile profile;
}
