package com.jdev.h2t_shop.config;

import com.jdev.h2t_shop.model.*;
import com.jdev.h2t_shop.repository.UserRepository;
import com.jdev.h2t_shop.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final PasswordEncoder passwordEncoder;
    private final SaleService saleService;

    public DataInit(UserService userService,
                    PasswordEncoder passwordEncoder,
                    RoleService roleService,
                    CategoryService categoryService,
                    ColorService colorService,
                    SizeService sizeService,
                    SaleService saleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.categoryService = categoryService;
        this.colorService = colorService;
        this.sizeService = sizeService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) {
        if(roleService.count() == 0L){
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleUser.setDescription("Đây là vai trò người dùng thông thường");
            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleAdmin.setDescription("Đây là vai trò quản trị viên");
            roleService.create(roleUser);
            roleService.create(roleAdmin);
            System.out.println("Roles created!");
        }
        if(saleService.count() == 0L){
            Sale sale = new Sale();
            sale.setDiscount(0);
            sale.setFreeship(false);
            saleService.create(sale);
            System.out.println("Sale created!");
        }
        if(categoryService.count() == 0L){
            Category category = new Category();
            category.setName("none");
            categoryService.create(category);
            System.out.println("Category created!");
        }
        if(colorService.count() == 0L){
            Color color = new Color();
            color.setName("none");
            colorService.create(color);
            System.out.println("Color created!");
        }
        if(sizeService.count() == 0L){
            Size size = new Size();
            size.setName("none");
            sizeService.create(size);
            System.out.println("Size created!");
        }
        if (userService.getUserByUsername("ad") == null) {
            User admin = new User();
            admin.setUsername("ad");
            admin.setPassword(passwordEncoder.encode("1")); // mã hóa mật khẩu
            admin.setRole(roleService.getByName("ROLE_ADMIN"));
            userService.create(admin);
            System.out.println("Admin account created!");
        } else {
            System.out.println("Admin account already exists.");
        }
    }
}

