package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Integer>, JpaSpecificationExecutor<Sale> {
    void deleteById(int id);
    Sale findByDiscountAndIsFreeship(int discount, boolean freeship);
}
