package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Image;
import com.jdev.h2t_shop.repository.ImageRepository;
import com.jdev.h2t_shop.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image getById(Integer id) {
        return imageRepository.getById(id);
    }

    @Override
    public Image update(Image image) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        imageRepository.deleteById(id);
    }


}
