package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.Category;
import com.jdev.h2t_shop.model.Sale;
import com.jdev.h2t_shop.service.CategoryService;
import com.jdev.h2t_shop.service.SaleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final SaleService saleService;
    public CategoryController(CategoryService categoryService,
                              SaleService saleService) {
        this.categoryService = categoryService;
        this.saleService = saleService;
    }
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("categories",categoryService.getAll());
        return "admin/category/show";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("sales", saleService.getAll());
        return "admin/category/create";
    }
    @PostMapping("/create")
    public String postCreate(@ModelAttribute("category") Category category){
        categoryService.create(category);
        return "redirect:/admin/category/";
    }
    @PostMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") int id){
        categoryService.deleteById(id);
        return "redirect:/admin/category/";
    }
    @GetMapping("/update/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("sales", saleService.getAll());
        return "admin/category/update";
    }
    @PostMapping("/update")
    public String postEdit( @ModelAttribute("category") Category category){
        categoryService.update(category);
        return "redirect:/admin/category/";
    }
}
