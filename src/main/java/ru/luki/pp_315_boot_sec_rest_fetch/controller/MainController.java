package ru.luki.pp_315_boot_sec_rest_fetch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.luki.pp_315_boot_sec_rest_fetch.model.User;
import ru.luki.pp_315_boot_sec_rest_fetch.service.UserService;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    final private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String mainPage() {
        return "core";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "logout";
    }


}
