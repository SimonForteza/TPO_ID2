package com.example.id2.controller;

import com.example.id2.dto.FilterProfessionalBySpecialtyRequestDto;
import com.example.id2.dto.FilterProfessionalBySpecialtyResponseDto;
import com.example.id2.dto.SearchPatientResponse;
import com.example.id2.service.PatientService;
import com.example.id2.service.ProfessionalService;
import com.example.id2.service.RelationshipService;
import com.example.id2.service.RiskScoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/professional")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping(path = "/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilterProfessionalBySpecialtyResponseDto> getProfessionalBySpecialty (@RequestBody FilterProfessionalBySpecialtyRequestDto request) {
        logger.info(request.toString());
        return ResponseEntity.ok(professionalService.filterProfessionalBySpecialty(request));
    }
}
