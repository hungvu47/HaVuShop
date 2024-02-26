package com.example.havushop.utility;

import com.example.havushop.dto.AddressDTO;
import com.example.havushop.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {
    public Address convertDtoToEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setProvince(addressDTO.getProvinceName());
        address.setDistrict(addressDTO.getDistrictName());
        address.setWards(addressDTO.getWardsName());
        address.setDetails(addressDTO.getDetails());
        // Các bước chuyển đổi khác nếu cần
        return address;
    }
}
