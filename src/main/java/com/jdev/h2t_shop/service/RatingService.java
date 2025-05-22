package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Rating;

import java.util.List;

public interface RatingService {
    Rating create(Rating rating);
    void deleteById(int id);
    List<Rating> getAll();
    List<Rating> getAllByProductId(int id);
}
