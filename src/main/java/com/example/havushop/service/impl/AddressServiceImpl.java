package com.example.havushop.service.impl;

import com.example.havushop.model.Address;
import com.example.havushop.model.User;
import com.example.havushop.repository.AddressRepository;
import com.example.havushop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressOfUser(User user) {
        return addressRepository.findAllByUser(user);
    }
}
