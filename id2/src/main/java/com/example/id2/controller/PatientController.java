package com.example.id2.controller;

import com.example.id2.dto.CreatePatientRequest;
import com.example.id2.dto.SearchPatientResponse;
import com.example.id2.service.PatientService;
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

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
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
}
