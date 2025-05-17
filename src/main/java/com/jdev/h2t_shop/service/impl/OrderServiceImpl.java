package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Order;
import com.jdev.h2t_shop.model.OrderDetail;
import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.repository.OrderDetailRepository;
import com.jdev.h2t_shop.repository.OrderRepository;
import com.jdev.h2t_shop.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public Page<Order> getAll(Specification<Order> spec, Pageable pageable) {
        return orderRepository.findAll(spec, pageable);
    }

    @Override
    @Transactional
    public void deleteDetails(int id) {
        List<OrderDetail> orderDetails =
                this.getById(id).getOrderDetails();
        List<Integer> ids = orderDetails.stream().map(od -> od.getId()).toList();
        orderDetailRepository.deleteByIdIn(ids);
        this.deleteById(id);
    }
}
