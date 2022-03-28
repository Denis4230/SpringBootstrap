package net.proselyte.springbootstrap.controller;


import net.proselyte.springbootstrap.model.User;
import net.proselyte.springbootstrap.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getUsers(Principal principal, Model model) {
        model.addAttribute("user", userService.findByUserName(principal.getName()));
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user,
                          @ModelAttribute("listRoles") String[] roles){
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

    @PatchMapping ("/admin/{id}/patch")
    public String updateUser(@ModelAttribute("user") User user,
                             @ModelAttribute("listRoles") String[] roles){
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping ("/admin/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin/";
    }
}
