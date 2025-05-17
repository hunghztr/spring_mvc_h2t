package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Sale;

import java.util.List;

public interface SaleService {
    List<Sale> getAll();
    Sale create(Sale sale);
    void delete(int id);
    Sale getById(int id);
    long count();
    Sale getByDiscountAndFreeship(int discount, boolean freeship);
}
