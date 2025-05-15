package com.jdev.h2t_shop.controller.admin.product;

import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public String home(Model model){
        List<Product> products = this.productService.getAll();
        model.addAttribute("products", products);
        return "admin/product/show";
    }
}
