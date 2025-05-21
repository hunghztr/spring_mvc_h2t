package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

     User getUserByUsername(String username);
     User create(User user);
     User update(User user);
     void deleteById(int id);
     User getById(int id);
     Page<User> getAll(Specification<User> spec, Pageable pageable);
     long count();
}
