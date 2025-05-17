package com.jdev.h2t_shop.service.specification;

import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.model.User;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> nameContains(String keyword) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
    }
}

