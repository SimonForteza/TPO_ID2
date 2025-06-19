package com.example.id2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record AddPatientToProfessionalRequestDto (

        @JsonProperty("patientDni")
        String patientDni,

        @JsonProperty("professionalDni")
        String professionalDni

) implements Serializable {}
