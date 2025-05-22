package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.Category;
import com.jdev.h2t_shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
    Page<Product> findAll(Pageable pageable);
    List<Product> findAllByIdIn(List<Integer> ids);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
    List<Product> findAllByCategoryId(int categoryId);
    Page<Product> findAllByPriceLessThan(double price, Pageable pageable);
    Page<Product> findAllByPriceBetween(double price1, double price2, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE (p.price - (p.price *p.category.sale.discount)/100) BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByDiscountedPriceBetween(
            @Param("minPrice") double minPrice,
            @Param("maxPrice") double maxPrice,
            Pageable pageable
    );
    long count();
}
