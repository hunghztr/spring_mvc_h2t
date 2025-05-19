package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.service.OrderDetailService;
import com.jdev.h2t_shop.service.OrderService;
import com.jdev.h2t_shop.service.ProductService;
import com.jdev.h2t_shop.service.specification.OrderSpecification;
import com.jdev.h2t_shop.service.specification.UserSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    public OrderController(OrderService orderService,
                           ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }
    @GetMapping("/")
    public String home(Model model,@RequestParam(value = "keyword", required = false) String keyword,
                       @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Specification<Order> spec = Specification.where(null);

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and(OrderSpecification.idContains(keyword));
        }
        model.addAttribute("orders",orderService.getAll(spec,pageable));
       return "admin/order/show";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        Order order = orderService.getById(id);
        List<ProductDetail> details =
                order.getOrderDetails().stream().map(od -> od.getProductDetail()).toList();
        List<Integer> ids = details.stream().map(i -> i.getProduct().getId()).toList();
        List<Product> products = productService.getAllByIdIn(ids);
        model.addAttribute("order",order);
        model.addAttribute("products",products);
        return "admin/order/update";
    }
    @PostMapping("/update")
    public String postUpdate(@ModelAttribute("order") Order order){
        orderService.create(order);
        return "redirect:/admin/order/";
    }
    @PostMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") int idOrder){
        orderService.deleteDetails(idOrder);
        return "redirect:/admin/order/";
    }
}
