package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Size;
import com.jdev.h2t_shop.repository.SizeRepository;
import com.jdev.h2t_shop.service.SizeService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    public SizeServiceImpl(SizeRepository sizeRepository) {
        this.sizeRepository = sizeRepository;
    }
    @Override
    public void deleteById(int id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public Size create(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public Size findById(int id) {
        return sizeRepository.findById(id).get();
    }

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size getByName(String name) {
        return sizeRepository.findByName(name);
    }

    @Override
    public long count() {
        return sizeRepository.count();
    }
}
