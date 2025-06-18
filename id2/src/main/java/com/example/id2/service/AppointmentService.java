package com.example.id2.service;

import com.example.id2.dto.CreateAppointmentRequestDto;
import com.example.id2.model.mongo.AppointmentModel;

import java.util.List;

public interface AppointmentService {
    List<AppointmentModel> getAppointmentsByProfessionalId(String professionalDni);
    void cancelAppointment (String appointmentId);
    void createAppointment (CreateAppointmentRequestDto createAppointmentRequestDto);
}
