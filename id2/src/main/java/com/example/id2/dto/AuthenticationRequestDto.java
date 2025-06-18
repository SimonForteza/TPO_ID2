package com.example.id2.dto;

import java.io.Serializable;

public record AuthenticationRequestDto(

        String email,

        String password

) implements Serializable {}
