package com.example.havushop.dto;

import com.example.havushop.model.User;
import lombok.Data;

@Data
public class AddressDTO {
    private Long id;

    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardsId;
    private String wardsName;
    private String details;

    private User user;
}
