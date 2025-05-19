package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.CartDetail;

import java.util.List;

public interface CartDetailService {
    CartDetail create(CartDetail cartDetail);
    void deleteById(int id);
    List<CartDetail> getAllByCartId(int id);
    CartDetail getByProductDetailIdAndCartId(int productDetailId, int cartId);
}
