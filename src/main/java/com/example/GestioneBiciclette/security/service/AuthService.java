package com.example.GestioneBiciclette.security.service;

import com.example.GestioneBiciclette.security.payload.LoginDto;
import com.example.GestioneBiciclette.security.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
