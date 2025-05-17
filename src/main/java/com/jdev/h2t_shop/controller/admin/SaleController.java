package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.Sale;
import com.jdev.h2t_shop.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/sale")
public class SaleController {
    private final SaleService saleService;
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping("/")
    public String home(Model model){
        var sales = this.saleService.getAll();
        model.addAttribute("sales", sales);
        return "admin/sale/show";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("sale", new Sale());
        return "admin/sale/create";
    }
    @PostMapping("/create")
    public String postCreate(@ModelAttribute("sale") Sale sale){
        saleService.create(sale);
        return "redirect:/admin/sale/";
    }
    @PostMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") int id){
        saleService.delete(id);
        return "redirect:/admin/sale/";
    }
}
