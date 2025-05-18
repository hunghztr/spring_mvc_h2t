package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.service.*;
import com.jdev.h2t_shop.service.specification.ProductSpecification;
import com.jdev.h2t_shop.service.specification.UserSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final ProductDetailService productDetailService;
    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             SizeService sizeService,
                             ColorService colorService,
                             ProductDetailService productDetailService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.productDetailService = productDetailService;
    }
    @GetMapping("/")
    public String home(Model model,@RequestParam(value = "keyword", required = false) String keyword,
                       @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Specification<Product> spec = Specification.where(null);

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and(ProductSpecification.nameContains(keyword));
        }
        var products = this.productService.getAll(spec,pageable);
        model.addAttribute("products", products);
        return "admin/product/show";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "admin/product/create";
    }
    @PostMapping("/create")
    public String postCreate(@ModelAttribute("product") Product product,
                             @RequestParam("imageFile1") MultipartFile file1,
                             @RequestParam("imageFile2") MultipartFile file2,
                             @RequestParam("imageFile3") MultipartFile file3,
                             @RequestParam("imageFile4") MultipartFile file4){
        productService.create(product);
        productService.saveImage(file1,product);
        productService.saveImage(file2,product);
        productService.saveImage(file3,product);
        productService.saveImage(file4,product);
        productService.create(product);
        return "redirect:/admin/product/";
    }
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") int id, Model model){
        Product product = productService.getById(id);
        var details = product.getProductDetails();
        Date createdAt = Date.from(product.getCreatedAt());
        Date updatedAt = product.getUpdatedAt() != null ? Date.from(product.getUpdatedAt()) : null;
        model.addAttribute("product",product);
        model.addAttribute("details",details);
        model.addAttribute("createdAt", createdAt);
        model.addAttribute("updatedAt", updatedAt);
        return "admin/product/view";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        Product product = productService.getById(id);

        if(product.getCategory() == null){
            Category category = categoryService.findByName("none");
            product.setCategory(category);
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        return "admin/product/update";
    }
    @PostMapping("/update")
    public String postUpdate(@ModelAttribute("product") Product product){
        productService.create(product);
        return "redirect:/admin/product/";
    }
    @PostMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") int id){
        productService.deleteById(id);
        return "redirect:/admin/product/";
    }
    @GetMapping("/add-detail/{id}")
    public String addDetail(@PathVariable("id") int id, Model model){
        Product product = productService.getById(id);
        ProductDetail detail = new ProductDetail();
        detail.setColor(colorService.getbyName("none"));
        detail.setSize(sizeService.getByName("none"));
        model.addAttribute("product",product);
        model.addAttribute("sizes",sizeService.getAll());
        model.addAttribute("colors",colorService.getAll());
        model.addAttribute("detail",detail);
        return "admin/product/add-detail";
    }
    @PostMapping("/add-detail")
    public String postAddDetail(@ModelAttribute("detail") ProductDetail detail,
                                @RequestParam("id") int idProduct){
        detail.setId(0);
        Product product = productService.getById(idProduct);
        detail.setProduct(product);
        productDetailService.create(detail);
        return "redirect:/admin/product/";
    }
}
