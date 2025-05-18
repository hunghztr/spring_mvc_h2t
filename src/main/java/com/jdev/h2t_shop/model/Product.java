package com.jdev.h2t_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @Column(columnDefinition = "TEXT")
    String description;
    double price;
    @OneToMany(mappedBy = "product")
    List<Image> images;
    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
    @OneToMany(mappedBy = "product")
    List<ProductDetail> productDetails;
    @OneToMany(mappedBy = "product")
    List<OrderDetail> order_details;
    @OneToMany(mappedBy = "product")
    List<CartDetail> cart_details;
    @OneToMany(mappedBy = "product")
    List<Rating> ratings;
    Instant createdAt;
    Instant updatedAt;
    @PrePersist
    void prePersist() {
        createdAt = Instant.now();
    }
    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }
}
