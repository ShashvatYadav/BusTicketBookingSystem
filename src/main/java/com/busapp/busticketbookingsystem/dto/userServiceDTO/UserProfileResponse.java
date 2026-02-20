package com.busapp.busticketbookingsystem.dto.userServiceDTO;

import com.busapp.busticketbookingsystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
