package com.busapp.busticketbookingsystem.dto.userServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
