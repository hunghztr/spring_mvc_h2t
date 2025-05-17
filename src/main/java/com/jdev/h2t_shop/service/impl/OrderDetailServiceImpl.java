package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.OrderDetail;
import com.jdev.h2t_shop.repository.OrderDetailRepository;
import com.jdev.h2t_shop.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }


    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public OrderDetail getById(int id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        orderDetailRepository.deleteByIdIn(ids);
    }
}
