package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Category;
import com.jdev.h2t_shop.model.Sale;
import com.jdev.h2t_shop.repository.CategoryRepository;
import com.jdev.h2t_shop.service.CategoryService;
import com.jdev.h2t_shop.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final SaleService saleService;
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               SaleService saleService) {
        this.categoryRepository = categoryRepository;
        this.saleService = saleService;
    }

    @Override
    public Category create(Category category) {
        if(category.getSale() == null ||category.getSale().getId() == null) {
            category.setSale(saleService.getByDiscountAndFreeship(0, false));
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        if(category.getSale() != null){
            Sale sale = saleService.getById(category.getSale().getId());
            category.setSale(sale);
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }
}
