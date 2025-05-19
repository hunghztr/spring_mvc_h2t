package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.repository.*;
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

    private final ImageRepository imageRepository;
    private final UpLoadService upLoadService;
    private final CategoryService categoryService;
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductDetailRepository productDetailRepository,
                              ImageRepository imageRepository,
                              UpLoadService upLoadService,
                              CategoryService categoryService) {
        this.productRepository = productRepository;
        this.productDetailRepository = productDetailRepository;
        this.imageRepository = imageRepository;
        this.upLoadService = upLoadService;
        this.categoryService = categoryService;
    }
    public Page<Product> getAll(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec, pageable);
    }

    @Override
    public List<Product> getAllByIdIn(List<Integer> ids) {
        return productRepository.findAllByIdIn(ids);
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
            return productRepository.save(product);

    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        Product product = this.getById(id);
        List<ProductDetail> detail = product.getProductDetails();
        detail.forEach(i -> productDetailRepository.deleteById(i.getId()));
        List<Image> images = productRepository.findById(id).get().getImages();
        images.forEach(i -> imageRepository.deleteById(i.getId()));
        productRepository.deleteById(id);
    }

    @Override
    public void saveImage(MultipartFile file, Product product) {
        if (file.getSize() > 0) {
            String image = this.upLoadService.handleUpLoadFile(file, "products");
            Image img = new Image();
            img.setName(image);
            img.setProduct(product);
            imageRepository.save(img);
        }
    }

    @Override
    public List<Product> getAllByCategory(int categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Page<Product> getPageByCategory(int categoryId, Pageable pageable) {
        Category category = categoryService.findById(categoryId);
        return productRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Page<Product> getPageByPriceLessThan(double price, Pageable pageable) {
        return productRepository.findAllByPriceLessThan(price, pageable);
    }

    @Override
    public Page<Product> getPageByPriceBetween(double price1,double price2 ,Pageable pageable) {
        return productRepository.findAllByPriceBetween(price1,price2,pageable);
    }

    @Override
    public Page<Product> getPageByDiscountedPriceBetween(double price1, double price2, Pageable pageable) {
        return productRepository.findByDiscountedPriceBetween(price1,price2,pageable);
    }

}
