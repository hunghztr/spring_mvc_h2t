package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.repository.ColorRepository;
import com.jdev.h2t_shop.repository.ProductDetailRepository;
import com.jdev.h2t_shop.repository.ProductRepository;
import com.jdev.h2t_shop.repository.SizeRepository;
import com.jdev.h2t_shop.service.CategoryService;
import com.jdev.h2t_shop.service.ImageService;
import com.jdev.h2t_shop.service.ProductService;
import com.jdev.h2t_shop.service.UpLoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ImageService imageService;
    private final UpLoadService upLoadService;
    private final CategoryService categoryService;
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductDetailRepository productDetailRepository,
                              ColorRepository colorRepository,
                              SizeRepository sizeRepository,
                              ImageService imageService,
                              UpLoadService upLoadService,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.imageService = imageService;
        this.upLoadService = upLoadService;
        this.categoryService = categoryService;
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
            product.setCategory(categoryService.findByName("none"));
        }

        if(product.getProductDetails() == null){
            product.setProductDetails(productDetailRepository.findById(1).get());
            return productRepository.save(product);
        }
        else {
            ProductDetail detail = product.getProductDetails();
            if (product.getProductDetails().getColor().getId() != null) {
                Color c = colorRepository.findById(product.getProductDetails().getColor().getId()).get();
                detail.setColor(c);
                product.getProductDetails().setColor(null);
            }
            if (product.getProductDetails().getSize().getId() != null) {
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
    }

    @Override
    public Product update(Product product) {
        if(product.getCategory().getId() == null){
            product.setCategory(categoryService.findByName("none"));
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
        Product product = this.getById(id);
        ProductDetail detail = product.getProductDetails();
        detail.setProduct(null);
        productDetailRepository.save(detail);
        productRepository.deleteById(id);
    }

    @Override
    public void saveImage(MultipartFile file, Product product) {
        if (file.getSize() > 0) {
            String image = this.upLoadService.handleUpLoadFile(file, "products");
            Image img = new Image();
            img.setName(image);
            img.setProduct(product);
            imageService.create(img);
        }
    }

}
