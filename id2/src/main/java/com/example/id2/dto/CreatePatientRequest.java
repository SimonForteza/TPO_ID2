package com.example.id2.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.Map;

public record CreatePatientRequest (

        //ya deberia existir con sus credenciales, pero excede el scope

        String name,

        String address,

        String age,

        String dni,

        @JsonAnySetter
        Map<String, Object> jsonData

) implements Serializable {}
