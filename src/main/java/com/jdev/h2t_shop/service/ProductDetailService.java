package com.jdev.h2t_shop.service;


import com.jdev.h2t_shop.model.ProductDetail;

public interface ProductDetailService {
    ProductDetail create(ProductDetail productDetail);
    ProductDetail update(ProductDetail productDetail);
    void deleteById(int id);
    ProductDetail getById(int id);
    long count();
}
