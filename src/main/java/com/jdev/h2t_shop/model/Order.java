package com.jdev.h2t_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    double total;
    String note;
    String method;
    boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
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
