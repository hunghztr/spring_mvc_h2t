package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Role;
import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.repository.RoleRepository;
import com.jdev.h2t_shop.repository.UserRepository;
import com.jdev.h2t_shop.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User getUserByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
    public User create(User user){
        if(this.getUserByUsername(user.getUsername()) != null){
            return null;
        }
        if(user.getRole() == null) {
            user.setRole(this.roleRepository.findByName("ROLE_USER"));
        }
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User getUser = this.userRepository.findById(user.getId()).get();
        user.setPassword(getUser.getPassword());
        user.setCreatedAt(getUser.getCreatedAt());
        user.setUpdatedAt(getUser.getUpdatedAt());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Page<User> getAll(Specification<User> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }

    @Override
    public long count() {
        return userRepository.count();
    }
}
