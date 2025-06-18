package com.example.id2.dto;

import java.io.Serializable;
import java.util.Map;

public record AddMedicalHistoryRequestDto(

        Map<String, Object> medicalHistory

) implements Serializable {}
