package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Role;
import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.repository.RoleRepository;
import com.jdev.h2t_shop.repository.UserRepository;
import com.jdev.h2t_shop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        user.setRole(this.roleRepository.findByName("ROLE_USER"));
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return this.userRepository.save(user);
    }
}
