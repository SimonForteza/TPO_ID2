package com.example.id2.controller;

import com.example.id2.dto.CreateAppointmentRequestDto;
import com.example.id2.dto.GetAppointmentByProfessionalIdResponseDto;
import com.example.id2.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> cancelAppointment (@PathVariable String id) {
        logger.info("CANCELLING APPOINTMENT WITH ID: " + id);
        appointmentService.cancelAppointment(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAppointmentByProfessionalIdResponseDto> getAppointmentByProfessionalId (@PathVariable String id) {
        logger.info("GETTING APPOINTMENTS BY PROFESSIONAL WITH ID: " + id);
        appointmentService.getAppointmentsByProfessionalId(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createAppointment (@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        logger.info(createAppointmentRequestDto.toString());
        appointmentService.createAppointment(createAppointmentRequestDto);
        return ResponseEntity.ok().build();
    }
}
