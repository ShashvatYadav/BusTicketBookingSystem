package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.dto.userServiceDTO.UserProfileResponse;
import com.busapp.busticketbookingsystem.entity.User;
import com.busapp.busticketbookingsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public UserProfileResponse getProfile(@AuthenticationPrincipal User user){
        return new UserProfileResponse(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
