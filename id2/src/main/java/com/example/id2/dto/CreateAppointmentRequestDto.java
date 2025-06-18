package com.example.id2.dto;

import java.io.Serializable;

public record CreateAppointmentRequestDto (

        String professionalDni,

        String patientDni

) implements Serializable {}
