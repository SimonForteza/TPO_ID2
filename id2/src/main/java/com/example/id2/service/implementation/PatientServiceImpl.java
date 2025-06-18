package com.example.id2.service.implementation;

import com.example.id2.config.JwtService;
import com.example.id2.dto.AddMedicalHistoryRequestDto;
import com.example.id2.dto.AddPatientToProfessionalRequestDto;
import com.example.id2.dto.CreatePatientRequest;
import com.example.id2.dto.FamiliarPrecedentRequest;
import com.example.id2.dto.SearchPatientResponse;
import com.example.id2.mapper.PatientMapper;
import com.example.id2.model.mongo.PatientModel;
import com.example.id2.repository.mongo.PatientMongoRepository;
import com.example.id2.repository.neo.PatientNeoRepository;
import com.example.id2.service.PatientService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService {

    private JwtService jwtService;

    private PatientMongoRepository patientMongoRepository;

    private PatientNeoRepository patientNeoRepository;

    private PatientMapper patientMapper;

    @Override
    public void createPatient(CreatePatientRequest createPatientRequest) {

    }

    @Override
    public SearchPatientResponse searchPatient(String dni) {
        checkRoleForPatientOperations(dni);
        PatientModel patientModel = patientMongoRepository.findByDni(dni)
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
        return patientMapper.patientModelToSearchResponse(patientModel);
    }

    @Override
    public void addFamiliarPrecedent(FamiliarPrecedentRequest familiarPrecedentRequest) {
        //TODO add familiar precedent in Neo4J
    }

    @Override
    public void addMedicalHistory(AddMedicalHistoryRequestDto addMedicalHistoryRequestDto) {
        //TODO
    }

    @Override
    public void addPatientToProfessional(AddPatientToProfessionalRequestDto addPatientToProfessionalRequestDto) {
        //TODO
    }

    private void checkRoleForPatientOperations (String patientDni) {
        String requesterDni = jwtService.extractDni(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        String role = jwtService.extractRole(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());

        if (role.equals("ADMIN"))
            return;

        if (requesterDni.equals(patientDni))
            return;

        if (patientNeoRepository.findProfessionalsByPatientDni(patientDni).get().stream().filter(d -> d.getDni().equals(requesterDni)).toList().isEmpty()
                && role.equals("PROFESSIONAL"))
            return;

        throw new RuntimeException();
    }
}
