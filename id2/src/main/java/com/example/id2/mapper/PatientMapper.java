package com.example.id2.mapper;

import com.example.id2.dto.SearchPatientResponse;
import com.example.id2.model.mongo.PatientModel;

public class PatientMapper {

    public SearchPatientResponse patientModelToSearchResponse (PatientModel patientModel) {
        return new SearchPatientResponse(
                patientModel.getName(),
                patientModel.getAddress(),
                patientModel.getAge(),
                patientModel.getDni(),
                patientModel.getJsonData()
        );
    }
}
