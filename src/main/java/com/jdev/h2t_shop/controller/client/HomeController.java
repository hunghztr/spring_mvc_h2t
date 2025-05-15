package com.jdev.h2t_shop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("products","");
       return "client/home/home";
    }
    @GetMapping("/denie")
    public String denie(){
        return "client/error/denie";
    }
}
