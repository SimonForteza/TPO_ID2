package com.example.id2.service;

import com.example.id2.dto.CreateProfessionalRequestDto;
import com.example.id2.dto.FilterProfessionalBySpecialtyRequestDto;
import com.example.id2.dto.FilterProfessionalBySpecialtyResponseDto;

public interface ProfessionalService {

    void createProfessional (CreateProfessionalRequestDto createProfessionalRequestDto);

    FilterProfessionalBySpecialtyResponseDto filterProfessionalBySpecialty (FilterProfessionalBySpecialtyRequestDto request);
}
