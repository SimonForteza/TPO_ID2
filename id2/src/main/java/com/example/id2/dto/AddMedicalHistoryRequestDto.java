package com.example.id2.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.Map;

public record AddMedicalHistoryRequestDto(

        String dni,

        @JsonAnySetter
        Map<String, Object> medicalHistory

) implements Serializable {}
