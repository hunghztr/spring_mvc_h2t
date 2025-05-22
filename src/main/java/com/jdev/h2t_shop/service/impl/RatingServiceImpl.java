package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.model.ProductDetail;
import com.jdev.h2t_shop.model.Rating;
import com.jdev.h2t_shop.repository.RatingRepository;
import com.jdev.h2t_shop.service.ProductDetailService;
import com.jdev.h2t_shop.service.ProductService;
import com.jdev.h2t_shop.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final ProductService productService;

    public RatingServiceImpl(RatingRepository ratingRepository,
                             ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void deleteById(int id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByProductId(int id) {
        Product product = productService.getById(id);
        List<ProductDetail> details = product.getProductDetails();
        List<Rating> ratings = new ArrayList<>();
        details.forEach(i->ratings.addAll(i.getRatings()) );
        return ratings;
    }
}
