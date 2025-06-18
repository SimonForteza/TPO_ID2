package com.example.id2.controller;

import com.example.id2.dto.AuthenticationRequestDto;
import com.example.id2.dto.AuthenticationResponseDto;
import com.example.id2.dto.RegisterRequestDto;
import com.example.id2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponseDto> authenticate (@RequestBody AuthenticationRequestDto authRequest) {
        logger.info(authRequest.toString());
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponseDto> register (@RequestBody RegisterRequestDto registerRequest) {
        logger.info(registerRequest.toString());
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
}
