package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size,Integer>, JpaSpecificationExecutor<Size> {
    Size findByName(String name);
}
