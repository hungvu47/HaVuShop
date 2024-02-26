package com.example.havushop.service;

import com.example.havushop.model.Cart;
import com.example.havushop.model.Order;
import com.example.havushop.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    public void saveOrder(Cart cart);

    public List<Order> getAllOrderOfUser(User user);

    public Order getOrderOfUser(Long id);

    public List<Order> getAllOrder();

    public void cancelOrder(Long id);
}
