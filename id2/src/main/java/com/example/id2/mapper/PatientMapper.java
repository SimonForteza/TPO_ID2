package com.example.id2.mapper;

import com.example.id2.dto.SearchPatientResponse;
import com.example.id2.model.mongo.PatientMongoModel;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public SearchPatientResponse patientModelToSearchResponse (PatientMongoModel patientMongoModel) {
        return new SearchPatientResponse(
                patientMongoModel.getName(),
                patientMongoModel.getAddress(),
                patientMongoModel.getAge(),
                patientMongoModel.getDni(),
                patientMongoModel.getJsonData()
        );
    }
}
