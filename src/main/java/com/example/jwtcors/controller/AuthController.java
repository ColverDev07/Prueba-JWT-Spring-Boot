package com.example.jwtcors.controller;

import com.example.jwtcors.dto.AuthRequest;
import com.example.jwtcors.entity.Role;
import com.example.jwtcors.entity.User;
import com.example.jwtcors.service.JwtUtil;
import com.example.jwtcors.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest req){
        return userService.register(req.getUsername(), req.getPassword(), req.getRole());
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest req){
        User u = userService.findByUsername(req.getUsername());

        if(u == null || !userService.checkPassword(req.getPassword(), u.getPassword())){
            throw new RuntimeException("Credenciales invalidas");
        }

        return jwtUtil.generateToken(u.getUsername(), u.getRole());
    }
}
