package com.jdev.h2t_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "product_details")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer total;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @ManyToOne
    @JoinColumn(name = "color_id")
    Color color;
    @ManyToOne
    @JoinColumn(name = "size_id")
    Size size;
    @OneToMany(mappedBy = "productDetail")
    List<OrderDetail> order_details;
    @OneToMany(mappedBy = "productDetail")
    List<CartDetail> cart_details;
    @OneToMany(mappedBy = "productDetail")
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
