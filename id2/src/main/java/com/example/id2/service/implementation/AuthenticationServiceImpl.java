package com.example.id2.service.implementation;

import com.example.id2.config.JwtService;
import com.example.id2.dto.AuthenticateRequestDto;
import com.example.id2.dto.AuthenticateResponseDto;
import com.example.id2.dto.RegisterRequestDto;
import com.example.id2.model.mongo.AuthenticationModel;
import com.example.id2.repository.mongo.AuthenticationRepository;
import com.example.id2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final AuthenticationRepository authenticationRepository;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, AuthenticationRepository authenticationRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public AuthenticateResponseDto authenticate(AuthenticateRequestDto authenticateRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequestDto.email(), authenticateRequestDto.password()));

        AuthenticationModel authModel = authenticationRepository.findByEmail(authenticateRequestDto.email())
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));

        return new AuthenticateResponseDto(jwtService.generateToken(authModel, authModel.getEmail(), authModel.getDni(), authModel.getRole()));
    }

    @Override
    public AuthenticateResponseDto register(RegisterRequestDto registerRequestDto) {
        AuthenticationModel authModel = new AuthenticationModel(registerRequestDto.email(), registerRequestDto.password(), registerRequestDto.dni(), registerRequestDto.role());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getEmail(), authModel.getPassword()));

        return new AuthenticateResponseDto(jwtService.generateToken(authModel, authModel.getEmail(), authModel.getDni(), authModel.getRole()));
    }
}
