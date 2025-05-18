package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.Color;
import com.jdev.h2t_shop.model.ProductDetail;
import com.jdev.h2t_shop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> , JpaSpecificationExecutor<ProductDetail> {
    List<ProductDetail> findAllBySize(Size size);
    List<ProductDetail> findAllByColor(Color color);
}
