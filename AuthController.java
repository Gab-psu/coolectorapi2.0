// AuthController.java
package com.example.payment.controller;

import com.example.payment.dto.AuthRequest;
import com.example.payment.dto.AuthResponse;
import com.example.payment.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        String token = authService.login(authRequest.getUsername(), authRequest.getPassword());
        return new AuthResponse(token);
    }
}
