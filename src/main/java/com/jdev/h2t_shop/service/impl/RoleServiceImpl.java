package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Role;
import com.jdev.h2t_shop.repository.RoleRepository;
import com.jdev.h2t_shop.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role getByName(String name){
        return this.roleRepository.findByName(name);
    }
}
