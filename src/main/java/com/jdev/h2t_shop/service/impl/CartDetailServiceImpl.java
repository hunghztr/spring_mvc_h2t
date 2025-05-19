package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.CartDetail;
import com.jdev.h2t_shop.repository.CartDetailRepository;
import com.jdev.h2t_shop.service.CartDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailServiceImpl(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    @Override
    public CartDetail create(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public void deleteById(int id) {
        cartDetailRepository.deleteById(id);
    }

    @Override
    public List<CartDetail> getAllByCartId(int id) {
        return cartDetailRepository.findByCartId(id);
    }

    @Override
    public CartDetail getByProductDetailIdAndCartId(int productDetailId, int cartId) {
        return cartDetailRepository.findByProductDetailIdAndCartId(productDetailId,cartId);
    }
}
