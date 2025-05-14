package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.repository.UserRepository;
import org.springframework.stereotype.Service;


public interface UserService {

     User getUserByUsername(String username);
     User create(User user);
}
