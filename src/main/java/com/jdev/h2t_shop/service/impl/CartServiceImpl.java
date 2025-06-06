package com.jdev.h2t_shop.service.impl;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.model.dto.ListDto;
import com.jdev.h2t_shop.repository.*;
import com.jdev.h2t_shop.service.CartService;
import com.jdev.h2t_shop.service.OrderDetailService;
import com.jdev.h2t_shop.service.OrderService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;
    private final ProductDetailRepository productDetailRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final MailService mailService;
    public CartServiceImpl(CartRepository cartRepository,
                           CartDetailRepository cartDetailRepository,
                           UserRepository userRepository,
                           ProductDetailRepository productDetailRepository,
                           OrderRepository orderRepository,
                           OrderDetailRepository orderDetailRepository,
                           MailService mailService) {
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userRepository = userRepository;
        this.productDetailRepository = productDetailRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.mailService = mailService;
    }


    @Override
    public Cart create(ProductDetail detail, int count, int userId) {
        User user = userRepository.findById(userId);
        Cart getCart = new Cart();
        if (user.getCart() != null) {
            getCart = user.getCart();
            getCart.setTotal(getCart.getTotal() + count);
            int price = (int) detail.getProduct().getPrice() * count;
            if(detail.getProduct().getCategory().getSale() != null){
                double temp =
                        detail.getProduct().getPrice() - (detail.getProduct().getPrice() * detail.getProduct().getCategory().getSale().getDiscount()/100);
                log.info("Sale: " +
                        temp);
                price = (int) temp*count;
                log.info(price+"");
            }

            getCart.setPrice(getCart.getPrice() + price);
        } else {
            getCart.setTotal(count);
            int price = (int) detail.getProduct().getPrice() * count;
            getCart.setPrice(price);
        }
        getCart.setUser(userRepository.findById(userId));
        getCart = cartRepository.save(getCart);
        CartDetail cartDetail = cartDetailRepository.findByProductDetailIdAndCartId(detail.getId(), getCart.getId());
        if (cartDetail != null) {
            cartDetail.setTotal(cartDetail.getTotal() + count);
        } else {
            cartDetail = new CartDetail();
            cartDetail.setCart(getCart);
            cartDetail.setProductDetail(detail);
            cartDetail.setTotal(count);
        }
        cartDetailRepository.save(cartDetail);
        return getCart;
    }

    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart getByUserId(int id) {
        return cartRepository.findByUserId(id);
    }

    @Transactional
    @Override
    public void removeProductDetail(ProductDetail detail, double price, int count, Cart cart) {
        cartDetailRepository.deleteByCartAndProductDetail(cart, detail);
        cart.setTotal(cart.getTotal() - count);
        cart.setPrice(cart.getPrice() - (int) price);
        cartRepository.save(cart);
    }

    @Override
    public List<ListDto<ProductDetail, Sale, Integer>> getlistDto(Cart cart) {
        List<CartDetail> cartDetails = cart.getCartDetails();
        List<ProductDetail> productDetails =
                cartDetails.stream().map(i -> i.getProductDetail()).toList();
        List<Product> products = productDetails.stream().map(i -> i.getProduct()).toList();
        List<Category> categories = products.stream().map(i -> i.getCategory()).toList();
        List<Sale> sales = categories.stream().map(i -> i.getSale()).toList();
        List<Integer> counts = cartDetails.stream().map(i -> i.getTotal()).toList();
        List<ListDto<ProductDetail, Sale, Integer>> list = new java.util.ArrayList<>();
        for (int i = 0; i < productDetails.size(); i++) {
            list.add(new ListDto<>(productDetails.get(i), sales.get(i), counts.get(i)));
        }
        return list;
    }

    @Transactional
    @Override
    public int addOrder(String note, String method, int userId) {
        Cart cart = this.getByUserId(userId);
        List<CartDetail> cartDetails = cart.getCartDetails();
        List<ProductDetail> productDetails =
                cartDetails.stream().map(i -> i.getProductDetail()).toList();
        for (int i = 0; i < productDetails.size(); i++) {
            productDetails.get(i).setTotal(productDetails.get(i).getTotal() - cartDetails.get(i).getTotal());
        }
        productDetailRepository.saveAll(productDetails);


        Order order = new Order();
        order.setCompleted(false);
        order.setNote(note);
        order.setMethod(method);
        order.setPrice(cart.getPrice());
        order.setTotal(cart.getTotal());
        order.setUser(userRepository.findById(userId));
        order = orderRepository.save(order);
        cart.setTotal(0);
        cart.setPrice(0);

        if (order != null) {
            for (var cartDetail : cartDetails) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProductDetail(cartDetail.getProductDetail());
                orderDetail.setTotal(cartDetail.getTotal());
                orderDetailRepository.save(orderDetail);
            }
        }
        cartRepository.save(cart);
        cartDetailRepository.deleteAll();
        return order.getId();
    }

    @Override
    public void addCodOrder(HttpSession session) {
        String fullname = (String) session.getAttribute("fullname");
        String email = (String) session.getAttribute("email");
        String address = (String) session.getAttribute("address");
        String note = (String) session.getAttribute("note");
        String method = (String) session.getAttribute("paymentMethod");

        note += ", " + fullname + ", " + email + ", " + address;
        int orderId = this.addOrder(note, method, (int) session.getAttribute("id"));
        Order order = orderRepository.findById(orderId);
        try {
            mailService.sendOrderConfirmationEmail(email, fullname,
                    orderId, order.getPrice(), order.getTotal());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
