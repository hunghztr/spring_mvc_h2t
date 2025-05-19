package com.jdev.h2t_shop.controller.client;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final CartDetailService cartDetailService;
    private final ProductService productService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final ProductDetailService productDetailService;
    private final CategoryService categoryService;

    public CartController(CartService cartService,
                          UserService userService,
                          CartDetailService cartDetailService,
                          ProductService productService,
                          SizeService sizeService,
                          ColorService colorService, ProductDetailService productDetailService,
                          CategoryService categoryService) {
        this.cartService = cartService;
        this.userService = userService;
        this.cartDetailService = cartDetailService;
        this.productService = productService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.productDetailService = productDetailService;
        this.categoryService = categoryService;
    }

    @GetMapping("/add-cart")
    public String addCart(Model model,@RequestParam(value = "productId",required = false) int id,
                          @RequestParam(value = "size",required = false,defaultValue = "none") String s,
                          @RequestParam(value = "color",required = false,defaultValue = "none") String c,
                          @RequestParam(value = "count",required = false,defaultValue = "1") int count, HttpSession session,
                          RedirectAttributes redirectAttributes) {

        Product product = productService.getById(id);
        Size size = sizeService.getByName(s);
        Color color = colorService.getbyName(c);
        ProductDetail detail = productDetailService.getAllBySizeAndColorAndProduct(size,color,product);
        if(detail == null) {
            redirectAttributes.addFlashAttribute("message", "sản phẩm này hiện chưa có");
            return "redirect:/product/"+id;
        }
        int userId = (int)session.getAttribute("id");
        Cart cart = cartService.create(detail,count,userId);
        session.setAttribute("sum",(int)session.getAttribute("sum")+count);
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String getCart(Model model,HttpSession session) {
        int userId = (int)session.getAttribute("id");
        Cart cart = cartService.getByUserId(userId);
        if(cart != null) {
            List<CartDetail> details = cart.getCartDetails();
            List<ProductDetail> productDetails = details.stream().map(i -> i.getProductDetail()).toList();
            List<Product> products = productDetails.stream().map(i -> i.getProduct()).toList();
            model.addAttribute("cart", cart);
            model.addAttribute("products", products);
        }
        model.addAttribute("categories", categoryService.getAll());
        return "client/home/cart";
    }
}
