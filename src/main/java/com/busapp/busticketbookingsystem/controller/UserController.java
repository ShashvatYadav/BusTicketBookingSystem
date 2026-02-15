package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public String getProfile(){
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Authorities: " + auth.getAuthorities());
        return "User Profile Accessed";
    }
}
