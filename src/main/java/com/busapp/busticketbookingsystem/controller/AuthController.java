package com.busapp.busticketbookingsystem.controller;

import com.busapp.busticketbookingsystem.dto.userServiceDTO.AuthResponse;
import com.busapp.busticketbookingsystem.dto.userServiceDTO.LoginRequest;
import com.busapp.busticketbookingsystem.dto.userServiceDTO.RegisterRequest;
import com.busapp.busticketbookingsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ){
        return  ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
            ){
        return ResponseEntity.ok(userService.login(request));
    }
}
