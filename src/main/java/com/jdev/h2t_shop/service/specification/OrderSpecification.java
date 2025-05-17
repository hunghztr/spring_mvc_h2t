package com.jdev.h2t_shop.service.specification;

import com.jdev.h2t_shop.model.Order;
import com.jdev.h2t_shop.model.User;
import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification {
    public static Specification<Order> idContains(String keyword) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("id")), "%" + keyword.toLowerCase() + "%");
    }
}

