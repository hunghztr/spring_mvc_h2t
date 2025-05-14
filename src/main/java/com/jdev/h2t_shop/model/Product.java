package com.jdev.h2t_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int quantity;
    String description;
    double price;
    @ManyToOne
    @JoinColumn(name = "image_id")
    Image image;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    @OneToOne(mappedBy = "product")
    ProductDetail productDetails;
    @OneToMany(mappedBy = "product")
    List<OrderDetail> order_details;
    @OneToMany(mappedBy = "product")
    List<CartDetail> cart_details;
    @OneToMany(mappedBy = "product")
    List<Rating> ratings;
}
