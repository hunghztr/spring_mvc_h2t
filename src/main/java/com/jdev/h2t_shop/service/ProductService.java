package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    long count();
    Page<Product> getAll(Specification<Product> spec, Pageable pageable);
    List<Product> getAllByIdIn(List<Integer> ids);
    Product getById(int id);
    Product create(Product product);
    Product update(Product product);
    void deleteById(int id);
    void saveImage(MultipartFile file, Product product);
    List<Product> getAllByCategory(int categoryId);
    Page<Product> getPageByCategory(int categoryId, Pageable pageable);
    Page<Product> getPageByPriceLessThan(double price, Pageable pageable);
    Page<Product> getPageByPriceBetween(double price1,double price2, Pageable pageable);
    Page<Product> getPageByDiscountedPriceBetween(double price1,double price2, Pageable pageable);
    List<Product> getAllProducts();
}
