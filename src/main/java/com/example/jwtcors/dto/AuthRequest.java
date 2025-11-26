package com.example.jwtcors.dto;

import com.example.jwtcors.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String username;
    private String password;
    private Role role;
}
