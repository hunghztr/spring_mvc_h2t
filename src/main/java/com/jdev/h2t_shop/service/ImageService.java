package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Image;

public interface ImageService {
    Image create(Image image);
    Image getById(Integer id);
    Image update(Image image);
    void deleteById(Integer id);
}
