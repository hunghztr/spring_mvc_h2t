package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
}
