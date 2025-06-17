package com.example.id2.service.implementation;

import com.example.id2.dto.AuthenticateRequestDto;
import com.example.id2.dto.AuthenticateResponseDto;
import com.example.id2.dto.RegisterRequestDto;
import com.example.id2.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public AuthenticateResponseDto authenticate(AuthenticateRequestDto authenticateRequestDto) {
        return null;
    }

    @Override
    public AuthenticateResponseDto register(RegisterRequestDto registerRequestDto) {
        return null;
    }
}
