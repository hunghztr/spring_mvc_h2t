package com.jdev.h2t_shop.controller.client;

import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "client/auth/register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user,
                               @RequestParam("confirm") String confirm, Model model){
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
