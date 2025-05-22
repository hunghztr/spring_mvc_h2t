package com.jdev.h2t_shop.service;

import com.jdev.h2t_shop.model.Cart;
import com.jdev.h2t_shop.model.ProductDetail;
import com.jdev.h2t_shop.model.Sale;
import com.jdev.h2t_shop.model.dto.ListDto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CartService {
    Cart create(ProductDetail detail,int count,int idUser);
    void deleteById(int id);
    Cart getByUserId(int id);
    void removeProductDetail(ProductDetail detail,double price,int count,Cart cart);
    List<ListDto<ProductDetail, Sale,Integer>> getlistDto(Cart cart);
    int addOrder(String note,String method,int userId);
    void addCodOrder(HttpSession session);
}
