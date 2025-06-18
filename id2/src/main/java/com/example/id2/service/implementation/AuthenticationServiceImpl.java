package com.example.id2.service.implementation;

import com.example.id2.config.JwtService;
import com.example.id2.dto.AuthenticationRequestDto;
import com.example.id2.dto.AuthenticationResponseDto;
import com.example.id2.dto.RegisterRequestDto;
import com.example.id2.model.mongo.AuthenticationModel;
import com.example.id2.repository.mongo.AuthenticationRepository;
import com.example.id2.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationRepository authenticationRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.email(), authenticationRequestDto.password()));

        AuthenticationModel authModel = authenticationRepository.findByEmail(authenticationRequestDto.email())
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));

        return new AuthenticationResponseDto(jwtService.generateToken(authModel, authModel.getEmail(), authModel.getDni(), authModel.getRole()));
    }

    @Override
    public AuthenticationResponseDto register(RegisterRequestDto registerRequestDto) {
        AuthenticationModel authModel = new AuthenticationModel(
                registerRequestDto.email(),
                passwordEncoder.encode(registerRequestDto.password()),
                registerRequestDto.dni(),
                registerRequestDto.role()
        );
        authenticationRepository.save(authModel);
        return new AuthenticationResponseDto(jwtService.generateToken(authModel, authModel.getEmail(), authModel.getDni(), authModel.getRole()));
    }
}
