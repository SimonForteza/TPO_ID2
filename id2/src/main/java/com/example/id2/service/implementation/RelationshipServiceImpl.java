package com.example.id2.service.implementation;

import com.example.id2.model.neo.PatientNeoModel;
import com.example.id2.model.neo.RelationshipWeight;
import com.example.id2.repository.neo.PatientNeoRepository;
import com.example.id2.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private PatientNeoRepository patientNeoRepository;

    // Establish family relationship between two patients
    public void establishFamilyRelationship(String patient1MongoId,
                                            String patient2MongoId,
                                            RelationshipWeight relationshipWeight) {

        // Validación: no puede relacionarse consigo mismo
        if (patient1MongoId.equals(patient2MongoId)) {
            throw new IllegalArgumentException("Un paciente no puede establecer una relación consigo mismo.");
        }


        //  verify the existence of Patient and Professional
        PatientNeoModel patient1 = patientNeoRepository.findByDni(patient1MongoId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patient1MongoId));

        PatientNeoModel patient2 = patientNeoRepository.findByDni(patient2MongoId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patient2MongoId));

        //  Create the relationship
        patientNeoRepository.createFamilyRelationship(patient1MongoId, patient2MongoId,
                relationshipWeight.getWeight());
    }

    // Get all family members of a patient
    public List<PatientNeoModel> getFamilyMembers(String patientMongoId) {
        return patientNeoRepository.findFamilyMembers(patientMongoId);
    }

    // Get family members with weight less than or equal to maxWeight
    public List<PatientNeoModel> getFamilyMembersByMaxWeight(String patientMongoId, RelationshipWeight maxWeight) {
        return patientNeoRepository.findFamilyMembersByMaxWeight(patientMongoId, maxWeight.getWeight());
    }

    // Get family members by relationship type
    public List<PatientNeoModel> getFamilyMembersByType(String patientMongoId, RelationshipWeight relationshipWeight) {
        return patientNeoRepository.findFamilyMembersByType(patientMongoId, relationshipWeight.getWeight());
    }
}
