package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.Cart;
import com.jdev.h2t_shop.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    List<CartDetail> findByCartId(Integer cartId);
    CartDetail findByProductDetailIdAndCartId(Integer productDetailId, Integer cartId);
}
