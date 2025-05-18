package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Color;
import com.jdev.h2t_shop.model.Size;
import com.jdev.h2t_shop.repository.ColorRepository;
import com.jdev.h2t_shop.repository.ProductDetailRepository;
import com.jdev.h2t_shop.service.ColorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ProductDetailRepository productDetailRepository;
    public ColorServiceImpl(ColorRepository colorRepository,
                            ProductDetailRepository productDetailRepository) {
        this.colorRepository = colorRepository;
        this.productDetailRepository = productDetailRepository;
    }
    @Override
    public void deleteById(int id) {
        Color color = colorRepository.findById(id).get();
        var detail = productDetailRepository.findAllByColor(color);
        detail.forEach(i -> i.setColor(colorRepository.findByName("none")));
        productDetailRepository.saveAll(detail);
        colorRepository.deleteById(id);
    }

    @Override
    public Color create(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color getById(int id) {
        return colorRepository.findById(id).get();
    }

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color getbyName(String name) {
        return colorRepository.findByName(name);
    }

    @Override
    public long count() {
        return colorRepository.count();
    }

}
