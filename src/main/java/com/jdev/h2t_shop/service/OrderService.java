package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Order;
import com.jdev.h2t_shop.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface OrderService {
    Order create(Order order);
    Order getById(int id);
    void deleteById(int id);
    Order update(Order order);
    Page<Order> getAll(Specification<Order> spec, Pageable pageable);
    void deleteDetails(int id);
    long count();
    List<Order> getAllOrders();
}
