package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(Category category);
    Category update(Category category);
    void deleteById(int id);
    Category findById(int id);
    List<Category> getAll();
    Category findByName(String name);
    long count();
}
