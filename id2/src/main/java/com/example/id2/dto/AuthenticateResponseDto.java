package com.example.id2.dto;

import java.io.Serializable;

public record AuthenticateResponseDto (

        String jwt

) implements Serializable {}
