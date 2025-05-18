package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Cart;
import com.jdev.h2t_shop.repository.CartRepository;
import com.jdev.h2t_shop.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart create(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }
}
