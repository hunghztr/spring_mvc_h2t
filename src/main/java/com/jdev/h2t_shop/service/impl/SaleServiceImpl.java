package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.Sale;
import com.jdev.h2t_shop.repository.SaleRepository;
import com.jdev.h2t_shop.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<Sale> getAll() {
        return saleRepository.findAll();
    }
    @Override
    public Sale create(Sale sale) {
        return saleRepository.save(sale);
    }
    @Override
    public void delete(int id){
         saleRepository.deleteById(id);
    }
    @Override
    public Sale getById(int id){
        return saleRepository.findById(id).orElse(null);
    }

    @Override
    public long count() {
        return saleRepository.count();
    }


    @Override
    public Sale getByDiscountAndFreeship(int discount, boolean freeship) {
        return saleRepository.findByDiscountAndIsFreeship(discount, freeship);
    }
}
