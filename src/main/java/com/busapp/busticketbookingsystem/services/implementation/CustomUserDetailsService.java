package com.busapp.busticketbookingsystem.services.implementation;

import com.busapp.busticketbookingsystem.entity.User;
import com.busapp.busticketbookingsystem.reposistory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return userRepo.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }
}
