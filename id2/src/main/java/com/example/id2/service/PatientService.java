package com.example.id2.service;

import com.example.id2.dto.AddMedicalHistoryRequestDto;
import com.example.id2.dto.AddPatientSensorDataDto;
import com.example.id2.dto.AddPatientToProfessionalRequestDto;
import com.example.id2.dto.CreatePatientRequest;
import com.example.id2.dto.SearchPatientResponse;

public interface PatientService {

    void createPatient (CreatePatientRequest createPatientRequest);

    SearchPatientResponse searchPatient (String dni);

    void addMedicalHistory (AddMedicalHistoryRequestDto addMedicalHistoryRequestDto);

    void addPatientToProfessional (AddPatientToProfessionalRequestDto addPatientToProfessionalRequestDto);

    void addPatientSensorData (AddPatientSensorDataDto addPatientSensorDataDto);
}