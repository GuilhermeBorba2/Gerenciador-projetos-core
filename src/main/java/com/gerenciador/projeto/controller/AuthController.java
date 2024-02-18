package com.gerenciador.projeto.controller;

import com.gerenciador.projeto.model.DTO.AuthTokenResponse;
import com.gerenciador.projeto.model.DTO.UserLoginDto;
import com.gerenciador.projeto.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto loginDto) {
        String token = authService.authenticateUser(loginDto.getUsername(), loginDto.getPassword());
        return ResponseEntity.ok(new AuthTokenResponse(token));
    }
}
