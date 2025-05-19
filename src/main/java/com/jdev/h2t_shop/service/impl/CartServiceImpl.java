package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.repository.CartDetailRepository;
import com.jdev.h2t_shop.repository.CartRepository;
import com.jdev.h2t_shop.repository.ProductRepository;
import com.jdev.h2t_shop.repository.UserRepository;
import com.jdev.h2t_shop.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    public CartServiceImpl(CartRepository cartRepository,
                           CartDetailRepository cartDetailRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Cart create(ProductDetail detail, int count,int userId) {
        User user = userRepository.findById(userId);
        Cart getCart = new Cart();
        if(user.getCart() != null) {
            getCart = user.getCart();
            getCart.setTotal(getCart.getTotal() + count);
            int price = (int)detail.getProduct().getPrice() * count;
            getCart.setPrice(getCart.getPrice() + price);
        }else{
            getCart.setTotal(count);
            int price = (int)detail.getProduct().getPrice() * count;
            getCart.setPrice(price);
        }
        getCart.setUser(userRepository.findById(userId));
        getCart = cartRepository.save(getCart);
        CartDetail cartDetail = cartDetailRepository.findByProductDetailIdAndCartId(detail.getId(),getCart.getId());
        if(cartDetail != null){
            cartDetail.setTotal(cartDetail.getTotal() + count);
        }else{
            cartDetail = new CartDetail();
            cartDetail.setCart(getCart);
            cartDetail.setProductDetail(detail);
            cartDetail.setTotal(count);
        }
        cartDetailRepository.save(cartDetail);
        return getCart;
    }

    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart getByUserId(int id) {
        return cartRepository.findByUserId(id);
    }
}
