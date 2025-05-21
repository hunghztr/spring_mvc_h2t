package com.jdev.h2t_shop.controller.client;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.service.CategoryService;
import com.jdev.h2t_shop.service.ProductService;
import com.jdev.h2t_shop.service.specification.ProductSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;
    public HomeController(ProductService productService,
                          CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping("/")
    public String home(Model model,@RequestParam(value = "keyword", required = false) String keyword,
                       @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Specification<Product> spec = Specification.where(null);

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and(ProductSpecification.nameContains(keyword));
        }
        var products = this.productService.getAll(spec,pageable);
        model.addAttribute("products",products);
        model.addAttribute("categories",categoryService.getAll());
       return "client/home/home";
    }
    @GetMapping("/deny")
    public String denie(Model model){
        model.addAttribute("categories",categoryService.getAll());
        return "client/auth/denie";
    }
    @GetMapping("/product/{id}")
    public String detail(@PathVariable("id") int id, Model model){
        Product product = productService.getById(id);
        List<Color> colors = product.getProductDetails().stream().map(i -> i.getColor()).distinct().toList();
        List<Size> sizes = product.getProductDetails().stream().map(i -> i.getSize()).distinct().toList();
        var suggestions = productService.getAllByCategory(product.getCategory().getId());
        model.addAttribute("categories",categoryService.getAll());
        model.addAttribute("product",product);
        model.addAttribute("colors",colors);
        model.addAttribute("sizes",sizes);
        model.addAttribute("suggestions",suggestions);
        return "client/home/view";
    }

    @GetMapping("/filter-cate")
    public String filterCate(Model model,@RequestParam("cid") int cid,
                             @PageableDefault(size = 10, sort = "id",
                                     direction = Sort.Direction.DESC) Pageable pageable){
        Page<Product> products = this.productService.getPageByCategory(cid,pageable);
        model.addAttribute("categories",categoryService.getAll());
        model.addAttribute("products",products);
        return "client/home/filter";
    }
    @GetMapping("/sale")
    public String sale(Model model,
                       @PageableDefault(size = 10, sort = "id",
                               direction = Sort.Direction.DESC) Pageable pageable){
        Page<Product> products1 = productService.getPageByDiscountedPriceBetween(0,199000,pageable);
        Page<Product> products2 = productService.getPageByDiscountedPriceBetween(200000,299000,pageable);
        Page<Product> products3 = productService.getPageByDiscountedPriceBetween(300000,399000,pageable);

        model.addAttribute("products1",products1);
        model.addAttribute("products2",products2);
        model.addAttribute("products3",products3);
        model.addAttribute("categories",categoryService.getAll());
        return "client/home/sale";
    }
    @GetMapping("/introduce")
    public String introduce(Model model){
        model.addAttribute("categories",categoryService.getAll());
        return "client/home/introduce";
    }
    @GetMapping("/policy")
    public String policy(Model model){
        model.addAttribute("categories",categoryService.getAll());
        return "client/home/policy";
    }
}
