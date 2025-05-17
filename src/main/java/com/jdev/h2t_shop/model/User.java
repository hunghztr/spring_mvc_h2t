package com.jdev.h2t_shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull
    @NotBlank
    String username;
    @NotNull
    @NotBlank
    String password;
    String address;
    String fullname;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
    @OneToOne(mappedBy = "user")
    Cart cart;
    @OneToMany(mappedBy = "user")
    List<Order> orders;
    @OneToMany(mappedBy = "user")
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
