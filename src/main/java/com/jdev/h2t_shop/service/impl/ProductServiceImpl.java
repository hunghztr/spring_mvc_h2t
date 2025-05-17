package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.repository.ColorRepository;
import com.jdev.h2t_shop.repository.ProductDetailRepository;
import com.jdev.h2t_shop.repository.ProductRepository;
import com.jdev.h2t_shop.repository.SizeRepository;
import com.jdev.h2t_shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductDetailRepository productDetailRepository,
                              ColorRepository colorRepository,
                              SizeRepository sizeRepository) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
    }
    public Page<Product> getAll(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec, pageable);
    }
    @Override
    public Product getById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product create(Product product) {
        if(product.getCategory().getId() == null){
            product.setCategory(null);
        }

        ProductDetail detail = new ProductDetail();

        if(product.getProductDetails().getColor().getId() != null){
            Color c = colorRepository.findById(product.getProductDetails().getColor().getId()).get();
            detail.setColor(c);
            product.getProductDetails().setColor(null);
        }
        if(product.getProductDetails().getSize().getId() != null){
            Size s = sizeRepository.findById(product.getProductDetails().getSize().getId()).get();
            detail.setSize(s);
            product.getProductDetails().setSize(null);
        }
        product.setProductDetails(null);
        Product get = productRepository.save(product);
        detail.setProduct(get);
        productDetailRepository.save(detail);
        return get;
    }

    @Override
    public Product update(Product product) {
        if(product.getCategory().getId() == null){
            product.setCategory(null);
        }
        ProductDetail detail = new ProductDetail();
        if(productDetailRepository.findById(product.getProductDetails().getId()).isPresent())
             detail = productDetailRepository.findById(product.getProductDetails().getId()).get();

        if(product.getProductDetails().getColor().getId() != null){
            Color c = colorRepository.findById(product.getProductDetails().getColor().getId()).get();
            detail.setColor(c);
            product.getProductDetails().setColor(null);
        }
        if(product.getProductDetails().getSize().getId() != null){
            Size s = sizeRepository.findById(product.getProductDetails().getSize().getId()).get();
            detail.setSize(s);
            product.getProductDetails().setSize(null);
        }
        product.setProductDetails(null);
        Product get = productRepository.save(product);
        detail.setProduct(get);
        productDetailRepository.save(detail);
        return get;
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

}
