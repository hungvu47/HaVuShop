package com.example.havushop.service.impl;

import com.example.havushop.enums.OrderStatus;
import com.example.havushop.model.*;
import com.example.havushop.repository.CartItemRepository;
import com.example.havushop.repository.CartRepository;
import com.example.havushop.repository.OrderDetailRepository;
import com.example.havushop.repository.OrderRepository;
import com.example.havushop.service.CartService;
import com.example.havushop.service.OrderService;
import com.example.havushop.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Override
    @Transactional
    public void saveOrder(Cart cart) {
        Order order = new Order();
        order.setOrderStatus("Chờ xác nhận");
        order.setOrderDate(new Date());
        order.setUser(cart.getUser());

        double totalAmount = 0.0;

        for (CartItem cartItem : cart.getCartItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getProduct().getPrice());
            orderDetail.setTotal(cartItem.getQuantity() * cartItem.getProduct().getPrice());
            orderDetail.setOrder(order);
            order.getOrderDetails().add(orderDetail);

            totalAmount += orderDetail.getTotal();
        }
        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

    @Override
    public List<Order> getAllOrderOfUser(User user) {
        return orderRepository.findAllByUserOrderByOrderDateDesc(user);
    }

    @Override
    public Order getOrderOfUser(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
