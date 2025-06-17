package com.example.id2.dto;

import java.io.Serializable;

public record SearchPatientRequest (

        String dni

) implements Serializable {}
