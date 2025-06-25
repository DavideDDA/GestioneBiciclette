package com.example.GestioneBiciclette.security.controller;

import com.example.GestioneBiciclette.security.entity.ERole;
import com.example.GestioneBiciclette.security.entity.User;
import com.example.GestioneBiciclette.security.payload.JWTAuthResponse;
import com.example.GestioneBiciclette.security.payload.LoginDto;
import com.example.GestioneBiciclette.security.payload.RegisterDto;
import com.example.GestioneBiciclette.security.repository.UserRepository;
import com.example.GestioneBiciclette.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    UserRepository userRepository;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
           	
    	String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setUsername(loginDto.getUsername());
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
    // Login per admin
    @PostMapping("/login/admin")
    public ResponseEntity<JWTAuthResponse> adminLogin(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        User user = userRepository.findByUsername(loginDto.getUsername()).orElse(null);
        if (user == null || !user.getRoles().stream()
                .anyMatch(role -> role.getRoleName() == ERole.ROLE_ADMIN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setUsername(loginDto.getUsername());
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
