package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail create(OrderDetail orderDetail);
    OrderDetail update(OrderDetail orderDetail);
    void deleteById(int id);
    OrderDetail getById(int id);
    void deleteByIds(List<Integer> ids);
}
