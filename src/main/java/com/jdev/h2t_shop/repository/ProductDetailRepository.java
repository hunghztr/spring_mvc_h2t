package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> , JpaSpecificationExecutor<ProductDetail> {

}
