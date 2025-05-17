package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductService {
    Page<Product> getAll(Specification<Product> spec, Pageable pageable);
    Product getById(int id);
    Product create(Product product);
    Product update(Product product);
    void deleteById(int id);
}
