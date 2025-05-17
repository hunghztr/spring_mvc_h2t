package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Role;
import com.jdev.h2t_shop.repository.RoleRepository;
import com.jdev.h2t_shop.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public Role getByName(String name){
        return this.roleRepository.findByName(name);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role) ;
    }

    @Override
    public long count() {
        return roleRepository.count();
    }

}
