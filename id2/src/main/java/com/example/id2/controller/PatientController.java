package com.example.id2.controller;

import com.example.id2.dto.*;
import com.example.id2.model.neo.RelationshipWeight;
import com.example.id2.service.PatientService;
import com.example.id2.service.RelationshipService;
import com.example.id2.service.RiskScoringService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;
    private final RelationshipService relationshipService;
    private final RiskScoringService riskScoringService;

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientService patientService, RelationshipService relationshipService, RiskScoringService riskScoringService) {
        this.patientService = patientService;
        this.relationshipService = relationshipService;
        this.riskScoringService = riskScoringService;
    }

    @GetMapping(path = "/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchPatientResponse> searchPatient (@PathVariable String dni) {
        logger.info("SEARCH PATIENT REQUEST WITH DNI: " + dni);
        return ResponseEntity.ok(patientService.searchPatient(dni));
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPatient (@RequestBody CreatePatientRequest createPatientRequest) {
        logger.info(createPatientRequest.toString());
        patientService.createPatient(createPatientRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/{familyDni}/{patientDni}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addFamilyRelation (@PathVariable String patientDni, @PathVariable String familyDni,
                                                   @RequestBody CreateFamilyRelationDto createFamilyRelationDto) {
        logger.info("CREATING FAMILY RELATION BETWEEN " + patientDni + " AND " + familyDni + " WITH RELATION: " + createFamilyRelationDto.relation());
        relationshipService.establishFamilyRelationship(patientDni, familyDni, RelationshipWeight.valueOf(createFamilyRelationDto.relation()));
        return ResponseEntity.ok().build();
    }


    @PostMapping(path = "/medical-history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMedicalHistory (@RequestBody AddMedicalHistoryRequestDto addMedicalHistoryRequestDto) {
        patientService.addMedicalHistory(addMedicalHistoryRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/assign-professional", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPatientToProfessional (@RequestBody AddPatientToProfessionalRequestDto addPatientToProfessionalRequestDto) {
        patientService.addPatientToProfessional(addPatientToProfessionalRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/sensor-data", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPatientSensorData (@RequestBody AddPatientSensorDataDto addPatientSensorDataDto) {
        patientService.addPatientSensorData(addPatientSensorDataDto);
        return ResponseEntity.ok().build();
    }

}
