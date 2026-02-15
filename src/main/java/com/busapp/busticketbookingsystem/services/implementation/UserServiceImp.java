package com.busapp.busticketbookingsystem.services.implementation;

import com.busapp.busticketbookingsystem.dto.AuthResponse;
import com.busapp.busticketbookingsystem.dto.LoginRequest;
import com.busapp.busticketbookingsystem.dto.RegisterRequest;
import com.busapp.busticketbookingsystem.entity.User;
import com.busapp.busticketbookingsystem.enums.Role;
import com.busapp.busticketbookingsystem.reposistory.UserRepository;
import com.busapp.busticketbookingsystem.security.JwtService;
import com.busapp.busticketbookingsystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        if(userRepo.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already registered");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepo.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse(token, user.getEmail());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found !"));
        String token = jwtService.generateToken(user);

        return new AuthResponse(token, user.getEmail());
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found !"));
    }

    @Override
    public User findById(Long userId) {
        return userRepo.findById(userId).orElseThrow(
                () -> new RuntimeException("User Not Found !")
        );
    }


    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}
