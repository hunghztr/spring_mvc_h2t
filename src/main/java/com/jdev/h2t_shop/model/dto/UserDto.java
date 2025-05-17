package com.jdev.h2t_shop.model.dto;

import com.jdev.h2t_shop.model.Role;
import com.jdev.h2t_shop.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Integer id;
    @NotBlank
    @NotNull
    String username;
    String address;
    String fullname;
    Role role;
    public UserDto toUserDto(User user) {
        this.role = new Role();
        this.id = user.getId();
        this.username = user.getUsername();
        this.address = user.getAddress();
        this.fullname = user.getFullname();
        this.setRole(user.getRole());
        return this;
    }
    public User toUser() {
        User user = new User();
        user.setId(this.getId());
        user.setUsername(this.getUsername());
        user.setAddress(this.getAddress());
        user.setFullname(this.getFullname());
        user.setRole(this.getRole());
        return user;
    }
}
