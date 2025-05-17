package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.Color;
import com.jdev.h2t_shop.service.ColorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/color")
public class ColorController {
    private final ColorService colorService;
    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("colors",colorService.getAll());
       return "admin/color/show";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("color", new Color());
        return "admin/color/create";
    }
    @PostMapping("/create")
    public String postCreate(@ModelAttribute("color") Color color){
        colorService.create(color);
        return "redirect:/admin/color/";
    }
    @PostMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") int id){
        colorService.deleteById(id);
        return "redirect:/admin/color/";
    }
}
