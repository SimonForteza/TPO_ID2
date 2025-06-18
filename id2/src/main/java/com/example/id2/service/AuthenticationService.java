package com.example.id2.service;

import com.example.id2.dto.AuthenticationRequestDto;
import com.example.id2.dto.AuthenticationResponseDto;
import com.example.id2.dto.RegisterRequestDto;

public interface AuthenticationService {

    AuthenticationResponseDto authenticate (AuthenticationRequestDto authenticationRequestDto);

    AuthenticationResponseDto register (RegisterRequestDto registerRequestDto);

}
