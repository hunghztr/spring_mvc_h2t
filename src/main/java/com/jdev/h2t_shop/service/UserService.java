package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
}
