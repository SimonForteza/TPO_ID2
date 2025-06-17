package com.example.id2.service;

import com.example.id2.dto.AuthenticateRequestDto;
import com.example.id2.dto.AuthenticateResponseDto;
import com.example.id2.dto.RegisterRequestDto;

public interface AuthenticationService {

    AuthenticateResponseDto authenticate (AuthenticateRequestDto authenticateRequestDto);

    AuthenticateResponseDto register (RegisterRequestDto registerRequestDto);

}
