package com.jdev.h2t_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "ratings")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    String mess;
    int star;
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
