package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.Category;
import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.service.CategoryService;
import com.jdev.h2t_shop.service.OrderService;
import com.jdev.h2t_shop.service.ProductService;
import com.jdev.h2t_shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminHomeController {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;
    private final CategoryService categoryService;
    public AdminHomeController(ProductService productService,
                               OrderService orderService,
                               UserService userService,
                               CategoryService categoryService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
        this.categoryService = categoryService;
    }
    @GetMapping("/admin")
    public String home(Model model){
        var orders = orderService.getAllOrders();
        List<Category> categories = categoryService.getAll();
        List<Integer> prices = new ArrayList<>();

        List<String> categoryNames = categories.stream().map(i->i.getName()).toList();

        int price = orders.stream().mapToInt(i->i.getPrice()).sum();
        model.addAttribute("products",productService.count());
        model.addAttribute("orders",orderService.count());
        model.addAttribute("users",userService.count());
        model.addAttribute("totalPrice",price);
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("categoryCounts", List.of(10, 20, 15));
       return "admin/home/home";
    }
}
