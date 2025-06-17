package com.example.id2.dto;

import java.io.Serializable;
import java.util.Map;

public record SearchPatientResponse (

        String name,

        String address,

        String age,

        String dni,

        Map<String, Object> jsonData

) implements Serializable {}
