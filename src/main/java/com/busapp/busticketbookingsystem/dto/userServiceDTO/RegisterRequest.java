package com.busapp.busticketbookingsystem.dto.userServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String password;
    private String email;
}
