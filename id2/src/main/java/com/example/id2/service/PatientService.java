package com.example.id2.service;

import com.example.id2.dto.CreatePatientRequest;
import com.example.id2.dto.SearchPatientResponse;

public interface PatientService {

    void createPatient (CreatePatientRequest createPatientRequest);

    SearchPatientResponse searchPatient (String dni);
}
