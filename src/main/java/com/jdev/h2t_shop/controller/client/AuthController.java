package com.jdev.h2t_shop.controller.client;

import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.service.CategoryService;
import com.jdev.h2t_shop.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class AuthController {
    private final UserService userService;
    private final CategoryService categoryService;
    public AuthController(UserService userService,
                          CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("categories",categoryService.getAll());
        model.addAttribute("user", new User());
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("categories",categoryService.getAll());
        model.addAttribute("user", new User());
        return "client/auth/register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                               @RequestParam("confirm") String confirm, Model model){

        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }
        if(!confirm.equals(user.getPassword())){
            model.addAttribute("error", "Vui lòng nhập đúng mật khẩu");
            return "client/auth/register";
        }
        User curUser = this.userService.create(user);
        if(curUser == null){
            model.addAttribute("error", "Người dùng đã tồn tại");
            return "client/auth/register";
        }
        return "redirect:/login";
    }
}
