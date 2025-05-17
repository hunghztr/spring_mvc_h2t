package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {
    Category save(Category category);
    void deleteById(int id);
    Category findById(int id);
    Category findByName(String name);
    long count();
}
