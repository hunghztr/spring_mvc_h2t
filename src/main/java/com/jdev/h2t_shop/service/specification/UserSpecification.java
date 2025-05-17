package com.jdev.h2t_shop.service.specification;

import com.jdev.h2t_shop.model.User;
import org.springframework.data.jpa.domain.Specification;


public class UserSpecification {
    public static Specification<User> fullnameContains(String keyword) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("fullname")), "%" + keyword.toLowerCase() + "%");
    }
}

