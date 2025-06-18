package com.example.id2.dto;

import java.io.Serializable;

public record AddPatientToProfessionalRequestDto (

        String patientDni,

        String professionalDni

) implements Serializable {}
