package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Cart;
import com.jdev.h2t_shop.model.ProductDetail;

public interface CartService {
    Cart create(ProductDetail detail,int count,int idUser);
    void deleteById(int id);
    Cart getByUserId(int id);
}
