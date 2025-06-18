package com.example.id2.service.implementation;

import com.example.id2.model.Neo4jPatient;
import com.example.id2.model.Neo4jProfessional;
import com.example.id2.model.RelationshipWeight;
import com.example.id2.repository.Neo4jPatientRepository;
import com.example.id2.repository.Neo4jProfessionalRepository;
import com.example.id2.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    @Autowired
    private Neo4jPatientRepository neo4jPatientRepository;

    // Establish family relationship between two patients
    public void establishFamilyRelationship(String patient1MongoId,
                                            String patient2MongoId,
                                            RelationshipWeight relationshipWeight) {

        // Validación: no puede relacionarse consigo mismo
        if (patient1MongoId.equals(patient2MongoId)) {
            throw new IllegalArgumentException("Un paciente no puede establecer una relación consigo mismo.");
        }


        //  verify the existence of Patient and Professional
        Neo4jPatient patient1 = neo4jPatientRepository.findByMongoId(patient1MongoId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patient1MongoId));

        Neo4jPatient patient2 = neo4jPatientRepository.findByMongoId(patient2MongoId)
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + patient2MongoId));

        //  Create the relationship
        neo4jPatientRepository.createFamilyRelationship(patient1MongoId, patient2MongoId,
                relationshipWeight.getWeight());
    }

    // Get all family members of a patient
    public List<Neo4jPatient> getFamilyMembers(String patientMongoId) {
        return neo4jPatientRepository.findFamilyMembers(patientMongoId);
    }

    // Get family members with weight less than or equal to maxWeight
    public List<Neo4jPatient> getFamilyMembersByMaxWeight(String patientMongoId, RelationshipWeight maxWeight) {
        return neo4jPatientRepository.findFamilyMembersByMaxWeight(patientMongoId, maxWeight.getWeight());
    }

    // Get family members by relationship type
    public List<Neo4jPatient> getFamilyMembersByType(String patientMongoId, RelationshipWeight relationshipWeight) {
        return neo4jPatientRepository.findFamilyMembersByType(patientMongoId, relationshipWeight.getWeight());
    }
}
