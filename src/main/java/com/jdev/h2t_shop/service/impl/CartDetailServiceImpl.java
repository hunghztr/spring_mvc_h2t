package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.CartDetail;
import com.jdev.h2t_shop.repository.CartDetailRepository;
import com.jdev.h2t_shop.service.CartDetailService;
import org.springframework.stereotype.Service;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailServiceImpl(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    @Override
    public CartDetail create(CartDetail cartDetail) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
