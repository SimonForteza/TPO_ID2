package com.example.id2.dto;

import java.io.Serializable;

public record AuthenticationResponseDto(

        String jwt

) implements Serializable {}
