package com.example.id2.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.Map;

public record CreateProfessionalRequestDto(

        String dni,

        String name,

        String specialty,

        @JsonAnySetter
        Map<String, Object> jsonData

) implements Serializable {}
