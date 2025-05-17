package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Role;

import java.util.List;

public interface RoleService {
    Role getByName(String name);
    List<Role> getAll();
    Role create(Role role);
    long count();
}
