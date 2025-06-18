package com.example.id2.dto;

import com.example.id2.model.mongo.ProfessionalMongoModel;

import java.io.Serializable;
import java.util.List;

public record GetAppointmentByProfessionalIdResponseDto (

        List<ProfessionalMongoModel> professionals

) implements Serializable {}
