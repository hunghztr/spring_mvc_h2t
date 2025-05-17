package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.Size;
import com.jdev.h2t_shop.service.SizeService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/size")
public class SizeController {
    private final SizeService sizeService;
    public SizeController(SizeService sizeService) {
        this.sizeService = sizeService;
    }
    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("sizes",sizeService.getAll());
       return "admin/size/show";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("size", new Size());
        return "admin/size/create";
    }
    @PostMapping("/create")
    public String postCreate(@ModelAttribute("size") Size size){
        sizeService.create(size);
        return "redirect:/admin/size/";
    }
    @PostMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") int id){
        sizeService.deleteById(id);
        return "redirect:/admin/size/";
    }
}
