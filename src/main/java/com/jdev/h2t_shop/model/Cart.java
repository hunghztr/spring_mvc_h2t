package com.jdev.h2t_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    int total;
    int price;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;
    @OneToMany(mappedBy = "cart")
    List<CartDetail> cartDetails;
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
