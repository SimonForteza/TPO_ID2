package com.example.id2.service.implementation;

import com.example.id2.config.JwtService;
import com.example.id2.dto.AddMedicalHistoryRequestDto;
import com.example.id2.dto.AddPatientSensorDataDto;
import com.example.id2.dto.AddPatientToProfessionalRequestDto;
import com.example.id2.dto.CreatePatientRequest;
import com.example.id2.dto.FamiliarPrecedentRequest;
import com.example.id2.dto.SearchPatientResponse;
import com.example.id2.mapper.PatientMapper;
import com.example.id2.model.cassandra.PatientSensorStatusModel;
import com.example.id2.model.mongo.PatientMongoModel;
import com.example.id2.model.neo.PatientNeoModel;
import com.example.id2.model.neo.ProfessionalNeoModel;
import com.example.id2.repository.cassandra.PatientSensorStatusRepository;
import com.example.id2.repository.mongo.PatientMongoRepository;
import com.example.id2.repository.neo.PatientNeoRepository;
import com.example.id2.repository.neo.ProfessionalNeoRepository;
import com.example.id2.service.PatientService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService {

    private final JwtService jwtService;

    private final ProfessionalNeoRepository professionalNeoRepository;

    private final PatientMongoRepository patientMongoRepository;

    private final PatientNeoRepository patientNeoRepository;

    private final PatientSensorStatusRepository patientSensorStatusRepository;

    private final PatientMapper patientMapper;

    public PatientServiceImpl(JwtService jwtService, ProfessionalNeoRepository professionalNeoRepository, PatientMongoRepository patientMongoRepository,
                              PatientNeoRepository patientNeoRepository, PatientSensorStatusRepository patientSensorStatusRepository, PatientMapper patientMapper) {
        this.jwtService = jwtService;
        this.professionalNeoRepository = professionalNeoRepository;
        this.patientMongoRepository = patientMongoRepository;
        this.patientNeoRepository = patientNeoRepository;
        this.patientSensorStatusRepository = patientSensorStatusRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public void createPatient(CreatePatientRequest createPatientRequest) {
        PatientMongoModel patientMongoModel = new PatientMongoModel(
                createPatientRequest.dni(),
                createPatientRequest.name(),
                createPatientRequest.address(),
                createPatientRequest.age(),
                createPatientRequest.jsonData()
        );
        patientMongoRepository.save(patientMongoModel);
    }

    @Override
    public SearchPatientResponse searchPatient(String dni) {
        checkRoleForPatientOperationsWithProfessional(dni);
        PatientMongoModel patientMongoModel = patientMongoRepository.findByDni(dni)
                .orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
        List<PatientNeoModel> family = patientNeoRepository.findFamilyMembers(dni).orElse(new ArrayList<>());
        List<ProfessionalNeoModel> professionals = patientNeoRepository.findProfessionalsConsultingPatient(dni).orElse(new ArrayList<>());
        Map<String, Object> jsonData = patientMongoModel.getJsonData();
        if (jsonData == null) {
            jsonData = new HashMap<>();
        }
        jsonData.put("family", family);
        jsonData.put("professionals", professionals);
        patientMongoModel.setJsonData(jsonData);
        return patientMapper.patientModelToSearchResponse(patientMongoModel);
    }

    @Override
    public void addFamiliarPrecedent(FamiliarPrecedentRequest familiarPrecedentRequest) {
        //TODO add familiar precedent in Neo4J
    }

    @Override
    public void addMedicalHistory(AddMedicalHistoryRequestDto addMedicalHistoryRequestDto) {
        checkRoleForPatientOperationsWithProfessional(addMedicalHistoryRequestDto.dni());

        PatientMongoModel patientMongo = patientMongoRepository.findByDni(addMedicalHistoryRequestDto.dni())
                .orElseThrow(() -> new NoSuchElementException("patient doesn't exist"));

        patientMongo.getJsonData().putAll(addMedicalHistoryRequestDto.medicalHistory());
    }

    @Override
    public void addPatientToProfessional(AddPatientToProfessionalRequestDto addPatientToProfessionalRequestDto) {
        checkRoleForPatientOperationsWithoutProfessional(addPatientToProfessionalRequestDto.patientDni());

        ProfessionalNeoModel professional = professionalNeoRepository.findById(addPatientToProfessionalRequestDto.professionalDni())
                .orElseThrow(() -> new NoSuchElementException("Professional not found with DNI: " +
                        addPatientToProfessionalRequestDto.professionalDni()));

        PatientNeoModel patient = patientNeoRepository.findById(addPatientToProfessionalRequestDto.patientDni())
                .orElseThrow(() -> new NoSuchElementException("Patient not found with DNI: " +
                        addPatientToProfessionalRequestDto.patientDni()));

        if (professional.getPatients().contains(patient)) {
            throw new RuntimeException("Patient is already assigned to this professional");
        }

        professional.getPatients().add(patient);
        professionalNeoRepository.save(professional);
    }

    @Override
    public void addPatientSensorData(AddPatientSensorDataDto addPatientSensorDataDto) {
        checkRoleForPatientOperationsWithoutProfessional(addPatientSensorDataDto.getDni());

        PatientSensorStatusModel.Key sensorKey = new PatientSensorStatusModel.Key();
        sensorKey.setDni(addPatientSensorDataDto.getDni());
        sensorKey.setSensorType(addPatientSensorDataDto.getSensorType());
        sensorKey.setTimestamp(addPatientSensorDataDto.getTimestamp());

        PatientSensorStatusModel sensor = new PatientSensorStatusModel();
        sensor.setKey(sensorKey);
        sensor.setStatusValue(addPatientSensorDataDto.getStatusValue());

        patientSensorStatusRepository.save(sensor);
    }

    private void checkRoleForPatientOperationsWithProfessional (String patientDni) {
        String requesterDni = jwtService.extractDni(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        String role = jwtService.extractRole(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());

        if (role.equals("ADMIN"))
            return;

        if (requesterDni.equals(patientDni))
            return;

        if (role.equals("PROFESSIONAL")
                && professionalNeoRepository.findPatientsConsultedByProfessional(requesterDni)
                .get()
                .stream()
                .anyMatch(patient -> patient.getDni().equals(patientDni))) {
            return;
        }

        throw new NoSuchElementException();
    }

    private void checkRoleForPatientOperationsWithoutProfessional (String patientDni) {
        String requesterDni = jwtService.extractDni(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
        String role = jwtService.extractRole(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());

        if (role.equals("ADMIN"))
            return;

        if (requesterDni.equals(patientDni))
            return;

        if (role.equals("PROFESSIONAL")
                && professionalNeoRepository.findPatientsConsultedByProfessional(requesterDni)
                .get()
                .stream()
                .anyMatch(patient -> patient.getDni().equals(patientDni))) {
            return;
        }

        throw new NoSuchElementException();
    }
}
