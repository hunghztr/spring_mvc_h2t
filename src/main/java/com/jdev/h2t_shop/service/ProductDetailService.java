package com.jdev.h2t_shop.service;


import com.jdev.h2t_shop.model.Color;
import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.model.ProductDetail;
import com.jdev.h2t_shop.model.Size;

import java.util.List;

public interface ProductDetailService {
    ProductDetail create(ProductDetail productDetail);
    ProductDetail update(ProductDetail productDetail);
    void deleteById(int id);
    ProductDetail getById(int id);
    long count();
    ProductDetail getAllBySizeAndColorAndProduct(Size size, Color color, Product product);
}
