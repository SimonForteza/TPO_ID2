package com.example.id2.dto;

import java.io.Serializable;

public record RegisterRequestDto (

    String email,

    String password,

    String dni,

    String role

) implements Serializable {}
