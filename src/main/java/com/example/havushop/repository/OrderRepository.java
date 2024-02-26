package com.example.havushop.repository;

import com.example.havushop.model.Order;
import com.example.havushop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserOrderByOrderDateDesc(User user);
}
