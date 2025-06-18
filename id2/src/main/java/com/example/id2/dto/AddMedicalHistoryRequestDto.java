package com.example.id2.dto;

import java.io.Serializable;
import java.util.Map;

public record AddMedicalHistoryRequestDto(

        String dni,

        Map<String, Object> medicalHistory

) implements Serializable {}
