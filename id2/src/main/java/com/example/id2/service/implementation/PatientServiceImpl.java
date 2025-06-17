package com.example.id2.service.implementation;

import com.example.id2.dto.CreatePatientRequest;
import com.example.id2.dto.SearchPatientResponse;
import com.example.id2.mapper.PatientMapper;
import com.example.id2.model.PatientModel;
import com.example.id2.repository.PatientRepository;
import com.example.id2.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    private PatientMapper patientMapper;

    @Override
    public void createPatient(CreatePatientRequest createPatientRequest) {

    }

    @Override
    public SearchPatientResponse searchPatient(String dni) {
        PatientModel patientModel = patientRepository.findByDni(dni)
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
        //TODO verifications when the Neo4J DB is up if the patient is being attended by the doctor that is requesting the data -> forbidden if not
        return patientMapper.patientModelToSearchResponse(patientModel);
    }
}
