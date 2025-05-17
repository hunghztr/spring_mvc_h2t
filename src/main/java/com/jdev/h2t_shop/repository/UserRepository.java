package com.jdev.h2t_shop.repository;

import com.jdev.h2t_shop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
    User findByUsername(String username);
    User save(User user);
    void deleteById(int id);
    User findById(int id);
    Page<User> findAll(Pageable pageable);
    Page<User> findAll(Specification<User> spec, Pageable pageable);
}
