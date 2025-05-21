package com.jdev.h2t_shop.controller.client;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.model.dto.ListDto;
import com.jdev.h2t_shop.service.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class CartController {
    private final CartService cartService;

    private final ProductService productService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final ProductDetailService productDetailService;
    private final CategoryService categoryService;
    private final MailService mailService;
    private final OrderService orderService;
    public CartController(CartService cartService,
                          ProductService productService,
                          SizeService sizeService,
                          ColorService colorService, ProductDetailService productDetailService,
                          CategoryService categoryService,
                          MailService mailService,
                          OrderService orderService) {
        this.cartService = cartService;
        this.productService = productService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.productDetailService = productDetailService;
        this.categoryService = categoryService;
        this.mailService = mailService;
        this.orderService = orderService;
    }

    @GetMapping("/add-cart")
    public String addCart(Model model, @RequestParam(value = "productId", required = false) int id,
                          @RequestParam(value = "size", required = false, defaultValue = "none") String s,
                          @RequestParam(value = "color", required = false, defaultValue = "none") String c,
                          @RequestParam(value = "count", required = false, defaultValue = "1") int count, HttpSession session,
                          RedirectAttributes redirectAttributes) {

        Product product = productService.getById(id);
        Size size = sizeService.getByName(s);
        Color color = colorService.getbyName(c);
        ProductDetail detail = productDetailService.getAllBySizeAndColorAndProduct(size, color, product);
        if (detail == null) {
            redirectAttributes.addFlashAttribute("message", "sản phẩm này hiện chưa có");
            return "redirect:/product/" + id;
        }
        int userId = (int) session.getAttribute("id");
        Cart cart = cartService.create(detail, count, userId);
        session.setAttribute("sum", (int) session.getAttribute("sum") + count);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {
        int userId = (int) session.getAttribute("id");
        Cart cart = cartService.getByUserId(userId);
        if (cart != null) {
            List<CartDetail> details = cart.getCartDetails();
            List<Integer> totals = details.stream().map(i -> i.getTotal()).toList();
            List<ProductDetail> productDetails = details.stream().map(i -> i.getProductDetail()).toList();
            List<Product> products = productDetails.stream().map(i -> i.getProduct()).toList();
            List<Size> sizes = productDetails.stream().map(i -> i.getSize()).toList();
            List<Color> colors = productDetails.stream().map(i -> i.getColor()).toList();
            model.addAttribute("cart", cart);
            model.addAttribute("products", products);
            model.addAttribute("sizes", sizes);
            model.addAttribute("colors", colors);
            model.addAttribute("totals", totals);
        }
        model.addAttribute("categories", categoryService.getAll());
        return "client/home/cart";
    }

    @PostMapping("/remove-cart")
    public String remove(@RequestParam(value = "price", required = false) String price,
                         @RequestParam(value = "count", required = false, defaultValue = "1") String count,
                         @RequestParam(value = "productId", required = false) int productId,
                         @RequestParam(value = "size", required = false, defaultValue = "none") String size,
                         @RequestParam(value = "color", required = false, defaultValue = "none") String color,
                         HttpSession session) {
        Cart cart = cartService.getByUserId((int) session.getAttribute("id"));
        double priceDb = Double.parseDouble(price);
        int countInt = Integer.parseInt(count);
        ProductDetail detail =
                productDetailService.getAllBySizeAndColorAndProduct(sizeService.getByName(size),
                        colorService.getbyName(color), productService.getById(productId));
        cartService.removeProductDetail(detail, priceDb, countInt, cart);
        session.setAttribute("sum", (int) session.getAttribute("sum") - countInt);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        Cart cart = cartService.getByUserId((int) session.getAttribute("id"));
        var list = cartService.getlistDto(cart);
        model.addAttribute("list", list);
        model.addAttribute("categories", categoryService.getAll());
        return "client/home/payment";
    }

    @PostMapping("/checkout")
    public String postCheckout(@RequestParam(value = "fullname", required = false, defaultValue = "none") String fullname,
                               @RequestParam(value = "email", required = false, defaultValue = "none@gmail.com") String email,
                               @RequestParam(value = "address", required = false, defaultValue = "none") String address,
                               @RequestParam(value = "note", required = false, defaultValue = "none") String note,
                               @RequestParam(value = "paymentMethod", required = false, defaultValue = "none") String method,
                               HttpSession session) {
        note += ", " + fullname + ", " + email + ", " + address;
        int orderId = cartService.addOrder(note, method, (int) session.getAttribute("id"));
        Order order = orderService.getById(orderId);
        try {
            mailService.sendOrderConfirmationEmail(email, fullname,
                    orderId,order.getPrice(),order.getTotal());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        session.setAttribute("sum", 0);
        return "redirect:/";
    }
}
