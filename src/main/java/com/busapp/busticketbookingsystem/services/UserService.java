package com.busapp.busticketbookingsystem.services;

import com.busapp.busticketbookingsystem.dto.AuthResponse;
import com.busapp.busticketbookingsystem.dto.LoginRequest;
import com.busapp.busticketbookingsystem.dto.RegisterRequest;
import com.busapp.busticketbookingsystem.entity.User;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    User findById(Long userId);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
