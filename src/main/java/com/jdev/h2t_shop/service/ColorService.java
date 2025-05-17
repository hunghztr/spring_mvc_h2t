package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Color;

import java.util.List;

public interface ColorService {
    void deleteById(int id);
    Color create(Color color);
    Color getById(int id);
    List<Color> getAll();
    Color getbyName(String name);
    long count();
}
