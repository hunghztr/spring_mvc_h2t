package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>, JpaSpecificationExecutor<OrderDetail> {
    OrderDetail findById(int id);
    void deleteByIdIn(List<Integer> ids);
}
