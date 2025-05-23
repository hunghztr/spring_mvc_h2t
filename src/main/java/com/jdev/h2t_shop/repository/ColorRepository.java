package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color,Integer> , JpaSpecificationExecutor<Color> {
    Color findByName(String name);
}
