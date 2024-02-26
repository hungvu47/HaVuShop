package com.example.havushop.repository;

import com.example.havushop.model.Address;
import com.example.havushop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUser(User user);
}
