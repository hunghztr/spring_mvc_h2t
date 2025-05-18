package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Cart;

public interface CartService {
    Cart create(Cart cart);
    void deleteById(int id);
}
