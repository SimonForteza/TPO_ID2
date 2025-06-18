package com.example.id2.service.implementation;

import com.example.id2.dto.CreateAppointmentRequestDto;
import com.example.id2.model.mongo.AppointmentModel;
import com.example.id2.repository.mongo.AppointmentRepository;
import com.example.id2.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentModel> getAppointmentsByProfessionalId(String professionalDni) {
        return appointmentRepository.findByProfessionalDniAndState(professionalDni, "REGISTER");
    }

    @Override
    public void cancelAppointment (String appointmentId) {
        AppointmentModel appointmentModel = appointmentRepository.findById(appointmentId).orElseThrow(NoSuchElementException::new);
        appointmentModel.setState("CANCELLED");
        appointmentRepository.save(appointmentModel);
    }

    @Override
    public void createAppointment(CreateAppointmentRequestDto createAppointmentRequestDto) {
        appointmentRepository.save(new AppointmentModel(
                createAppointmentRequestDto.professionalDni(),
                createAppointmentRequestDto.patientDni()
        ));
    }
}