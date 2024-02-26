package com.example.havushop.service;

import com.example.havushop.model.Address;
import com.example.havushop.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    public Address saveAddress(Address address);

    public List<Address> getAddressOfUser(User user);
}
