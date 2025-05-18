package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Category;
import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.model.Sale;
import com.jdev.h2t_shop.repository.CategoryRepository;
import com.jdev.h2t_shop.repository.ProductRepository;
import com.jdev.h2t_shop.repository.SaleRepository;
import com.jdev.h2t_shop.service.CategoryService;
import com.jdev.h2t_shop.service.ProductService;
import com.jdev.h2t_shop.service.SaleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               SaleRepository saleRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Category create(Category category) {
        if(category.getSale() == null ||category.getSale().getId() == null) {
            category.setSale(saleRepository.findByDiscountAndIsFreeship(0, false));
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        if(category.getSale() != null){
            Sale sale = saleRepository.findById(category.getSale().getId()).get();
            category.setSale(sale);
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(int id) {
        List<Product> products = productRepository.findAllByCategoryId(id);
        products.forEach(i -> i.setCategory(categoryRepository.findByName("none")));
        productRepository.saveAll(products);
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
