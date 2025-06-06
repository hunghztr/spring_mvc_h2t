package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Color;
import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.model.ProductDetail;
import com.jdev.h2t_shop.model.Size;
import com.jdev.h2t_shop.repository.ProductDetailRepository;
import com.jdev.h2t_shop.service.ProductDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    private ProductDetailRepository productDetailRepository;

    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @Override
    public ProductDetail create(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail update(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    @Override
    public void deleteById(int id) {
        productDetailRepository.deleteById(id);
    }

    @Override
    public ProductDetail getById(int id) {
        return productDetailRepository.findById(id).get();
    }

    @Override
    public long count() {
        return productDetailRepository.count();
    }

    @Override
    public ProductDetail getAllBySizeAndColorAndProduct(Size size, Color color, Product product) {
        return productDetailRepository.findBySizeAndColorAndProduct(size, color, product);
    }
}
