package com.jdev.h2t_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "images")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
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
