package com.jdev.h2t_shop.controller.admin;

import com.jdev.h2t_shop.model.User;
import com.jdev.h2t_shop.model.dto.UserDto;
import com.jdev.h2t_shop.service.RoleService;
import com.jdev.h2t_shop.service.UserService;
import com.jdev.h2t_shop.service.specification.UserSpecification;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/admin/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    public UserController(UserService userService,
                          RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/")
    public String home(Model model, @RequestParam(value = "keyword", required = false) String keyword,
                       @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        Specification<User> spec = Specification.where(null);

        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and(UserSpecification.fullnameContains(keyword));
        }
        var users = userService.getAll(spec,pageable);
        log.info(users.getTotalPages()+"");
        model.addAttribute("users",users);
       return "admin/user/show";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles",roleService.getAll());
        return "admin/user/create";
    }
    @PostMapping("/create")
    public String postCreate(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()) {
            return "admin/user/create";
        }
        if(user.getRole().getId() == null){
            model.addAttribute("roleError","vui lòng chọn vai trò");
            return "admin/user/create";
        }
        userService.create(user);
        return "redirect:/admin/user/";
    }
    @GetMapping("/view/{id}")
    public String view(Model model,@PathVariable("id") int id){
        User user = userService.getById(id);
        model.addAttribute("user",user);
        return "admin/user/view";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model){
        User user = userService.getById(id);
        UserDto userDto = new UserDto();
        userDto.toUserDto(user);
        model.addAttribute("user", userDto);
        model.addAttribute("roles",roleService.getAll());
        return "admin/user/update";
    }
    @PostMapping("/update")
    public String postUpdate(@ModelAttribute("user")  @Valid UserDto userDto,BindingResult bindingResult,
                             Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("roles",roleService.getAll());
            return "admin/user/update";
        }
        if(userDto.getRole().getId() == null){
            model.addAttribute("roleError","vui lòng chọn vai trò");
            return "admin/user/update";
        }
        userService.update(userDto.toUser());
        return "redirect:/admin/user/";
    }
    @PostMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") int id){
        userService.deleteById(id);
        return "redirect:/admin/user/";
    }
}
