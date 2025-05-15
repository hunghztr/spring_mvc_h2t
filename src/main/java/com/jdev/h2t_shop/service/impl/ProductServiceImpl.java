package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.repository.ProductRepository;
import com.jdev.h2t_shop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAll(){
        return this.productRepository.findAll();
    }
}
