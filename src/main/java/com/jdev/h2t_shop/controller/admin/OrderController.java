package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.Order;
import com.jdev.h2t_shop.model.OrderDetail;
import com.jdev.h2t_shop.model.Product;
import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.service.OrderDetailService;
import com.jdev.h2t_shop.service.OrderService;
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
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
        List<Product> products =
                order.getOrderDetails().stream().map(od -> od.getProduct()).toList();
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
