package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.CartDetail;

public interface CartDetailService {
    CartDetail create(CartDetail cartDetail);
    void deleteById(int id);
}
