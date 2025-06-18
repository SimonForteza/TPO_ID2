package com.example.id2.service.implementation;

import com.example.id2.dto.CreateProfessionalRequestDto;
import com.example.id2.dto.FilterProfessionalBySpecialtyRequestDto;
import com.example.id2.dto.FilterProfessionalBySpecialtyResponseDto;
import com.example.id2.model.mongo.ProfessionalMongoModel;
import com.example.id2.model.neo.ProfessionalNeoModel;
import com.example.id2.repository.mongo.ProfessionalMongoRepository;
import com.example.id2.repository.neo.ProfessionalNeoRepository;
import com.example.id2.service.ProfessionalService;

import java.util.ArrayList;

public class ProfessionalServiceImpl implements ProfessionalService {

    ProfessionalNeoRepository professionalNeoRepository;

    ProfessionalMongoRepository professionalMongoRepository;

    public ProfessionalServiceImpl(ProfessionalNeoRepository professionalNeoRepository, ProfessionalMongoRepository professionalMongoRepository) {
        this.professionalNeoRepository = professionalNeoRepository;
        this.professionalMongoRepository = professionalMongoRepository;
    }

    @Override
    public void createProfessional(CreateProfessionalRequestDto createProfessionalRequestDto) {
        professionalNeoRepository.save(new ProfessionalNeoModel(createProfessionalRequestDto.dni()));
        professionalMongoRepository.save(new ProfessionalMongoModel(
                createProfessionalRequestDto.dni(),
                createProfessionalRequestDto.name(),
                createProfessionalRequestDto.specialty(),
                createProfessionalRequestDto.jsonData()
        ));
    }

    @Override
    public FilterProfessionalBySpecialtyResponseDto filterProfessionalBySpecialty(FilterProfessionalBySpecialtyRequestDto request) {
        return new FilterProfessionalBySpecialtyResponseDto(
            professionalMongoRepository.findBySpecialty(request.specialty()).orElse(new ArrayList<>())
        );
    }
}
