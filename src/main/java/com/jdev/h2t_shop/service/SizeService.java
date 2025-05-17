package com.jdev.h2t_shop.service;


import com.jdev.h2t_shop.model.Size;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface SizeService {
    void deleteById(int id);
    Size create(Size size);
    Size findById(int id);
    List<Size> getAll();
    Size getByName(String name);
    long count();
}
