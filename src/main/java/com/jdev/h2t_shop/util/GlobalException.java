package com.jdev.h2t_shop.util;

import com.jdev.h2t_shop.util.exception.InvalidException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(InvalidException.class)
    public String handleInvalid(Exception e, Model model){
        model.addAttribute("message", e.getMessage());
        return "error/invalid";
    }
}
